package cn.ucmed.general.shiro.dao;

import cn.ucmed.general.shiro.model.Permission;
import cn.ucmed.general.shiro.model.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RolePermissionMapper extends Mapper<RolePermission> {

    @Update({
            "update rp_roles_permissions",
            "set deletion_state = '1'",
            "where r_roles_id = #{roleId,jdbcType=VARCHAR}"
    })
    int deleteByRoleId(@Param("roleId") String roleId);


    /**
     * 根据角色ID批量删除和权限的关系
     * @param list
     * @param roleId
     * @return
     */
    Integer batchDeleteByRoleId(@Param("list") List<Permission> list, @Param("roleId") String roleId);


    /**
     * 批量新增
     * @param list
     * @return
     */
    Integer batchAdd(List<RolePermission> list);
}