package cn.ucmed.general.shiro.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ur_users_roles")
public class UserRole extends BaseEntity{
    /**
     * 主键
     */
    @Id
    @Column(name = "ur_users_roles_id")
    private String userRoleId;

    /**
     * 外键用户id
     */
    @Column(name = "u_users_id")
    private String uUsersId;

    /**
     * 外键角色id
     */
    @Column(name = "r_roles_id")
    private String rRolesId;

    /**
     * 获取主键
     *
     * @return ur_users_roles_id - 主键
     */
    public String getUserRoleId() {
        return userRoleId;
    }

    /**
     * 设置主键
     *
     * @param userRoleId 主键
     */
    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * 获取外键用户id
     *
     * @return u_users_id - 外键用户id
     */
    public String getuUsersId() {
        return uUsersId;
    }

    /**
     * 设置外键用户id
     *
     * @param uUsersId 外键用户id
     */
    public void setuUsersId(String uUsersId) {
        this.uUsersId = uUsersId;
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
}