package cn.ucmed.general.vc.dao;

import cn.ucmed.general.vc.model.VCSoftwareVersion;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Repository("vcSoftwareVersionMapper")
public interface VCSoftwareVersionMapper extends Mapper<VCSoftwareVersion> {

    @Select({
            "SELECT count(1)",
            "FROM vc_software_version vsv",
            "WHERE vsv.deletion_state = '0'"})
    Long count();

    @Select({
            "SELECT count(1)",
            "FROM vc_software_version vsv",
            "WHERE vsv.vc_project_software_id=#{softwareId,jdbcType=VARCHAR} AND vsv.deletion_state = '0'"})
    Long counts(String softwareId);

    @Select({
            "SELECT                                                                                             ",
            "	version.*, IFNULL(                                                                              ",
            "		(                                                                                           ",
            "			SELECT                                                                                  ",
            "				app_version_number                                                                  ",
            "			FROM                                                                                    ",
            "				vc_software_version                                                                 ",
            "			WHERE                                                                                   ",
            "				vc_software_version.vc_software_version_id = version.current_max_app                ",
            "		),                                                                                          ",
            "		version.app_version_number                                                                  ",
            "	) AS current_max_app_version,                                                                   ",
            "	IFNULL(                                                                                         ",
            "		(                                                                                           ",
            "			SELECT                                                                                  ",
            "				zip_version_number                                                                  ",
            "			FROM                                                                                    ",
            "				vc_software_version                                                                 ",
            "			WHERE                                                                                   ",
            "				vc_software_version.vc_software_version_id = version.current_max_zip                ",
            "		),                                                                                          ",
            "		version.zip_version_number                                                                  ",
            "	) AS current_max_zip_version                                                                    ",
            "FROM                                                                                               ",
            "	(                                                                                               ",
            "		SELECT                                                                                      ",
            "			vp.project_name,                                                                        ",
            "			vps.software_name,                                                                      ",
            "			vps.common_name,                                                                        ",
            "			vps.software_type,                                                                      ",
            "			vps.vc_project_id,                                                                      ",
            "			vps.deletion_state,                                                                     ",
            "			vsv.vc_software_version_id,                                                             ",
            "			vsv.vc_project_software_id,                                                             ",
            "			vsv.version_number,                                                                     ",
            "			vsv.app_version_number,                                                                 ",
            "			vsv.release_time,                                                                       ",
            "			vsv.app_download_url,                                                                   ",
            "			vsv.app_force_update,                                                                   ",
            "			vsv.zip_force_update,                                                                   ",
            "			vsv.zip_version_number,                                                                 ",
            "			vsv.zip_desc,                                                                           ",
            "			vsv.app_desc,                                                                           ",
            "			vsv.zip_download_url,                                                                   ",
            "			vsv.is_off_shelves,                                                                     ",
            "			vsv.current_max_app,                                                                    ",
            "			vsv.current_max_zip,                                                                    ",
            "			vsv.minimum_support_system_version,                                                     ",
            "			vsv.createdby,                                                                          ",
            "			vsv.createdon,                                                                          ",
            "			vsv.modifiedby,                                                                         ",
            "			vsv.modifiedon,                                                                         ",
            "			vsv.is_zip,                                                                             ",
            "			vsv.description                                                                         ",
            "		FROM                                                                                        ",
            "			vc_software_version vsv                                                                 ",
            "		LEFT JOIN vc_project_software vps ON vps.vc_project_software_id = vsv.vc_project_software_id",
            "		LEFT JOIN vc_project vp ON vps.vc_project_id = vp.vc_project_id                             ",
            "		WHERE                                                                                       ",
            "			vsv.vc_project_software_id = #{softwareId,jdbcType=VARCHAR}                             ",
            "		AND vsv.deletion_state = '0'                                                                ",
            "		ORDER BY                                                                                    ",
            "			vsv.release_time DESC                                                                   ",
            "		LIMIT #{start,jdbcType=BIGINT}, #{offset,jdbcType=BIGINT}                                   ",
            "	) version                                                                                       "})
    @Results({
            @Result(column = "vc_software_version_id", property = "vcSoftwareVersionId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version_number", property = "versionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "release_time", property = "releaseTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_force_update", property = "appForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "zip_force_update", property = "zipForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_off_shelves", property = "isOffShelves", jdbcType = JdbcType.CHAR),
            @Result(column = "current_max_app", property = "currentMaxApp", jdbcType = JdbcType.VARCHAR),
            @Result(column = "current_max_zip", property = "currentMaxZip", jdbcType = JdbcType.VARCHAR),
            @Result(column = "minimum_support_system_version", property = "minimunSupportSystemVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "is_zip", property = "isZip", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "current_max_app_version", property = "currentMaxAppVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "current_max_zip_version", property = "currentMaxZipVersion", jdbcType = JdbcType.VARCHAR)
    })
    List<VCSoftwareVersionModel> selectPaginateds(@Param("start") Long start,
                                                  @Param("offset") Long offset, @Param("softwareId") String softwareId);

