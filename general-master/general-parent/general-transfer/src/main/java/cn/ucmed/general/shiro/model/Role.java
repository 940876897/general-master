package cn.ucmed.general.shiro.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "r_roles")
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7567890116222830533L;

    /**
     * 主键
     */
    @Id
    @Column(name = "r_roles_id")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 根据app_key验证不同APP的权限
     */
    @Column(name = "app_key")
    private String appKey;

    /**
     * 获取主键
     *
     * @return r_roles_id - 主键
     */
    public String getroleId() {
        return roleId;
    }

    /**
     * 设置主键
     *
     * @param roleId 主键
     */
    public void setroleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    /**
     * 获取appKey
     *
     * @return appKey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置appKey
     *
     * @param appKey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}