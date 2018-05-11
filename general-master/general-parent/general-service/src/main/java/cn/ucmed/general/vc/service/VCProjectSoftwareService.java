package cn.ucmed.general.vc.service;

import cn.ucmed.general.vc.model.VCProjectSoftware;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.common.util.PaginationResult;

public interface VCProjectSoftwareService {

    /**
     * @Description 获取项目软件列表
     * @param vcProject
     */
    public PaginationResult<VCProjectSoftwareModel> getProjectSoftwareList(
            String vcProjectId, Long pageNo, Long pageSize);

    /**
     * 获取软件列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PaginationResult<VCProjectSoftwareModel> loadSoftwareList(Long pageNo, Long pageSize);


    /**
     * 搜索软件列表
     * @param seach
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PaginationResult<VCProjectSoftwareModel> seachSoftwareList(String seach,Long pageNo, Long pageSize);

    /**
     * @Description 添加项目软件
     * @param vcProjectSoftware
     */
    public void addProjectSoftware(VCProjectSoftware vcProjectSoftware);

    /**
     * @Description 修改项目软件
     * @param vcProjectSoftware
     */
    public void updateProjectSoftware(VCProjectSoftware vcProjectSoftware);

    /**
     * @Desription 根据软件Id获取软件
     * @param softwareId
     *            软件Id
     * @return 软件
     */
    public VCProjectSoftwareModel findBySoftwareId(String softwareId);


    /**
     * 根据软件通用名获取软件
     * @param commonName
     * @return
     */
    public VCProjectSoftwareModel loadByCommonName(String commonName);

}