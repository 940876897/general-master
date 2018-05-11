package cn.ucmed.general.shiro.service;


import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;
import cn.ucmed.common.util.PaginationResult;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param user
     */
    User createUser(UserModel user, String appKey);

    /**
     * 检查用户是否在数据库中是否存在
     *
     * @param userName
     */
    User ifExistUser(String userName, String appKey);


    /**
     * 检查非这个userId ，用户是否在数据库中是否存在
     *
     * @param userName
     */
    User findUserDupUserNameNotUserId(String userName, String userId, String appKey);


    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(UserModel user);

    /**
     * 删除用户
     *
     * @param user
     */
    void deleteUser(User user);


    /**
     * 获取用户列表
     *
     * @param pageNo
     * @param pageSize
     * @param searchValue
     * @return
     */
    PaginationResult<User> getUserPaginatedList(Long pageNo,
                                                Long pageSize, String searchValue, String appKey);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    UserModel findByUsername(String username, String appKey);


    /**
     * 根据用户Id查找用户
     *
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * 根据用户Id查找其角色
     *
     * @param userId
     * @return
     */
    Set<String> findRoles(String userId);

    /**
     * 根据用户Id查找其权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermissions(String userId);


    int passwordRest(UserModel user);

    /**
     * 验证用户id对应的密码是否正确
     *
     * @param userId   用户id
     * @param password 用于验证的密码
     * @return 验证通过返回true，否则返回false
     */
    Boolean certificatePSW(String userId, String password);

    /**
     * 获取所有项目负责人列表
     *
     * @param roleId 项目负责人角色主键
     * @param appKey 区分应用
     * @return
     */
    List<User> getProjectOwnerList(String roleId, String appKey);
}
