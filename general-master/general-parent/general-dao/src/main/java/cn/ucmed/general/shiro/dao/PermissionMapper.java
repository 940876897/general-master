package cn.ucmed.general.shiro.dao;

import cn.ucmed.general.shiro.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface PermissionMapper extends Mapper<Permission> {

    @Select({
            "SELECT",
            "	pp.*",
            "FROM",
            "	rp_roles_permissions rrp",
            "INNER JOIN p_permissions pp ON pp.p_permissions_id = rrp.p_permissions_id",
            "WHERE",
            "	rrp.r_roles_id = #{roleId,jdbcType=VARCHAR}",
            "AND rrp.deletion_state = '0'",
            "AND pp.deletion_state = '0'"})
    @Results({
            @Result(column = "p_permissions_id", property = "permissionId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "permission", property = "permission", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_key", property = "appKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<Permission> loadSelectedPermission(@Param("roleId") String roleId);

}