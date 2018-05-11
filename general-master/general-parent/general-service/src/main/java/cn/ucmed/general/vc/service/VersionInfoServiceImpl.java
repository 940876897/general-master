package cn.ucmed.general.vc.service;

import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.vc.dao.VCProjectSoftwareMapper;
import cn.ucmed.general.vc.dao.VersionInfoMapper;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import cn.ucmed.general.vc.model.VersionInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class VersionInfoServiceImpl implements VersionInfoService {

    private static final Logger LOG = Logger
            .getLogger(VersionInfoServiceImpl.class);

    private VersionInfoMapper versionInfoMapper;

    private VCProjectSoftwareMapper vcProjectSoftwareMapper;

    private VCSoftwareVersionService vcSoftwareVersionService;

    public void setVcProjectSoftwareMapper(VCProjectSoftwareMapper vcProjectSoftwareMapper) {
        this.vcProjectSoftwareMapper = vcProjectSoftwareMapper;
    }

    public void setVcSoftwareVersionService(VCSoftwareVersionService vcSoftwareVersionService) {
        this.vcSoftwareVersionService = vcSoftwareVersionService;
    }

    public void setVersionInfoMapper(VersionInfoMapper versionInfoMapper) {
        this.versionInfoMapper = versionInfoMapper;
    }

    @Override
    @Transactional
    public void updateToLatestVersion() {

        List<VersionInfo> clientVersionList = versionInfoMapper.selectAll();

        VCSoftwareVersionModel serverVersion = null;
        VCProjectSoftwareModel serverSoftware = null;
        VersionInfo clientVersionInfo = null;

        List<VCProjectSoftwareModel> lstSoftwares = getAllSoftwares();

        for (int i = lstSoftwares.size() - 1; i >= 0; i--) {
            serverSoftware = lstSoftwares.get(i);

            /**
             * 获取每个软件的最新版本数据
             */
            if (serverSoftware.getLatestVersion() == null) {
                LOG.info("The latest version not found under current softWare:"
                        + serverSoftware.getVcProjectSoftwareId() + ","
                        + serverSoftware.getSoftwareName());
                continue;
            }

            serverVersion = serverSoftware.getLatestVersion();

            for (int j = clientVersionList.size() - 1; j >= 0; j--) {
                clientVersionInfo = clientVersionList.get(j);

                /**
                 * 如果软件相同且大版本不相同，则更新
                 */
                if (serverSoftware.getSoftwareId().equals(
                        clientVersionInfo.getSoftwareId())) {

                    if (serverSoftware.getModifiedon().after(new Date(
                            clientVersionInfo.getSoftwareUpdateTime().getTime() - 10 * 60 * 1000))) {
                        setAppClientVersionField(clientVersionInfo, serverVersion);
                        setZipClientVersionField(clientVersionInfo, serverVersion);
                        clientVersionInfo.setMinimunSupportSystemVersion(serverVersion.getMinimunSupportSystemVersion());
                        clientVersionInfo.setSoftwareUpdateTime(serverSoftware.getModifiedon());
                        clientVersionInfo.setLatestForceUpdateVersion(serverSoftware
                                .getLatestForceUpdateVersion());
                        versionInfoMapper.updateByPrimaryKey(clientVersionInfo);
                    }
                    clientVersionList.remove(j);
                    lstSoftwares.remove(i);

                    /**
                     * 当version_info表的软件被更新后，跳出clientVersionList循环；
                     *
                     * TODO 此处可能因为脏数据而产生Bug，脏数据产生原因不明确，结果是version_info对相同的software_id有两条一样的数据
                     *
                     *      通过查询语句可以确定是否有脏数据:
                     *      SELECT software_id, count(1)
                     *      FROM `version_info`
                     *      GROUP BY software_id
                     *      HAVING count(1) > 1;
                     */
                    break;
                }
            }
        }

        /**
         * 删除版本服务器上没有的软件版本数据
         */
        for (VersionInfo del : clientVersionList) {
            versionInfoMapper.deleteByPrimaryKey(del.getVersionInfoId());
        }

        /**
         * 新增版本服务器上新增的软件版本数据
         */
        VersionInfo latestVersion = null;
        for (VCProjectSoftwareModel software : lstSoftwares) {

            serverVersion = software.getLatestVersion();
            if (serverVersion == null) {
                LOG.info("The latest version not found under current softWare:"
                        + serverSoftware.getVcProjectSoftwareId() + ","
                        + serverSoftware.getSoftwareName());
                continue;
            }

            latestVersion = new VersionInfo();
            latestVersion.setSoftwareId(software.getSoftwareId());
            latestVersion.setSoftwareUpdateTime(software.getModifiedon());
            latestVersion.setMinimunSupportSystemVersion(serverVersion.getMinimunSupportSystemVersion());
            setAppClientVersionField(latestVersion, serverVersion);
            setZipClientVersionField(latestVersion, serverVersion);
            setStandardClientVersionField(latestVersion);

            latestVersion.setVersionInfoId(UUID.randomUUID().toString());
            versionInfoMapper.insert(latestVersion);
        }
    }

    private void setAppClientVersionField(VersionInfo clientVersionInfo,
                                          VCSoftwareVersionModel serverVersion) {
        // app
        clientVersionInfo.setAppVersionNumber(serverVersion.getAppVersionNumber());
        clientVersionInfo.setAppDownloadUrl(serverVersion.getAppDownloadUrl());
        clientVersionInfo.setAppDesc(serverVersion.getAppDesc());
    }

    private void setZipClientVersionField(VersionInfo clientVersionInfo,
                                          VCSoftwareVersionModel serverVersion) {
        // zip
        clientVersionInfo.setZipVersionState(serverVersion.getZipForceUpdate());
        clientVersionInfo.setZipVersionNumber(serverVersion.getZipVersionNumber());
        clientVersionInfo.setZipDownloadUrl(serverVersion.getZipDownloadUrl());
        clientVersionInfo.setZipDesc(serverVersion.getZipDesc());
    }

    private void setStandardClientVersionField(VersionInfo clientVersionInfo) {
        clientVersionInfo.setCreatedon(new Date());
        clientVersionInfo.setCreatedby(StringUtils.EMPTY);
        clientVersionInfo.setModifiedon(new Date());
        clientVersionInfo.setModifiedby(StringUtils.EMPTY);
        clientVersionInfo.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
    }

    private List<VCProjectSoftwareModel> getAllSoftwares() {
        List<VCProjectSoftwareModel> lstSoftwares =
                vcProjectSoftwareMapper.getAllSoftwares();
        for (VCProjectSoftwareModel vcProjectSoftwareModel : lstSoftwares) {
            vcProjectSoftwareModel.setLatestVersion(vcSoftwareVersionService
                    .getLatestVersionBySoftWareId(vcProjectSoftwareModel
                            .getVcProjectSoftwareId()));
        }
        return lstSoftwares;
    }

}
