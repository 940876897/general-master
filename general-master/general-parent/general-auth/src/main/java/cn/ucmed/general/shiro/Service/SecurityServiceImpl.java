package cn.ucmed.general.shiro.Service;

import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;
import cn.ucmed.general.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by ucmed on 2016/3/29.
 */
public class SecurityServiceImpl implements SecurityService {


    @Autowired
    private UserService shiroUserService;

    @Value("${app.key}")
    private String appKey;

    @Override
    public UserModel login(User user) {

        UserModel userForAuth = shiroUserService.findByUsername(user.getUsername(), appKey);
        if (userForAuth == null) {
            throw new UnknownAccountException();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(
                userForAuth.getUserId(), user.getPassword());

        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        subject.getSession().setAttribute("userid", userForAuth.getUserId());

        userForAuth.setRoles(shiroUserService.findRoles(String.valueOf(subject.getPrincipal())));
        userForAuth.setPermissions(shiroUserService.findPermissions(String.valueOf(subject.getPrincipal())));

        return userForAuth;

    }
}
