package cn.ucmed.general.security;

import cn.ucmed.common.constants.PermissionConstants;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.common.util.ResultUtil;
import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.shiro.model.Permission;
import cn.ucmed.general.shiro.model.Role;
import cn.ucmed.general.shiro.model.RoleModel;
import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.service.PermissionService;
import cn.ucmed.general.shiro.service.RoleService;
import cn.ucmed.general.web.constants.ShiroRoleConstants;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ��ɫ������
 * Created by ucmed on 2015/12/30.
 */

@Controller
public class RoleController {
    @Autowired
    private RoleService shiroRoleService;

    @Autowired
    private PermissionService shiroPermissionService;

    @Value("${app.key}")
    private String appKey;

    /**
     * ������ɫ�ķ���
     *
     * @param role
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.ROLE_CREATE,
            PermissionConstants.ROLE_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroRoleConstants.ADD)
    @ResponseBody
    public JSONObject addRole(@RequestBody Role role) {
        Role roledb = shiroRoleService.ifExistRole(role.getRoleName(), appKey);
        //如果 修改的用户名 与其他用户重名
        if (roledb != null && StringUtils.isNotBlank(roledb.getroleId()))
            {
            return ResultUtil.getResult(-1, "角色名已经存在" + role.getRoleName());
        }
        shiroRoleService.createRole(role, appKey);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * �޸Ľ�ɫ�ķ���
     *
     * @param role
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.ROLE_UPDATE,
            PermissionConstants.ROLE_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.PUT, value = ShiroRoleConstants.UPDATE)
    @ResponseBody
    public JSONObject updateRole(@RequestBody Role role) {

        Role roledb = shiroRoleService.findRoleDupRoleNameNotRoleId(role.getRoleName(), role.getroleId(), appKey);
        //如果 修改的用户名 与其他用户重名
        if (roledb != null && StringUtils.isNotBlank(roledb.getroleId())
                && StringUtils.isNotBlank(role.getroleId())
                && !role.getroleId().equalsIgnoreCase(roledb.getroleId())) {
            return ResultUtil.getResult(-1, "角色名不可以重复" + role.getRoleName());
        }
        shiroRoleService.updateRole(role);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * ɾ����ɫ�ķ���
     *
     * @param role
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.ROLE_DELETE,
            PermissionConstants.ROLE_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroRoleConstants.DELETE)
    @ResponseBody
    public JSONObject deleteRole(@RequestBody Role role) {
        shiroRoleService.deleteRole(role);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 搜索角色信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = {
            PermissionConstants.ROLE_SEARCH,
            PermissionConstants.ROLE_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.SEARCH)
    @ResponseBody
    public PaginationResult<Role> searchRole(HttpServletRequest request) throws Exception {
        return shiroRoleService.getRolePaginatedList(
                Long.parseLong(request.getParameter("currentPageNo")),
                Long.parseLong(request.getParameter("pageSize")), request.getParameter("searchValue"), appKey);
    }

    /**
     * 获取角色列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = {
            PermissionConstants.ROLE_VIEW,
            PermissionConstants.ROLE_ALL,
            PermissionConstants.USER_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.ROLELIST_PAGE)
    @ResponseBody
    public PaginationResult<Role> getRoleList(HttpServletRequest request) {

        String search = null;
        return shiroRoleService.getRolePaginatedList(
                Long.parseLong(request.getParameter("currentPageNo")),
                Long.parseLong(request.getParameter("pageSize")), search, appKey);
    }


    /**
     * ��ȡ��ɫ�б���ҳ
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.LIST)
    @ResponseBody
    public List<Role> loadRoleList() {
        return shiroRoleService.loadRoleList(appKey);
    }


    /**
     * ��ȡ�û����еĽ�ɫ�б�
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.ROLELIST_USERID)
    @ResponseBody
    public List<Role> roleListToUserId(HttpServletRequest request) {
        return shiroRoleService.roleListToUserId(request.getParameter("userid"));
    }


    /**
     * ��ȡ�����ڽ�ɫ���û��б�
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.UNSELECTEDUSER)
    @ResponseBody
    public List<User> loadUnselectedUserToRole(HttpServletRequest request) {
        return shiroRoleService.loadUnselectedUserToRole(request.getParameter("roleid"), appKey);
    }


    /**
     * ��ȡ���ڸý�ɫ���û��б�
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.SELECTEDUSER)
    @ResponseBody
    public List<User> loadSelectedUserToRole(HttpServletRequest request) {
        return shiroRoleService.loadSelectedUserToRole(request.getParameter("roleid"));
    }


    /**
     * �û�����
     *
     * @param role
     * @return
     */
    @RequiresPermissions(value = {
            PermissionConstants.ROLE_USER,
            PermissionConstants.ROLE_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroRoleConstants.USERMANAGE)
    @ResponseBody
    public JSONObject userManage(@RequestBody RoleModel role) {
        shiroRoleService.userManage(role);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.SELECTEDPERMISSION)
    @ResponseBody
    public List<Permission> loadSelectedPermission(HttpServletRequest request) {
        return shiroPermissionService.loadSelectedPermission(request.getParameter("roleid"));
    }


    @RequiresPermissions(value = {
            PermissionConstants.ROLE_PERMISSION,
            PermissionConstants.ROLE_ALL},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = ShiroRoleConstants.UPDATEPERMISSION)
    @ResponseBody
    public JSONObject roleUpdatePermission(@RequestBody RoleModel role) {
        shiroPermissionService.roleUpdatePermission(role);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 判断角色名是否存�?
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = ShiroRoleConstants.ROLENAME_EXISTS)
    @ResponseBody
    public JSONObject findRoleNameIfExist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Role role = shiroRoleService.ifExistRole(request.getParameter("rolename"), appKey);
        JSONObject res = new JSONObject();
        res.put("exists", role != null);
        return res;
    }
}
