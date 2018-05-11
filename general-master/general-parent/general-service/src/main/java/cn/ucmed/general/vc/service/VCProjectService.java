package cn.ucmed.general.vc.service;

import cn.ucmed.general.vc.model.VCProjectModel;
import cn.ucmed.common.util.PaginationResult;

import java.util.List;

public interface VCProjectService {

    /**
     * @param vcProject
     * @Description 获取项目列表
     */
    public PaginationResult<VCProjectModel> getPaginatedList(Long pageNo,
                                                             Long pageSize, String searchValue);

    /**
     * @param projectName 项目名称
     * @return 项目
     * @Desription 获取项目根据项目名称
     */
    public VCProjectModel findByProjectName(String projectName);

    /**
     * @param projectId 项目Id
     * @return 项目
     * @Desription 根据项目Id获取项目
     */
    public VCProjectModel findByProjectId(String projectId);

    /**
     * @param vcProject
     * @Description 添加新项目
     */
    public int add(VCProjectModel model);

    /**
     * @param vcProject
     * @Description 修改项目
     */
    public int update(VCProjectModel model);

    /**
     * 搜索项目信息
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationResult<VCProjectModel> search(String keyword, Long pageNo,
                                            Long pageSize);

    /**
     * 获取所有医院
     *
     * @return
     */
    List<VCProjectModel> loadHospitalList();


    /**
     * 获取所有的项目列表
     *
     * @return
     */
    List<VCProjectModel> loadProjectList();


    /**
     * 获取医院下的项目列表
     *
     * @param hospitalName
     * @return
     */
    List<VCProjectModel> loadProjectListByHospitalName(String hospitalName);


    /**
     * 获取医院列表（内含对应的项目列表）
     *
     * @return
     */
    List<VCProjectModel> loadHospitalProjectList();
}