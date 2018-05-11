package cn.ucmed.general.shiro.service;


import cn.ucmed.general.shiro.model.Permission;
import cn.ucmed.general.shiro.model.RoleModel;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionService {

    Permission createPermission(Permission permission, String appKey);

    void deletePermission(Long permissionId);

    /**
     * 根据角色ID获取权限列表
     *
     * @param roleId
     * @return
     */
    List<Permission> loadSelectedPermission(String roleId);


    /**
     * 角色对应权限的修�?
     *
     * @param role
     */
    void roleUpdatePermission(RoleModel role);
}