    @Select({
            "select vps.common_name,",
            "vsv.vc_software_version_id, vsv.vc_project_software_id, vsv.version_number, vsv.app_version_number, ",
            "vsv.release_time, vsv.app_download_url, vsv.app_force_update, vsv.zip_version_number, vsv.zip_desc, ",
            "vsv.app_desc, vsv.zip_download_url, vsv.is_off_shelves, vsv.createdby, vsv.createdon, vsv.modifiedby, ",
            "vsv.current_max_app,vsv.current_max_zip,vsv.modifiedon, vsv.deletion_state, vsv.is_zip,vsv.description",
            ",vsv.minimum_support_system_version FROM vc_software_version as vsv LEFT JOIN vc_project_software as vps",
            "on vsv.vc_project_software_id=vps.vc_project_software_id ",
            " WHERE  vsv.is_zip!='1' and vsv.release_time<=#{releaseTime,jdbcType=VARCHAR} and vsv.deletion_state='0' and vsv.is_off_shelves='0' ORDER BY release_time DESC"
    })
    @Results({
            @Result(column = "vc_software_version_id", property = "vcSoftwareVersionId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version_number", property = "versionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "release_time", property = "releaseTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_force_update", property = "appForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_off_shelves", property = "isOffShelves", jdbcType = JdbcType.CHAR),
            @Result(column = "current_max_app", property = "currentMaxApp", jdbcType = JdbcType.VARCHAR),
            @Result(column = "current_max_zip", property = "currentMaxZip", jdbcType = JdbcType.VARCHAR),
            @Result(column = "minimum_support_system_version", property = "minimunSupportSystemVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    List<VCSoftwareVersionModel> selectNotShelvesVersionList(
            @Param("releaseTime") String releaseTime);

    @Select({
            "SELECT                                                                                    ",
            "vps.common_name,                                                                           ",
            "vsv.vc_software_version_id,",
            "vsv.vc_project_software_id,",
            "vsv.version_number,",
            "vsv.app_version_number,",
            "vsv.release_time,",
            "vsv.app_download_url,",
            "vsv.app_force_update,",
            "vsv.zip_version_number,",
            "vsv.zip_desc,",
            "vsv.app_desc,",
            "vsv.zip_download_url,",
            "vsv.is_off_shelves,",
            "vsv.createdby,",
            "vsv.createdon,",
            "vsv.modifiedby,",
            "vsv.current_max_app,",
            "vsv.current_max_zip,",
            "vsv.modifiedon,",
            "vsv.deletion_state,",
            "vsv.is_zip,",
            "vsv.description",
            "FROM",
            "vc_software_version AS vsv",
            "LEFT JOIN vc_project_software AS vps ON vsv.vc_project_software_id = vps.vc_project_software_id",
            "WHERE",
            "vsv.is_zip != '1'",
            "AND vsv.vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}",
            " AND vsv.is_off_shelves = '1'",
            " AND vsv.app_version_number = (",
            "	SELECT",
            "		MAX(app_version_number)",
            "	FROM",
            "		vc_software_version",
            "	WHERE",
            "		deletion_state = '0'",
            "	AND is_off_shelves = '1'",
            "	AND is_zip != '1'",
            "	AND app_version_number != #{appVersionNumber,jdbcType=VARCHAR}",
            "	AND vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR})"
    })
    @Results({
            @Result(column = "vc_software_version_id", property = "vcSoftwareVersionId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version_number", property = "versionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "release_time", property = "releaseTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_force_update", property = "appForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_off_shelves", property = "isOffShelves", jdbcType = JdbcType.CHAR),
            @Result(column = "current_max_app", property = "currentMaxApp", jdbcType = JdbcType.VARCHAR),
            @Result(column = "current_max_zip", property = "currentMaxZip", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    VCSoftwareVersionModel selectSecondVersion(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId,
            @Param("appVersionNumber") String appVersionNumber);

    @Select({
            "SELECT MAX(app_version_number) FROM vc_software_version WHERE deletion_state='0' and is_off_shelves='1' and is_zip!='1' and vc_project_software_id=#{vcProjectSoftwareId,jdbcType=VARCHAR}"
    })
    String selectShelvesMaxVersionBySoftwareId(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);

    @Select({
            "select vps.common_name,",
            "vsv.vc_software_version_id, vsv.vc_project_software_id, vsv.version_number, vsv.app_version_number, ",
            "vsv.release_time, vsv.app_download_url, vsv.app_force_update, vsv.zip_version_number, vsv.zip_desc, ",
            "vsv.app_desc, vsv.zip_download_url, vsv.is_off_shelves, vsv.createdby, vsv.createdon, vsv.modifiedby, ",
            "vsv.modifiedon, vsv.deletion_state, vsv.is_zip,vsv.description",
            "FROM vc_software_version as vsv LEFT JOIN vc_project_software as vps",
            "on vsv.vc_project_software_id=vps.vc_project_software_id and vsv.vc_project_software_id=#{vcProjectSoftwareId,jdbcType=VARCHAR} ",
            "where vsv.is_zip!='1' and vsv.app_version_number=(SELECT MAX(app_version_number) FROM vc_software_version WHERE deletion_state='0' and is_off_shelves='1' and is_zip!='1' and vc_project_software_id=#{vcProjectSoftwareId,jdbcType=VARCHAR})"
    })
    @Results({
            @Result(column = "vc_software_version_id", property = "vcSoftwareVersionId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version_number", property = "versionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "release_time", property = "releaseTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_force_update", property = "appForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_off_shelves", property = "isOffShelves", jdbcType = JdbcType.CHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    VCSoftwareVersionModel selectShelvesMaxAppVersion(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);

    /**
     * @param vcProjectSoftwareId 软件ID
     * @Description 根据软件ID获取当前已上架的最新的app版本和zip版本
     */
    @Select({
            "SELECT                                                                                    ",
            "	(                                                                                      ",
            "		SELECT                                                                             ",
            "			vc_software_version_id                                                         ",
            "		FROM                                                                               ",
            "			vc_software_version                                                            ",
            "		WHERE                                                                              ",
            "			zip_version_number = (                                                         ",
            "				SELECT                                                                     ",
            "					IFNULL(                                                                ",
            "						max(vZip.zip_version_number),                                      ",
            "						NULL                                                               ",
            "					)                                                                      ",
            "				FROM                                                                       ",
            "					vc_software_version AS vZip                                            ",
            "				WHERE                                                                      ",
            "					vZip.vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}  ",
            "				AND vZip.deletion_state = '0'                                              ",
            "				AND vZip.is_off_shelves != '2'                                             ",
            "				AND vZip.is_zip != '0'                                                     ",
            "			)   AND vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}       ",
            "	) AS current_max_zip,                                                                  ",
            "	(                                                                                      ",
            "		SELECT                                                                             ",
            "			vc_software_version_id                                                         ",
            "		FROM                                                                               ",
            "			vc_software_version                                                            ",
            "		WHERE                                                                              ",
            "			app_version_number = (                                                         ",
            "				SELECT                                                                     ",
            "					IFNULL(                                                                ",
            "						max(vApp.app_version_number),                                      ",
            "						NULL                                                               ",
            "					)                                                                      ",
            "				FROM                                                                       ",
            "					vc_software_version AS vApp                                            ",
            "				WHERE                                                                      ",
            "					vApp.vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}  ",
            "				AND vApp.deletion_state = '0'                                              ",
            "				AND vApp.is_off_shelves != '2'                                             ",
            "				AND vApp.is_zip != '1'                                                     ",
            "			)   AND vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}       ",
            "	) AS current_max_app                                                                   "})
    @Results({
            @Result(column = "current_max_app", property = "currentMaxApp", jdbcType = JdbcType.VARCHAR),
            @Result(column = "current_max_zip", property = "currentMaxZip", jdbcType = JdbcType.VARCHAR)})
    VCSoftwareVersionModel selectMaxVersion(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);

    @Select({
            "select",
            "app_version_number, app_download_url, app_force_update, app_desc,modifiedon",
            ",minimum_support_system_version from vc_software_version",
            "where vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}",
            "and deletion_state = '0' and is_off_shelves = '1' and release_time <= NOW()",
            "ORDER BY CONVERT(REPLACE(app_version_number,'.',''),UNSIGNED) DESC limit 1"
    })
    @Results({
            @Result(column = "app_version_number", property = "appVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_download_url", property = "appDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_force_update", property = "appForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "app_desc", property = "appDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "minimum_support_system_version", property = "minimunSupportSystemVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP)
    })
    VCSoftwareVersionModel selectLatestAppBySoftWareId(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);

    @Select({
            "select zip_version_number,zip_desc,zip_download_url,zip_force_update,modifiedon",
            "from vc_software_version",
            "where vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}",
            "and deletion_state = '0' and is_off_shelves = '1' and release_time <= NOW()",
            "ORDER BY CONVERT(REPLACE(zip_version_number,'.',''),UNSIGNED) DESC limit 1"
    })
    @Results({
            @Result(column = "zip_version_number", property = "zipVersionNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_desc", property = "zipDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_download_url", property = "zipDownloadUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "zip_force_update", property = "zipForceUpdate", jdbcType = JdbcType.CHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP)
    })
    VCSoftwareVersionModel selectLatestZipBySoftWareId(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);


    @Select({
            "SELECT app_version_number",
            "FROM vc_software_version",
            "WHERE vc_project_software_id=#{vcProjectSoftwareId,jdbcType=VARCHAR}",
            " AND deletion_state = '0'",
            "ORDER BY CONVERT(REPLACE(app_version_number,'.',''),UNSIGNED) DESC",
            "LIMIT 1;"
    })
    String selectMaxAppVersionNumber(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);


    @Select({
            "SELECT zip_version_number",
            "FROM vc_software_version",
            "WHERE vc_project_software_id=#{vcProjectSoftwareId,jdbcType=VARCHAR}",
            " AND deletion_state = '0'",
            "ORDER BY CONVERT(REPLACE(zip_version_number,'.',''),UNSIGNED) DESC",
            "LIMIT 1;"
    })
    String selectMaxZipVersionNumber(
            @Param("vcProjectSoftwareId") String vcProjectSoftwareId);

    /**
     * 查询版本号是否存在，包含下架的版本
     *
     * @param vcProjectSoftwareId 软件表主键
     * @param zipVersionNum       zip版本号
     * @param appVersionNum       app版本号
     * @return 1：存在，null：不存在
     */
    @Select({
            "SELECT 1 FROM vc_software_version ",
            "WHERE (if(zip_version_number = '',FALSE,zip_version_number = #{zipVersionNum,jdbcType=VARCHAR}) ",
            " OR if(app_version_number = '',FALSE,app_version_number = #{appVersionNum,jdbcType=VARCHAR}))",
            " AND deletion_state = '0' AND vc_project_software_id = #{vcProjectSoftwareId,jdbcType=VARCHAR}",
            " AND vc_software_version_id != #{vcSoftwareVersionId,jdbcType=VARCHAR}",
            "LIMIT 1;"
    })
    String selectByVersionNumber(@Param("vcProjectSoftwareId") String vcProjectSoftwareId, @Param("vcSoftwareVersionId") String vcSoftwareVersionId,
                                 @Param("zipVersionNum") String zipVersionNum,
                                 @Param("appVersionNum") String appVersionNum);
}
