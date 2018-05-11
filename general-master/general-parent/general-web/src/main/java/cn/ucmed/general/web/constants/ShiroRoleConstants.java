package cn.ucmed.general.web.constants;

import cn.ucmed.common.constants.web.BaseURLConstants;

/**
 * Created by Christian on 2016/7/15.
 * Shiro Role URL constants
 */
public class ShiroRoleConstants extends BaseURLConstants {


    public static final String SHIRO_ROLE = AUTH + "/shiroRole";

    public static final String ADD = SHIRO_ROLE + "/addRole" + POSTFIX_JSON;

    public static final String UPDATE = SHIRO_ROLE + "/updateRole" + POSTFIX_JSON;

    public static final String DELETE = SHIRO_ROLE + "/deleteRole" + POSTFIX_JSON;

    public static final String SEARCH = SHIRO_ROLE + "/roleSearch" + POSTFIX_JSON;

    public static final String ROLELIST_PAGE = SHIRO_ROLE + "/roleList" + POSTFIX_JSON;

    public static final String LIST = SHIRO_ROLE + "/list" + POSTFIX_JSON;

    public static final String ROLELIST_USERID = SHIRO_ROLE + "/roleListToUserId" + POSTFIX_JSON;

    public static final String UNSELECTEDUSER = SHIRO_ROLE + "/unselectedUser" + POSTFIX_JSON;

    public static final String SELECTEDUSER = SHIRO_ROLE + "/selectedUser" + POSTFIX_JSON;


    public static final String USERMANAGE = SHIRO_ROLE + "/userManage" + POSTFIX_JSON;

    public static final String SELECTEDPERMISSION = SHIRO_ROLE + "/selectedPermission" + POSTFIX_JSON;

    public static final String UPDATEPERMISSION = SHIRO_ROLE + "/roleUpdatePermission" + POSTFIX_JSON;

    public static final String ROLENAME_EXISTS = SHIRO_ROLE + "/roleNameIfExist" + POSTFIX_JSON;

    private ShiroRoleConstants() {
    }
}
