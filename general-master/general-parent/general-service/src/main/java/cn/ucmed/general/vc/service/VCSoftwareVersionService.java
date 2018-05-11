package cn.ucmed.general.vc.service;


import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import cn.ucmed.common.util.PaginationResult;
import net.sf.json.JSONObject;

import java.util.List;

public interface VCSoftwareVersionService {

    /**
     * @param pageNo
     * @param pageSize
     * @param softwareId
     * @return
     * @Description 获取软件下的版本列表
     */
    public PaginationResult<VCSoftwareVersionModel> getPaginatedList(
            Long pageNo, Long pageSize, String softwareId);

    /**
     * @param vcSoftwareVersion
     * @throws Exception
     * @Description 添加软件版本
     */
    public int add(VCSoftwareVersionModel model) throws Exception;

    /**
     * @param VCSoftwareVersionModel
     * @Description 修改软件版本
     */
    public int update(VCSoftwareVersionModel model) throws Exception;

    /**
     * @param softwareId
     * @return
     * @throws Exception
     * @Description 下架软件版本
     */
    public void offShelves(VCSoftwareVersionModel model) throws Exception;

    /**
     * @param versionId
     * @return
     * @Description 根据版本ID获取版本
     */
    public VCSoftwareVersionModel findByVersionId(String versionId);

    /**
     * @Description 获取没有上架的软件版本列表
     */
    public List<VCSoftwareVersionModel> selectNotShelvesVersionList(String releaseTime);

    /**
     * @param softWareId 软件表主键
     * @return
     * @Description 获取最新的app及zip版本信息，将最新的app和zip信息合成一条记录返回
     */
    public VCSoftwareVersionModel getLatestVersionBySoftWareId(String softWareId);

    /**
     * @param softWareId 软件表主键
     * @return
     * @Description 获取最大的app及zip版本信息
     */
    public JSONObject getMaxVersionBySoftWareId(String softWareId);

}
