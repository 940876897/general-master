package cn.ucmed.general.web.constants;

import cn.ucmed.common.constants.web.BaseURLConstants;

/**
 * Created by Christian on 2016/7/15.
 * Shiro User URL constants
 */
public class ShiroUserConstants extends BaseURLConstants {

    public static final String SHIRO_USER = AUTH + "/shiroUser";


    public static final String ADD = SHIRO_USER + "/addUser" + POSTFIX_JSON;

    public static final String EDIT = SHIRO_USER + "/editUser" + POSTFIX_JSON;

    public static final String DELETE = SHIRO_USER + "/deleteUser" + POSTFIX_JSON;

    public static final String SEARCH = SHIRO_USER + "/userSearch" + POSTFIX_JSON;

    public static final String LIST = SHIRO_USER + "/userList" + POSTFIX_JSON;

    public static final String PSWINIT = SHIRO_USER + "/passwordInit" + POSTFIX_JSON;

    public static final String PSWRESET = SHIRO_USER + "/passwordReset" + POSTFIX_JSON;

    public static final String USERNAME_EXISTS = SHIRO_USER + "/userNameIfExist" + POSTFIX_JSON;

    public static final String PSWCERT = SHIRO_USER + "/passwordCertificate" + POSTFIX_JSON;

    private ShiroUserConstants() {
    }
}
