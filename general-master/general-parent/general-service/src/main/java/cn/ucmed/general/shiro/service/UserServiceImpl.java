package cn.ucmed.general.shiro.service;


import cn.ucmed.common.rubikexception.BusinessException;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.general.common.ConstansBuss;
import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.shiro.dao.UserMapper;
import cn.ucmed.general.shiro.dao.UserRoleMapper;
import cn.ucmed.general.shiro.model.Role;
import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;
import cn.ucmed.general.shiro.model.UserRole;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService shiroRoleService;

    private PasswordHelper passwordHelper;

    public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    /**
     * 创建用户
     *
     * @param user
     */
    @Transactional
    @Override
    public User createUser(UserModel user, String appKey) {

        String userName = user.getUsername();
        if (ifExistUser(userName, appKey) != null) {
            throw new BusinessException(511, "用户名已存在");
        }
        user.setUserId(UUID.randomUUID().toString());

        //设置初始密码88851766
        user.setPassword(GlobalConstants.INIT_PSW);
        user.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        user.setCreatedon(new Date());
        user.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        user.setModifiedon(new Date());
        user.setAppKey(appKey);

        //加密密码
        passwordHelper.encryptPassword(user);

        //新增用户信息
        userMapper.insertSelective(user);

        //新增用户对应的角色
        shiroRoleService.batchAddRoleToUser(user.getUserId(), user.getRoles());

        return user;
    }

    /**
     * 判断用户名在数据库中是否存在的方法
     *
     * @param userName
     * @return
     */
    @Override
    public User ifExistUser(String userName, String appKey) {
        User model = new User();
        model.setUsername(userName);
        model.setAppKey(appKey);
        model.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
        return userMapper.selectOne(model);
    }

    /**
     *
     * @param  String userName,
     * @param String userId ,
     * @param String  appKey,
     * @return User
     */
    @Override
    public User findUserDupUserNameNotUserId(String userName, String userId, String appKey) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(ConstansBuss.USERNAME, userName)
                .andEqualTo(ConstansBuss.APPKEY, appKey)
                .andNotEqualTo(ConstansBuss.USERID, userId)
                .andEqualTo(ConstansBuss.DELETIONSTATE, DefaultDBValue.CREATE_DELETION_STATE);//这里给出的条件查询为 userName = userName
        List<User> userList = userMapper.selectByExample(example);
        User userRet = null;
        if (CollectionUtils.isNotEmpty(userList)) {
            userRet = userList.get(0);
        }
        return userRet;
    }

    /*
    * @param userId
    * @param newPassword
    */
    @Override
    public void changePassword(Long userId, String newPassword) {

        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户
     *
     * @param user
     */
    @Transactional
    @Override
    public void deleteUser(User user) {

        user.setDeletionState(DefaultDBValue.DELETION_STATE_DEL);
        user.setModifiedon(new Date());
        userMapper.updateByPrimaryKeySelective(user);

        userRoleMapper.deleteByUserId(user.getUserId());
    }

    private void updateUserRoles(UserModel user) {

        List<Role> oldRoles = shiroRoleService.roleListToUserId(user.getUserId());//旧的角色
        Set<String> newRoles = user.getRoles();//新选中的角色

        //获取要删除的用户对应的角色关系
        List<Role> olds = new ArrayList<Role>();
        for (Role role : oldRoles) {
            String oldRoleId = role.getroleId();
            boolean boo = true;

            if (!CollectionUtils.isEmpty(newRoles)) {
                for (String roleId : newRoles) {
                    if (oldRoleId.equals(roleId)) {
                        boo = false;
                        break;
                    }
                }
            }
            if (boo) {
                olds.add(role);
            }
        }

        //获取要新增的用户对应的角色关系
        List<UserRole> news = new ArrayList<UserRole>();
        if (!CollectionUtils.isEmpty(newRoles)) {
            for (String roleId : newRoles) {
                boolean boo = true;
                for (Role role : oldRoles) {
                    String oldRoleId = role.getroleId();
                    if (roleId.equals(oldRoleId)) {
                        boo = false;
                        break;
                    }
                }
                if (boo) {
                    UserRole ur = new UserRole();
                    ur.setUserRoleId(UUID.randomUUID().toString());
                    ur.setuUsersId(user.getUserId());
                    ur.setrRolesId(roleId);
                    ur.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                    ur.setCreatedon(new Date());
                    ur.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                    ur.setModifiedon(new Date());
                    ur.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
                    news.add(ur);
                }
            }
        }

        //批量删除对应的用户关系
        if (CollectionUtils.isNotEmpty(olds)) {
            userRoleMapper.batchDeleteByUserId(olds, user.getUserId());
        }

        //批量新增对应的用户关系
        if (CollectionUtils.isNotEmpty(news)) {
            userRoleMapper.batchAddRoleToUser(news);
        }

        olds.clear();
        news.clear();
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Transactional
    @Override
    public void updateUser(UserModel user) {

        userMapper.updateByPrimaryKeySelective(user);
        updateUserRoles(user);
    }

    /**
     * 获取用户列表
     *
     * @param pageNo
     * @param pageSize
     * @param searchValue
     * @return
     */
    @Override
    public PaginationResult<User> getUserPaginatedList(Long pageNo, Long pageSize,
                                                       String searchValue, String appKey) {

        PaginationResult<User> result = new PaginationResult<User>();
        List<User> modelList = new ArrayList<User>();
        Long count = userMapper.countAll(searchValue, GlobalConstants.SUPER_USERS, appKey);
        result.setPageCount(count % pageSize > 0 ? count / pageSize + 1 : count
                / pageSize);
        result.setTotalCount(count);
        List<User> userModelList = userMapper.selectPaginated(
                (pageNo - 1L) * pageSize, pageSize,
                searchValue, GlobalConstants.SUPER_USERS, appKey);

        if (CollectionUtils.isNotEmpty(userModelList)) {
            for (User data : userModelList) {
                modelList.add(data);
            }
        }
        result.setList(modelList);
        return result;
    }


    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public UserModel findByUsername(String username, String appKey) {
        return userMapper.findByUsername(username, appKey);
    }

    public User findByUserId(String userId) {
        User model = new User();
        model.setUserId(userId);
        model.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
        return userMapper.selectOne(model);
    }

    /**
     * 根据用户Id查找其角色
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findRoles(String userId) {
        return userMapper.findRoles(userId);
    }

    /**
     * 根据用户id查找其权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermissions(String userId) {
        return userMapper.findPermissions(userId);
    }

    @Override
    public int passwordRest(UserModel user) {

        User db = findByUserId(user.getUserId());
        user.setUserId(db.getUserId());
        user.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        user.setModifiedon(new Date());
        user.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);

        //加密密码
        passwordHelper.encryptPassword(user);

        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Boolean certificatePSW(String userId, String password) {

        User dbUser = findByUserId(userId);
        String dbPassword = dbUser.getPassword();

        dbUser.setPassword(password);
        passwordHelper.encryptPasswordWithOldSalt(dbUser);

        return dbPassword.equals(dbUser.getPassword());
    }

    @Override
    public List<User> getProjectOwnerList(String roleId, String appKey) {
        return userMapper.getProjectOwnerList(roleId, appKey);
    }
}
