package cn.ucmed.general.security;

import cn.ucmed.general.shiro.Service.SecurityService;
import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger("LoginController");

    private static final String MESSAGE = "message";

    private static final String ACCOUNT_OR_PASSWORD_ERROR = "账号或密码不正确。";

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.POST, value = "login.json")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, @RequestBody User user)
            throws IOException {

        JSONObject ret = new JSONObject();

        try {
            UserModel userForAuth = securityService.login(user);

            ret.put("userId", userForAuth.getUserId());
            ret.put("surname", userForAuth.getSurname());
            ret.put("roles", userForAuth.getRoles());
            ret.put("permissions", userForAuth.getPermissions());
            ret.put("success", "true");

            return ret;
        } catch (ExcessiveAttemptsException eaEx) {
            LOGGER.error("", eaEx);
            ret.put(MESSAGE, "密码输错次数超限，请稍后再试。");
            return ret;
        } catch (UnknownAccountException uaEX) {
            LOGGER.error("", uaEX);
            ret.put(MESSAGE, ACCOUNT_OR_PASSWORD_ERROR);//不显示“账号不存在”，以防恶意扫描账号库
            return ret;
        } catch (AuthenticationException aEx) {
            LOGGER.error("", aEx);
            ret.put(MESSAGE, ACCOUNT_OR_PASSWORD_ERROR);
            return ret;
        } catch (Exception ex) {
            LOGGER.error("", ex);
            ret.put(MESSAGE, ACCOUNT_OR_PASSWORD_ERROR);
            return ret;
        }
    }
}

