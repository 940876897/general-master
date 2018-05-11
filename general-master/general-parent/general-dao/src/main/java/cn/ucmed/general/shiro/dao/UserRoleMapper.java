package cn.ucmed.general.shiro.dao;

import cn.ucmed.general.shiro.model.Role;
import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {


    /**
     * ����Ϊһ���û�������ɫ
     *
     * @param roles
     * @return
     */
    Integer batchAddRoleToUser(List<UserRole> roles);


    @Select({
            "SELECT",
            "	uur.ur_users_roles_id,",
            "	uur.u_users_id,",
            "	rr.r_roles_id,",
            "	rr.role_name,",
            "	rr.app_key,",
            "	rr.createdby,",
            "	rr.createdon,",
            "	rr.modifiedby,",
            "	rr.modifiedon,",
            "	rr.deletion_state",
            "FROM",
            "	ur_users_roles uur",
            "INNER JOIN r_roles rr ON rr.r_roles_id = uur.r_roles_id",
            "WHERE",
            "	uur.u_users_id = #{userId,jdbcType=VARCHAR}",
            "AND uur.deletion_state = '0'",
            "AND rr.deletion_state = '0'"})
    @Results({
            @Result(column = "r_roles_id", property = "roleId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "role_name", property = "roleName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_key", property = "appKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<Role> roleListToUserId(@Param("userId") String userId);


    /**
     * ��ȡ���ڽ�ɫ���û��б�
     *
     * @param roleId ��ɫID
     * @return
     */
    @Select({
            "SELECT",
            "	uu.*",
            "FROM",
            "	ur_users_roles uur",
            "INNER JOIN u_users uu ON uu.u_users_id = uur.u_users_id",
            "WHERE",
            "	uur.r_roles_id = #{roleId,jdbcType=VARCHAR}",
            "AND uur.deletion_state = '0'",
            "AND uu.deletion_state = '0'"})
    @Results({
            @Result(column = "u_users_id", property = "userId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "surname", property = "surname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_key", property = "appKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<User> loadSelectedUserToRole(@Param("roleId") String roleId);


    @Update({
            "update ur_users_roles",
            "set deletion_state = '1'",
            "where r_roles_id = #{roleId,jdbcType=VARCHAR}"
    })
    int deleteByRoleId(@Param("roleId") String roleId);


    @Update({
            "update ur_users_roles",
            "set deletion_state = '1'",
            "where u_users_id = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByUserId(@Param("userId") String userId);

    /**
     * ����ɾ�����ݽ�ɫID
     *
     * @param list
     * @param roleId
     * @return
     */
    Integer batchDeleteByRoleId(@Param("list") List<User> list, @Param("roleId") String roleId);


    /**
     * ����ɾ�������û�ID
     *
     * @param list
     * @param userId
     * @return
     */
    Integer batchDeleteByUserId(@Param("list") List<Role> list, @Param("userId") String userId);
}