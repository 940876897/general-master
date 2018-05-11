package cn.ucmed.general.shiro.dao;

import cn.ucmed.general.shiro.model.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    Long countAll(@Param("search") String search,
                  @Param("list") List<String> lstSuperRole,
                  @Param("appKey") String appKey);

    List<Role> selectPaginated(@Param("start") Long start,
                               @Param("offset") Long offset,
                               @Param("search") String search,
                               @Param("list") List<String> lstSuperRole,
                               @Param("appKey") String appKey);

    List<Role> loadList(@Param("list") List<String> lstSuperRole,
                        @Param("appKey") String appKey);
}