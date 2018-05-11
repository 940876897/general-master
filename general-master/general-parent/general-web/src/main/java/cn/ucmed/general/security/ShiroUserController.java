package cn.ucmed.general.security;

import cn.ucmed.common.constants.PermissionConstants;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.common.util.ResultUtil;
import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;
import cn.ucmed.general.shiro.realm.UserRealm;
import cn.ucmed.general.shiro.service.UserService;
import cn.ucmed.general.web.constants.ShiroUserConstants;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 用户控制器
 * Created by ucmed on 2015/12/30.
 */
@Controller
public class ShiroUserController {

    @Autowired
    private UserService shiroUserService;

    @Autowired
    private SessionDAO sessionDAO;

    @Value("${app.key}")
    private String appKey;

    @Autowired
    private UserRealm userRealm;

    /**
     * 创建用户的方法
     *
     * @param user
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.USER_CREATE,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroUserConstants.ADD)
    @ResponseBody
    public JSONObject addUser(@RequestBody UserModel user) {
        User userdb = shiroUserService.ifExistUser(user.getUsername(), appKey);
        //如果 用户名 与其他用户重名
        if (userdb != null && StringUtils.isNotBlank(userdb.getUserId())) {
            return ResultUtil.getResult(-1, "用户名已经存在" + user.getUsername());
        }

        shiroUserService.createUser(user, appKey);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 修改用户的方法
     *
     * @param user
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.USER_UPDATE,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroUserConstants.EDIT)
    @ResponseBody
    public JSONObject updateUser(@RequestBody UserModel user) {
        User userdb = shiroUserService.findUserDupUserNameNotUserId(user.getUsername(), user.getUserId(), appKey);
        //如果 修改的用户名 与其他用户重名
        if (userdb != null && StringUtils.isNotBlank(userdb.getUserId()) && !userdb.getUserId().equalsIgnoreCase(user.getUserId())) {
            return ResultUtil.getResult(-1, "用户名不可以重复" + user.getUsername());
        }
        shiroUserService.updateUser(user);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 删除用户的方法
     *
     * @param user
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.USER_DELETE,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroUserConstants.DELETE)
    @ResponseBody
    public JSONObject deleteUser(@RequestBody User user) {
        shiroUserService.deleteUser(user);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 获取用户列表带搜索，带分页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = {
            PermissionConstants.USER_SEARCH,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.GET, value = ShiroUserConstants.SEARCH)
    @ResponseBody
    public PaginationResult<User> searchUser(HttpServletRequest request) throws Exception {
        return shiroUserService.getUserPaginatedList(
                Long.parseLong(request.getParameter("currentPageNo")),
                Long.parseLong(request.getParameter("pageSize")), request.getParameter("searchValue"), appKey);
    }

    /**
     * 获取用户列表不带搜索，带分页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = {
            PermissionConstants.USER_VIEW,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.GET, value = ShiroUserConstants.LIST)
    @ResponseBody
    public PaginationResult<User> getUserList(HttpServletRequest request) throws Exception {

        String search = null;
        return shiroUserService.getUserPaginatedList(
                Long.parseLong(request.getParameter("currentPageNo")),
                Long.parseLong(request.getParameter("pageSize")), search, appKey);
    }

    /**
     * 密码初始化
     *
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.USER_RESETPSW,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroUserConstants.PSWINIT)
    @ResponseBody
    public JSONObject passwordInit(@RequestBody UserModel user) {

        user.setPassword(GlobalConstants.INIT_PSW);
        shiroUserService.passwordRest(user);

        Session session = SecurityUtils.getSubject().getSession();
        // 如果管理员 对用户自己重置密码 则删除用户session 重新登录
        if (user.getUserId().equals(session.getAttribute("userid").toString())) {
            sessionDAO.delete(session);
        }
        Boolean isLogin = false;
        for (Session sess : sessionDAO.getActiveSessions()) {
            if (user.getUserId().equals(sess.getAttribute("userid").toString())) {
                sessionDAO.delete(sess);
                isLogin = true;
                new Subject.Builder().sessionId(sess.getId())
                        .buildSubject().logout();
                break;
            }
        }

        userRealm.clearAllCache(user.getUserId());

        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE,
                GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 密码重置
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = ShiroUserConstants.PSWRESET)
    @ResponseBody
    public JSONObject passwordRest(@RequestBody UserModel user) {

        if (!shiroUserService.certificatePSW(user.getUserId(), user.getOldPassword())) {
            return ResultUtil.getResult(-1, "原密码错误，修改密码失败。");
        }
        Session session = SecurityUtils.getSubject().getSession();
        //   只有 用户本人可以 修改密码 则删除用户session 重新登录
        if (user.getUserId().equals(session.getAttribute("userid").toString())) {
            shiroUserService.passwordRest(user);
            sessionDAO.delete(session);
        }
        userRealm.clearAllCache(user.getUserId());

        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE,
                GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 判断用户名是否存在
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroUserConstants.USERNAME_EXISTS)
    @ResponseBody
    public JSONObject findUserNameIfExist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        User user = shiroUserService.ifExistUser(request.getParameter("username"), appKey);
        JSONObject res = new JSONObject();
        res.put("exists", user != null);
        return res;
    }

    /**
     * 根据用户id验证密码，验证通过则pswMismatch为false，否则为true
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = ShiroUserConstants.PSWCERT)
    @ResponseBody
    public JSONObject passwordCertification(HttpServletRequest request, @RequestBody UserModel user) {
        JSONObject res = new JSONObject();
        res.put("pswMismatch",
                !shiroUserService.certificatePSW(user.getUserId(), user.getOldPassword()));
        return res;
    }

}
