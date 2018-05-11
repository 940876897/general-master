package cn.ucmed.general.shiro.dao;

import cn.ucmed.general.shiro.model.User;
import cn.ucmed.general.shiro.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface UserMapper extends Mapper<User> {

    @Select({
            "select r.r_roles_id from u_users u, r_roles r,ur_users_roles ur ",
            "where u.u_users_id=#{userId,jdbcType=VARCHAR} and u.u_users_id=ur.u_users_id ",
            "and r.r_roles_id=ur.r_roles_id and u.deletion_state='0' and r.deletion_state='0'",
            "and ur.deletion_state='0'"
    })
    Set<String> findRoles(String userId);

    @Select({
            "select permission from u_users u, r_roles r, p_permissions p, ",
            "ur_users_roles ur, rp_roles_permissions rp ",
            "where u.u_users_id=#{userId,jdbcType=VARCHAR} ",
            "and u.u_users_id=ur.u_users_id and r.r_roles_id=ur.r_roles_id ",
            "and r.r_roles_id=rp.r_roles_id ",
            "and p.p_permissions_id=rp.p_permissions_id ",
            "and u.deletion_state='0' and r.deletion_state='0' and p.deletion_state='0'",
            "and ur.deletion_state='0' and rp.deletion_state='0'"
    })
    Set<String> findPermissions(String userId);

    Long countAll(@Param("search") String search,
                  @Param("list") List<String> lstSuperUser,
                  @Param("appKey") String appKey);

    List<User> selectPaginated(@Param("start") Long start, @Param("offset") Long offset,
                               @Param("search") String search,
                               @Param("list") List<String> lstSuperUser,
                               @Param("appKey") String appKey);

    List<User> loadList(@Param("list") List<String> lstSuperUser,
                        @Param("appKey") String appKey);

    @Select({"select",
            "u_users_id, username, password, salt,surname,email,mobile,app_key,createdon,modifiedon", "from u_users",
            "where deletion_state='0' and app_key = #{appKey,jdbcType=VARCHAR} ",
            " and username = #{username,jdbcType=VARCHAR}"})
    @Results({
            @Result(column = "u_users_id", property = "userId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "surname", property = "surname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_key", property = "appKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP)})
    UserModel findByUsername(@Param("username") String username, @Param("appKey") String appKey);

    @Select({"SELECT",
            " u.u_users_id, u.username, u.password, u.salt,u.surname,",
            " u.email,u.mobile,u.app_key,u.createdon,u.modifiedon",
            " FROM u_users u,ur_users_roles ur",
            " WHERE u.u_users_id = ur.u_users_id",
            " AND ur.r_roles_id = #{roleId,jdbcType=VARCHAR}",
            " AND ur.deletion_state = '0'",
            " AND u.deletion_state = '0'",
            " AND u.app_key = #{appKey,jdbcType=VARCHAR}"})
    @Results({
            @Result(column = "u_users_id", property = "userId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "surname", property = "surname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mobile", property = "mobile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_key", property = "appKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP)})
    List<User> getProjectOwnerList(@Param("roleId") String roleId, @Param("appKey") String appKey);


}