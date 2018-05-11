package cn.ucmed.general.vc.dao;

import cn.ucmed.general.vc.model.VersionInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import cn.ucmed.general.vc.model.VersionInfoModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Repository("versionInfoMapper")
public interface VersionInfoMapper extends Mapper<VersionInfo> {
    /**
     * @return
     * @Description 查询当前项目的所有版本数据
     */
    @Select({
            "select",
            "version_info_id, software_id, app_version_number, app_download_url,zip_version_state ,software_update_time ",
            "zip_version_number, zip_download_url, app_desc, zip_desc,latest_force_update_version,software_update_time, createdby, createdon, ",
            "modifiedby, modifiedon, deletion_state, description",
            "from version_info",
            "where deletion_state = '0'"})
    @Results({
            @Result(column = "version_info_id", property = "versionInfoId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "latest_force_update_version", property = "latestForceUpdateVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_version_state", property = "zipVersionState", jdbcType = JdbcType.CHAR),
            @Result(column = "software_update_time", property = "softwareUpdateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    List<VersionInfo> selectAll();

    @Select({
            "select",
            "version_info_id, software_id, app_version_number, app_download_url,zip_version_state, software_update_time, ",
            "zip_version_number, zip_download_url, app_desc, zip_desc,latest_force_update_version,software_update_time, createdby, createdon, ",
            "minimum_support_system_version,modifiedby, modifiedon, deletion_state, description",
            "from version_info",
            "where software_id = #{softWareId,jdbcType=VARCHAR} and deletion_state = '0'",
            "limit 1"
    })
    @Results({
            @Result(column = "version_info_id", property = "versionInfoId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "latest_force_update_version", property = "latestForceUpdateVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_version_state", property = "zipVersionState", jdbcType = JdbcType.CHAR),
            @Result(column = "software_update_time", property = "softwareUpdateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "minimum_support_system_version", property = "minimunSupportSystemVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    VersionInfoModel selectBySoftWareId(String softWareId);
}
