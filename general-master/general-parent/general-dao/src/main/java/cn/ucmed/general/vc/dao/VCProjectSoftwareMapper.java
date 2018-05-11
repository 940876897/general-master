package cn.ucmed.general.vc.dao;

import cn.ucmed.general.vc.model.VCProjectSoftware;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Repository("vcProjectSoftwareMapper")
public interface VCProjectSoftwareMapper extends Mapper<VCProjectSoftware> {

    /**
     * @param vcProjectId 项目表主键
     * @return 软件数量
     * @Description 获取项目下的软件数量
     */
    @Select({"select count(1) from vc_project_software",
            " where vc_project_id=#{vcProjectId,jdbcType=VARCHAR}",
            " and deletion_state='0'"})
    Long countAllByProjectId(String vcProjectId);

    @Select({
            "SELECT",
            "	COUNT(1)",
            "FROM",
            "	vc_project vp",
            "INNER JOIN vc_project_software vps ON vps.vc_project_id = vp.vc_project_id",
            "WHERE",
            "	vps.deletion_state = '0'",
            "AND vp.deletion_state = '0'"})
    Long countAll();


    @Select({
            "SELECT",
            "	COUNT(1)",
            "FROM",
            "vc_project vp",
            "INNER JOIN vc_project_software vps ON vps.vc_project_id = vp.vc_project_id",
            "WHERE",
            "	vps.deletion_state = '0'",
            "AND vp.deletion_state = '0'",
            "AND (#{search,jdbcType=VARCHAR} is null",
            " or (project_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%') ",
            " or hospital_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%')",
            " or software_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%')))"})
    Long countSeachAll(@Param("search") String search);


