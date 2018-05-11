package cn.ucmed.general.shiro.service;


import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.shiro.dao.PermissionMapper;
import cn.ucmed.general.shiro.dao.RolePermissionMapper;
import cn.ucmed.general.shiro.model.Permission;
import cn.ucmed.general.shiro.model.RoleModel;
import cn.ucmed.general.shiro.model.RolePermission;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public Permission createPermission(Permission permission, String appKey) {
        permission.setpermissionId(UUID.randomUUID().toString());
        permissionMapper.insertSelective(permission);
        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public List<Permission> loadSelectedPermission(String roleId) {
        return permissionMapper.loadSelectedPermission(roleId);
    }

    @Override
    public void roleUpdatePermission(RoleModel role) {
        //获取角色对应的权限列�?
        List<Permission> oldList = permissionMapper.loadSelectedPermission(role.getroleId());
        //新的角色对应的权限列�?
        List<String> newList = role.getPermissionList();


        //要删除的角色对应的权限列�?
        List<Permission> olds = new ArrayList<Permission>();
        for (Permission p : oldList) {
            String oldPermission = p.getPermission();
            boolean boo = true;
            for (String newPermission : newList) {
                if (oldPermission.equals(newPermission)) {
                    boo = false;
                    break;
                }
            }
            if (boo) {
                olds.add(p);
            }
        }
        //要新增的角色对应的权限列�?
        List<RolePermission> news = new ArrayList<RolePermission>();
        for (String newPermission : newList) {
            boolean boo = true;
            for (Permission p : oldList) {
                String oldPermission = p.getPermission();
                if (newPermission.equals(oldPermission)) {
                    boo = false;
                    break;
                }
            }
            if (boo) {
                RolePermission rp = new RolePermission();
                rp.setRolePermissionId(UUID.randomUUID().toString());
                rp.setrRolesId(role.getroleId());
                rp.setpPermissionsId(newPermission);
                rp.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                rp.setCreatedon(new Date());
                rp.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                rp.setModifiedon(new Date());
                rp.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
                news.add(rp);
            }
        }
        //批量删除该角色对应的用户关系
        if (CollectionUtils.isNotEmpty(olds)) {
            rolePermissionMapper.batchDeleteByRoleId(olds, role.getroleId());
        }

        //批量新增该角色对应的用户关系
        if (CollectionUtils.isNotEmpty(news)) {
            rolePermissionMapper.batchAdd(news);
        }

        olds.clear();
        news.clear();

    }


}
