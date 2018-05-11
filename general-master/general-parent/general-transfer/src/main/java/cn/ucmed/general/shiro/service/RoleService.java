package cn.ucmed.general.shiro.service;


import cn.ucmed.general.shiro.model.Role;
import cn.ucmed.general.shiro.model.RoleModel;
import cn.ucmed.general.shiro.model.User;
import cn.ucmed.common.util.PaginationResult;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface RoleService {
    /**
     * 创建角色的方法
     *
     * @param role
     * @return
     */
    Role createRole(Role role, String appKey);


    /**
     * 判断角色名是否存在的方法
     *
     * @param roleName
     */
    Role ifExistRole(String roleName, String appKey);


    /**
     * 判断是否 包含
     * @param roleName
     * @param roleId
     * @param appKey
     * @return
     */
    public Role findRoleDupRoleNameNotRoleId(String roleName,String roleId, String appKey);



    /**
     * 修改角色的方法
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色的方法
     *
     * @param role
     */
    void deleteRole(Role role);

    /**
     * 获取用户列表
     *
     * @param pageNo
     * @param pageSize
     * @param searchValue
     * @return
     */
    PaginationResult<Role> getRolePaginatedList(Long pageNo, Long pageSize,
                                                String searchValue, String appKey);

    /**
     * 获取角色列表不分页
     *
     * @return
     */
    List<Role> loadRoleList(String appKey);


    Integer batchAddRoleToUser(String userId, Set<String> roleIds);


    /**
     * 获取用户的角色列表
     *
     * @return
     */
    List<Role> roleListToUserId(String userId);


    /**
     * 获取属于角色的用户列表
     *
     * @param roleId 角色ID
     * @return
     */
    List<User> loadSelectedUserToRole(String roleId);


    /**
     * 获取不属于角色的用户列表
     *
     * @param roleId 角色ID
     * @return
     */
    List<User> loadUnselectedUserToRole(String roleId, String appKey);


    /**
     * 角色的用户管理
     *
     * @param role
     */
    void userManage(RoleModel role);

    Role selectRoleByRoleId(String roleId);

}