    /**
     * @param vcProjectId 项目主键
     * @param start       分页开始
     * @param offset      分页结束
     * @return 软件列表
     * @Description 根据项目主键获取软件列表
     */
    @Select({
            "select                                                                 ",
            "vc_project_software_id, vc_project_id, software_name, common_name, software_type, ",
            "software_id, createdby, createdon, modifiedby, modifiedon, deletion_state, description",
            "from vc_project_software vps                                           ",
            "where vps.vc_project_id= #{vcProjectId,jdbcType=VARCHAR}               ",
            "and vps.deletion_state='0'                                             ",
            "order by vps.createdon                                                  ",
            "limit #{start,jdbcType=BIGINT}, #{offset,jdbcType=BIGINT}"})
    @Results({
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_name", property = "softwareName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "software_type", property = "softwareType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectSoftwareModel> getProjectSoftwareList(
            @Param("vcProjectId") String vcProjectId,
            @Param("start") Long start, @Param("offset") Long offset);


    /**
     * 获取软件列表
     *
     * @param start
     * @param offset
     * @return
     */
    @Select({
            "SELECT",
            "	vp.hospital_name,",
            "	vp.project_name,",
            "	vps.vc_project_software_id,",
            "	vps.vc_project_id,",
            "	vps.software_name,",
            "	vps.common_name,",
            "	vps.software_type,",
            "	vps.software_id,",
            "	vps.createdby,",
            "	vps.createdon,",
            "	vps.modifiedby,",
            "	vps.modifiedon,",
            "	vps.deletion_state,",
            "	vps.description",
            "FROM",
            "	vc_project vp",
            "INNER JOIN vc_project_software vps ON vps.vc_project_id = vp.vc_project_id",
            "WHERE",
            "	vps.deletion_state = '0'",
            "AND vp.deletion_state = '0'",
            "order by vps.createdon DESC",
            "limit #{start,jdbcType=BIGINT}, #{offset,jdbcType=BIGINT}"})
    @Results({
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_name", property = "softwareName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "software_type", property = "softwareType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectSoftwareModel> loadSoftwareList(@Param("start") Long start, @Param("offset") Long offset);


    @Select({
            "SELECT",
            "	vp.hospital_name,",
            "	vp.project_name,",
            "	vps.vc_project_software_id,",
            "	vps.vc_project_id,",
            "	vps.software_name,",
            "	vps.common_name,",
            "	vps.software_type,",
            "	vps.software_id,",
            "	vps.createdby,",
            "	vps.createdon,",
            "	vps.modifiedby,",
            "	vps.modifiedon,",
            "	vps.deletion_state,",
            "	vps.description",
            "FROM",
            "	vc_project vp",
            "INNER JOIN vc_project_software vps ON vps.vc_project_id = vp.vc_project_id",
            "WHERE",
            "	vps.deletion_state = '0'",
            "AND vp.deletion_state = '0'",
            "AND (",
            "	#{search,jdbcType=VARCHAR} IS NULL",
            "	OR (",
            "		project_name LIKE CONCAT(",
            "			CONCAT('%', #{search,jdbcType=VARCHAR}),",
            "			'%'",
            "		)",
            "		OR hospital_name LIKE CONCAT(",
            "			CONCAT('%', #{search,jdbcType=VARCHAR}),",
            "			'%'",
            "		)",
            "		OR software_name LIKE CONCAT(",
            "			CONCAT('%', #{search,jdbcType=VARCHAR}),",
            "			'%'",
            "		)",
            "	)",
            ")",
            "order by vps.createdon DESC",
            "limit #{start,jdbcType=BIGINT}, #{offset,jdbcType=BIGINT}"})
    @Results({
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_name", property = "softwareName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "software_type", property = "softwareType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectSoftwareModel> seachSoftwareList(@Param("search") String search, @Param("start") Long start, @Param("offset") Long offset);


    List<VCSoftwareVersionModel> getSoftwareVersionList(
            String vcProjectSoftwareId);

    /**
     * 新增软件，软件ID事物自增；
     *
     * @param record
     * @return
     */
    @Insert({
            "BEGIN;",
            "SET @i = 0;",
            "SELECT",
            "	last_key_value INTO @i",
            "FROM",
            "	`last_key`",
            "WHERE",
            "	last_key_id = '87fd378f-4261-11e5-a6af-0050569b08a8' FOR UPDATE;",

            "INSERT INTO vc_project_software (`vc_project_software_id`,`vc_project_id`,`software_name`,",
            "	`common_name`,`software_type`,`software_id`,`createdby`,`createdon`,`modifiedby`,`modifiedon`,",
            "	`deletion_state`,`description`)",
            "VALUES",
            "	(",
            "		#{vcProjectSoftwareId,jdbcType=VARCHAR}, #{vcProjectId,jdbcType=VARCHAR}, ",
            "		#{softwareName,jdbcType=VARCHAR}, #{commonName,jdbcType=VARCHAR}, #{softwareType,jdbcType=VARCHAR},@i + 1, ",
            "		#{createdby,jdbcType=VARCHAR} ,#{createdon,jdbcType=TIMESTAMP}, #{modifiedby,jdbcType=VARCHAR}, ",
            "		#{modifiedon,jdbcType=TIMESTAMP}, #{deletionState,jdbcType=CHAR}, #{description,jdbcType=VARCHAR}); ",

            "	UPDATE `last_key` SET last_key_value = @i + 1",
            "	WHERE last_key_id = '87fd378f-4261-11e5-a6af-0050569b08a8';", "COMMIT;"})
    int insertProjectSoftware(VCProjectSoftware record);

    @Select({
            "select                                                         ",
            "   *,                                                          ",
            "   (select MAX(vcsv.app_version_number) as                     ",
            "   app_version_number                                          ",
            "   from vc_software_version vcsv                               ",
            "   where                                                       ",
            "   vcsv.is_off_shelves = 1                                     ",
            "   and vcsv.app_force_update = 'F'                             ",
            "   and                                                         ",
            "   vcsv.vc_project_software_id = vcps.vc_project_software_id)as",
            "   latestForceUpdateVersion                                    ",
            "from vc_project_software vcps                                  ",
            "where                                                          ",
            "deletion_state='0';                                            ",
    })
    @Results({
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_name", property = "softwareName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_type", property = "softwareType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "latestForceUpdateVersion", property = "latestForceUpdateVersion", jdbcType = JdbcType.VARCHAR)
    })
    List<VCProjectSoftwareModel> getAllSoftwares();


    /**
     * 根据通用名获取软件
     *
     * @param commonName
     * @return
     */
    @Select({
            "select",
            "vc_project_software_id, vc_project_id, software_name, common_name, software_type, ",
            "software_id, createdby, createdon, modifiedby, modifiedon, deletion_state, description",
            "from vc_project_software",
            "where common_name = #{commonName,jdbcType=VARCHAR} and deletion_state='0'",
            "limit 1"
    })
    @Results({
            @Result(column = "vc_project_software_id", property = "vcProjectSoftwareId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_name", property = "softwareName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "common_name", property = "commonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_type", property = "softwareType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "software_id", property = "softwareId", jdbcType = JdbcType.INTEGER),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    VCProjectSoftwareModel selectByCommonName(@Param("commonName") String commonName);

}
