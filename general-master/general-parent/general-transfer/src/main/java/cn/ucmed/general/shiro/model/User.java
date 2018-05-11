package cn.ucmed.general.shiro.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "u_users")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7123450116222830533L;

    /**
     * 主键
     */
    @Id
    @Column(name = "u_users_id")
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * 用户身份证号
     */
    private String idCard;

    /**
     * 工号
     */
    private String employeeId;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 密钥
     */
    private String salt;

    /**
     * 姓名
     */
    private String surname;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 根据app_key验证不同APP的权限
     */
    @Column(name = "app_key")
    private String appKey;

    /**
     * 获取主键
     *
     * @return u_users_id - 主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置主键
     *
     * @param userId 主键
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密钥
     *
     * @return salt - 密钥
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密钥
     *
     * @param salt 密钥
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getCredentialsSalt() {
        return userId + salt;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}