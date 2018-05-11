package cn.ucmed.general.shiro.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "p_permissions")
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7567890112345830533L;

    /**
     * 主键
     */
    @Id
    @Column(name = "p_permissions_id")
    private String permissionId;

    /**
     * 权限字符串
     */
    private String permission;

    /**
     * 根据app_key验证不同APP的权限
     */
    @Column(name = "app_key")
    private String appKey;

    /**
     * 获取主键
     *
     * @return p_permissions_id - 主键
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * 设置主键
     *
     * @param permissionId 主键
     */
    public void setpermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取权限字符串
     *
     * @return permission - 权限字符串
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限字符串
     *
     * @param permission 权限字符串
     */
    public void setPermission(String permission) {
        this.permission = permission;
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