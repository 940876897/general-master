package cn.ucmed.general.shiro.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "rp_roles_permissions")
public class RolePermission extends BaseEntity{
    /**
     * 主键
     */
    @Id
    @Column(name = "rp_roles_permissions_id")
    private String rolePermissionId;

    /**
     * 外键角色id
     */
    @Column(name = "r_roles_id")
    private String rRolesId;

    /**
     * 外键角色id
     */
    @Column(name = "p_permissions_id")
    private String pPermissionsId;

    /**
     * 获取主键
     *
     * @return rp_roles_permissions_id - 主键
     */
    public String getRolePermissionId() {
        return rolePermissionId;
    }

    /**
     * 设置主键
     *
     * @param rolePermissionId 主键
     */
    public void setRolePermissionId(String rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    /**
     * 获取外键角色id
     *
     * @return r_roles_id - 外键角色id
     */
    public String getrRolesId() {
        return rRolesId;
    }

    /**
     * 设置外键角色id
     *
     * @param rRolesId 外键角色id
     */
    public void setrRolesId(String rRolesId) {
        this.rRolesId = rRolesId;
    }

    /**
     * 获取外键角色id
     *
     * @return p_permissions_id - 外键角色id
     */
    public String getpPermissionsId() {
        return pPermissionsId;
    }

    /**
     * 设置外键角色id
     *
     * @param pPermissionsId 外键角色id
     */
    public void setpPermissionsId(String pPermissionsId) {
        this.pPermissionsId = pPermissionsId;
    }
}