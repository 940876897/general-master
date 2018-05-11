package cn.ucmed.general.shiro.service;


import cn.ucmed.common.rubikexception.BusinessException;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.general.common.ConstansBuss;
import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.shiro.dao.RoleMapper;
import cn.ucmed.general.shiro.dao.RolePermissionMapper;
import cn.ucmed.general.shiro.dao.UserMapper;
import cn.ucmed.general.shiro.dao.UserRoleMapper;
import cn.ucmed.general.shiro.model.Role;
import cn.ucmed.general.shiro.model.RoleModel;
import cn.ucmed.general.shiro.model.User;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Role createRole(Role role, String appKey) {

        String roleName = role.getRoleName();

        if (ifExistRole(roleName, appKey) != null) {
            throw new BusinessException(511, "角色名已存在");
        }
        role.setroleId(UUID.randomUUID().toString());
        role.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        role.setCreatedon(new Date());
        role.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        role.setModifiedon(new Date());
        role.setAppKey(appKey);
        roleMapper.insertSelective(role);
        return role;
    }

    @Override
    public Role ifExistRole(String roleName, String appKey) {
        Role model = new Role();
        model.setRoleName(roleName);
        model.setAppKey(appKey);
        model.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
        return roleMapper.selectOne(model);
    }

    @Override
    public Role findRoleDupRoleNameNotRoleId(String roleName,String roleId, String appKey) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo(ConstansBuss.ROLENAME, roleName)
                .andEqualTo(ConstansBuss.APPKEY, appKey)
                .andNotEqualTo(ConstansBuss.ROLEID, roleId)
                .andEqualTo(ConstansBuss.DELETIONSTATE, DefaultDBValue.CREATE_DELETION_STATE);//这里给出的条件查询为 userName = userName
        List<Role> roleList = roleMapper.selectByExample(example);
        Role RoleRet = null;
        if (CollectionUtils.isNotEmpty(roleList)) {
            RoleRet = roleList.get(0);
        }
        return RoleRet;
    }


    /**
     * 修改角色
     *
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        role.setModifiedon(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional
    public void deleteRole(Role role) {

        role.setDeletionState(DefaultDBValue.DELETION_STATE_DEL);
        role.setModifiedon(new Date());
        roleMapper.updateByPrimaryKeySelective(role);

        userRoleMapper.deleteByRoleId(role.getroleId());

        rolePermissionMapper.deleteByRoleId(role.getroleId());
    }

    /**
     * 获取角色列表
     *
     * @param pageNo
     * @param pageSize
     * @param searchValue
     * @return
     */
    @Override
    public PaginationResult<Role> getRolePaginatedList(Long pageNo, Long pageSize,
                                                       String searchValue, String appKey) {

        PaginationResult<Role> result = new PaginationResult<Role>();
        List<Role> modelList = new ArrayList<Role>();
        Long count = roleMapper.countAll(searchValue, GlobalConstants.SUPER_ROLES, appKey);
        result.setPageCount(count % pageSize > 0 ? count / pageSize + 1 : count
                / pageSize);
        result.setTotalCount(count);
        List<Role> roleModelList = roleMapper.selectPaginated(
                (pageNo - 1L) * pageSize, pageSize, searchValue,
                GlobalConstants.SUPER_ROLES, appKey);

        if (CollectionUtils.isNotEmpty(roleModelList)) {
            for (Role data : roleModelList) {
                modelList.add(data);
            }
        }
        result.setList(modelList);
        return result;
    }


    /**
     * 获取角色列表不分页
     *
     * @return
     */
    @Override
    public List<Role> loadRoleList(String appKey) {
        return roleMapper.loadList(GlobalConstants.SUPER_ROLES, appKey);
    }

    @Override
    public Integer batchAddRoleToUser(String userId, Set<String> roleIds) {

        if (null != roleIds && !roleIds.isEmpty()) {
            List<UserRole> list = new ArrayList<UserRole>();
            for (String roleId : roleIds) {
                UserRole ur = new UserRole();
                ur.setUserRoleId(UUID.randomUUID().toString());
                ur.setuUsersId(userId);
                ur.setrRolesId(roleId);
                ur.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                ur.setCreatedon(new Date());
                ur.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                ur.setModifiedon(new Date());
                ur.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
                list.add(ur);
            }

            return userRoleMapper.batchAddRoleToUser(list);
        }
        return 0;
    }

    @Override
    public List<Role> roleListToUserId(String userId) {
        return userRoleMapper.roleListToUserId(userId);
    }

    @Override
    public List<User> loadSelectedUserToRole(String roleId) {
        return userRoleMapper.loadSelectedUserToRole(roleId);
    }

    @Override
    public List<User> loadUnselectedUserToRole(String roleId, String appKey) {

        //获取全部用户列表
        List<User> allList = userMapper.loadList(GlobalConstants.SUPER_USERS, appKey);

        //获取已经属于该角色的用户列表
        List<User> selectedList = loadSelectedUserToRole(roleId);

        List<User> list = new ArrayList<User>();
        for (User allUser : allList) {

            boolean exists = false;

            for (User selectedUser : selectedList) {
                if (allUser.getUserId().equals(selectedUser.getUserId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                list.add(allUser);
            }
        }
        return list;
    }


    @Transactional
    @Override
    public void userManage(RoleModel role) {

        //获取角色已经具有的用户列表
        List<User> oldUsers = userRoleMapper.loadSelectedUserToRole(role.getroleId());
        //角色新的用户列表
        List<User> newUsers = role.getUserList();

        //获取角色要解除的用户列表
        List<User> olds = new ArrayList<User>();
        for (User oldUser : oldUsers) {
            String oldUserId = oldUser.getUserId();
            boolean boo = true;
            for (User newUser : newUsers) {
                String newUserId = newUser.getUserId();
                if (oldUserId.equals(newUserId)) {
                    boo = false;
                    break;
                }
            }
            if (boo) {
                olds.add(oldUser);
            }
        }


        //获取角色要新增关系的用户列表
        List<UserRole> news = new ArrayList<UserRole>();
        for (User newUser : newUsers) {
            String newUserId = newUser.getUserId();
            boolean boo = true;
            for (User oldUser : oldUsers) {
                String oldUserId = oldUser.getUserId();
                if (newUserId.equals(oldUserId)) {
                    boo = false;
                    break;
                }
            }
            if (boo) {
                UserRole ur = new UserRole();
                ur.setUserRoleId(UUID.randomUUID().toString());
                ur.setuUsersId(newUserId);
                ur.setrRolesId(role.getroleId());
                ur.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                ur.setCreatedon(new Date());
                ur.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
                ur.setModifiedon(new Date());
                ur.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
                news.add(ur);
            }
        }


        //批量删除该角色对应的用户关系
        if (CollectionUtils.isNotEmpty(olds)) {
            userRoleMapper.batchDeleteByRoleId(olds, role.getroleId());
        }

        //批量新增该角色对应的用户关系
        if (CollectionUtils.isNotEmpty(news)) {
            userRoleMapper.batchAddRoleToUser(news);
        }


        olds.clear();
        news.clear();
    }

    @Override
    public Role selectRoleByRoleId(String roleId) {
        Role role = new Role();
        role.setroleId(roleId);
        return roleMapper.selectOne(role);
    }
}
