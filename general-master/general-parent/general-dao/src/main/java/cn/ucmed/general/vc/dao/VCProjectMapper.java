package cn.ucmed.general.vc.dao;

import cn.ucmed.general.vc.model.VCProject;
import cn.ucmed.general.vc.model.VCProjectModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("vcProjectMapper")
public interface VCProjectMapper extends Mapper<VCProject> {

    @Select({
            "select count(1) from vc_project where deletion_state='0'",
            " and (#{search,jdbcType=VARCHAR} is null",
            " or (project_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%') ",
            "     or hospital_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%')))"})
    Long countAll(@Param("search") String search);

    @Select({
            "select",
            "vc_project_id, project_name, hospital_name, createdby, createdon, modifiedby, modifiedon, deletion_state, ",
            "description",
            "from vc_project",
            "where deletion_state='0'",
            " and (#{search,jdbcType=VARCHAR} is null",
            " or (project_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%') or hospital_name like CONCAT(CONCAT('%', #{search,jdbcType=VARCHAR}),'%')))",
            " order by createdon",
            "limit #{start,jdbcType=BIGINT}, #{offset,jdbcType=BIGINT}"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectModel> selectPaginated(@Param("start") Long start,
                                         @Param("offset") Long offset, @Param("search") String search);

    @Select({
            "select",
            "vc_project_id, project_name, hospital_name, createdby, createdon, modifiedby, modifiedon, deletion_state, ",
            "description", "from vc_project",
            "where project_name = #{projectName,jdbcType=VARCHAR} and deletion_state='0'"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    VCProject findByProjectName(String projectName);

    @Select({
            "select",
            "vc_project_id, project_name, hospital_name, createdby, createdon, modifiedby, modifiedon, deletion_state, ",
            "description", "from vc_project",
            "where vc_project_id = #{projectId,jdbcType=VARCHAR} and deletion_state='0'"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    VCProjectModel findByProjectId(String projectId);

    @Select({
            "select count(*) ",
            "from vc_project",
            "where project_name like CONCAT(CONCAT('%', #{keyword,jdbcType=VARCHAR}),'%') and deletion_state='0'"})
    Long conutByKeyword(String keyword);

    @Select({
            "select",
            "vc_project_id, project_name, hospital_name, createdby, createdon, modifiedby, modifiedon, deletion_state, ",
            "description",
            "from vc_project",
            "where project_name like CONCAT(CONCAT('%', #{keyword,jdbcType=VARCHAR}),'%') and deletion_state='0' ",
            "limit #{start,jdbcType=BIGINT}, #{offset,jdbcType=BIGINT}"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProject> selectPaginatedByKeyword(@Param("start") Long start,
                                             @Param("offset") Long offset, @Param("keyword") String keyword);


    @Select({
            "SELECT DISTINCT hospital_name FROM vc_project where deletion_state='0'"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectModel> loadHospitalList();


    @Select({
            "select",
            "vc_project_id, project_name, hospital_name, createdby, createdon, modifiedby, modifiedon, deletion_state, ",
            "description",
            "from vc_project",
            "where deletion_state='0'"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectModel> loadProjectList();


    @Select({
            "select",
            "vc_project_id, project_name, hospital_name, createdby, createdon, modifiedby, modifiedon, deletion_state, ",
            "description",
            "FROM",
            "	vc_project",
            "WHERE",
            "	hospital_name = #{hospitalName,jdbcType=VARCHAR}",
            "AND deletion_state = '0'"})
    @Results({
            @Result(column = "vc_project_id", property = "vcProjectId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hospital_name", property = "hospitalName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdby", property = "createdby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "createdon", property = "createdon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifiedby", property = "modifiedby", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modifiedon", property = "modifiedon", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "deletion_state", property = "deletionState", jdbcType = JdbcType.CHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)})
    List<VCProjectModel> loadProjectListByHospitalName(@Param("hospitalName") String hospitalName);

}
