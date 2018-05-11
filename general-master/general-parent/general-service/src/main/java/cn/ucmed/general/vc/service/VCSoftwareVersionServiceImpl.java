package cn.ucmed.general.vc.service;

import cn.ucmed.common.orika.OrikaBeanMapper;
import cn.ucmed.common.rubikexception.BusinessException;
import cn.ucmed.common.util.ModelDataObjectUtil;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.common.util.FileUploadConfig;
import cn.ucmed.general.common.util.VersionUtil;
import cn.ucmed.general.vc.dao.VCProjectSoftwareMapper;
import cn.ucmed.general.vc.dao.VCSoftwareVersionMapper;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.general.vc.model.VCSoftwareVersion;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class VCSoftwareVersionServiceImpl implements VCSoftwareVersionService {

    private VCSoftwareVersionMapper vcSoftwareVersionMapper;

    private VCProjectSoftwareMapper vcProjectSoftwareMapper;

    private FileUploadConfig fileUploadConfig;

    private OrikaBeanMapper orikaBeanMapper;

    public void setVcProjectSoftwareMapper(VCProjectSoftwareMapper vcProjectSoftwareMapper) {
        this.vcProjectSoftwareMapper = vcProjectSoftwareMapper;
    }

    public void setVcSoftwareVersionMapper(VCSoftwareVersionMapper vcSoftwareVersionMapper) {
        this.vcSoftwareVersionMapper = vcSoftwareVersionMapper;
    }

    public void setFileUploadConfig(FileUploadConfig fileUploadConfig) {
        this.fileUploadConfig = fileUploadConfig;
    }

    public void setOrikaBeanMapper(OrikaBeanMapper orikaBeanMapper) {
        this.orikaBeanMapper = orikaBeanMapper;
    }

    @Override @Transactional public int add(VCSoftwareVersionModel model) throws Exception {

        String appVersion = model.getAppVersionNumber();
        String zipVersion = model.getZipVersionNumber();

        validateVersionNumber(model);

        /**
         * 版本更新类型: APP_ZIP:appVersion和zipVersion都有值 ; IS_APP:只有appVersion;
         * IS_ZIP:只有zipVersion;
         */
        String versionType = StringUtils.isNotEmpty(appVersion) && StringUtils.isNotEmpty(zipVersion) ?
                DefaultDBValue.APP_ZIP :
                StringUtils.isNotEmpty(appVersion) ? DefaultDBValue.IS_APP : DefaultDBValue.IS_ZIP;

        if(versionType != DefaultDBValue.IS_ZIP) {
            fileCopy(model, versionType);
        } else {
            model.setIsZip(DefaultDBValue.IS_ZIP);
            model.setIsOffShelves(DefaultDBValue.ON_SHELVES);
        }

        VCSoftwareVersionModel maxVersion = vcSoftwareVersionMapper.selectMaxVersion(model.getVcProjectSoftwareId());
        String currentMaxApp = StringUtils.EMPTY;
        String currentMaxZip = StringUtils.EMPTY;

        if(maxVersion != null) {
            currentMaxApp = (StringUtils.isNotBlank(appVersion)) ? currentMaxApp : maxVersion.getCurrentMaxApp();
            currentMaxZip = (StringUtils.isNotBlank(zipVersion)) ? currentMaxZip : maxVersion.getCurrentMaxZip();
        }

        model.setCurrentMaxApp(currentMaxApp);
        model.setCurrentMaxZip(currentMaxZip);
        model.setVcSoftwareVersionId(UUID.randomUUID().toString());

        // Standard fields
        model.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        model.setCreatedon(new Date());
        model.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        model.setModifiedon(new Date());
        model.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);

        VCSoftwareVersion record = ModelDataObjectUtil.model2do(model, VCSoftwareVersion.class);

        updateSoftwareForVersionChange(record.getVcProjectSoftwareId());
        return vcSoftwareVersionMapper.insertSelective(record);
    }

    private void fileCopy(VCSoftwareVersionModel model, String isZip) throws Exception {
        if(model.getReleaseTime().after(new Date())) {
            model.setIsOffShelves(DefaultDBValue.NOT_ON_SHELVES);
            model.setIsZip(isZip);
        } else {
            // 版本包拷贝到通用名中
            VCProjectSoftwareModel vm = orikaBeanMapper
                    .map(vcProjectSoftwareMapper.selectByPrimaryKey(model.getVcProjectSoftwareId()),
                            VCProjectSoftwareModel.class);
            if(null != vm) {
                saveCommonFile(vm.getCommonName(), model.getAppVersionNumber());
            }
            model.setIsOffShelves(DefaultDBValue.ON_SHELVES);
            model.setIsZip(isZip);
        }
    }

    @Override @Transactional public int update(VCSoftwareVersionModel model) throws Exception {

        String appVersion = model.getAppVersionNumber();
        String zipVersion = model.getZipVersionNumber();

        validateVersionNumber(model);

        /**
         * 版本更新类型: APP_ZIP:appVersion和zipVersion都有值 ; IS_APP:只有appVersion;
         * IS_ZIP:只有zipVersion;
         */
        String versionType = StringUtils.isNotEmpty(appVersion) && StringUtils.isNotEmpty(zipVersion) ?
                DefaultDBValue.APP_ZIP :
                StringUtils.isNotEmpty(appVersion) ? DefaultDBValue.IS_APP : DefaultDBValue.IS_ZIP;
        if(versionType != DefaultDBValue.IS_ZIP) {
            fileCopy(model, versionType);
        }

        updateSoftwareForVersionChange(model.getVcProjectSoftwareId());

        return vcSoftwareVersionMapper.updateByPrimaryKeySelective(model);
    }

    @Override public PaginationResult<VCSoftwareVersionModel> getPaginatedList(Long pageNo, Long pageSize,
            String softwareId) {
        PaginationResult<VCSoftwareVersionModel> result = new PaginationResult<VCSoftwareVersionModel>();
        Long count = vcSoftwareVersionMapper.counts(softwareId);
        Long pageCount = count % pageSize > 0 ? count / pageSize + 1 : count / pageSize;
        List<VCSoftwareVersionModel> modelList = vcSoftwareVersionMapper
                .selectPaginateds((pageNo - 1L) * pageSize, pageSize, softwareId);
        result.setPageCount(pageCount);
        result.setTotalCount(count);
        result.setList(modelList);
        return result;
    }

    @Override @Transactional public void offShelves(VCSoftwareVersionModel model) throws Exception {

        VCSoftwareVersionModel versionModel = orikaBeanMapper
                .map(vcSoftwareVersionMapper.selectByPrimaryKey(model.getVcSoftwareVersionId()),
                        VCSoftwareVersionModel.class);
        
        /**
         * 对于已经发布的 且不是zip 且是最大的版本的版本
         */
        if(!DefaultDBValue.NOT_ON_SHELVES.equals(versionModel.getIsOffShelves()) && !DefaultDBValue.IS_ZIP
                .equals(versionModel.getIsZip()) &&
                !VersionUtil.hasNewVersion(versionModel.getAppVersionNumber(), vcSoftwareVersionMapper
                        .selectShelvesMaxVersionBySoftwareId(versionModel.getVcProjectSoftwareId()))) {

            // 如果当前要下架的版本是最大的上架版本，则通用包需要更新到次新版本
            VCSoftwareVersionModel vm = vcSoftwareVersionMapper
                    .selectSecondVersion(versionModel.getVcProjectSoftwareId(), versionModel.getAppVersionNumber());
            if(null != vm) {
                saveCommonFile(vm.getCommonName(), vm.getAppVersionNumber());
            }
        }

        versionModel.setIsOffShelves(DefaultDBValue.OFF_SHELVES);
        versionModel.setModifiedon(new Date());
        versionModel.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));

        vcSoftwareVersionMapper.updateByPrimaryKeySelective(versionModel);
        updateSoftwareForVersionChange(versionModel.getVcProjectSoftwareId());
    }

    /**
     * 通过版本号的包产生通用名的包
     *
     * @param commonName
     * @param appVersionNumber
     * @throws Exception
     */
    private void saveCommonFile(String commonName, String appVersionNumber) throws Exception {
        if(StringUtils.isNotEmpty(commonName)) {
            int indexNum = commonName.lastIndexOf(".");
            StringBuffer sb = new StringBuffer();
            String common = commonName.substring(0, indexNum);
            sb.append(common).append(appVersionNumber).append(commonName.substring(indexNum));
            String savePath = fileUploadConfig.getUploadRoot() + fileUploadConfig.getFolder() + File.separator + common;
            File versionFile = new File(savePath + File.separator + sb.toString());
            if(versionFile.exists()) {
                InputStream input = new FileInputStream(versionFile);
                OutputStream output = new FileOutputStream(new File(savePath + File.separator + commonName));
                FileCopyUtils.copy(input, output);
            }
        }
    }

    @Override public VCSoftwareVersionModel findByVersionId(String versionId) {
        return orikaBeanMapper.map(vcSoftwareVersionMapper.selectByPrimaryKey(versionId), VCSoftwareVersionModel.class);
    }

    @Override public List<VCSoftwareVersionModel> selectNotShelvesVersionList(String releaseTime) {
        return vcSoftwareVersionMapper.selectNotShelvesVersionList(releaseTime);
    }

    @Override public VCSoftwareVersionModel getLatestVersionBySoftWareId(String softWareId) {
        VCSoftwareVersionModel latestApp = vcSoftwareVersionMapper.selectLatestAppBySoftWareId(softWareId);

        VCSoftwareVersionModel latestZip = vcSoftwareVersionMapper.selectLatestZipBySoftWareId(softWareId);
        VCSoftwareVersionModel retVersion = new VCSoftwareVersionModel();

        if(latestApp != null) {
            retVersion.setAppDesc(latestApp.getAppDesc());
            retVersion.setAppDownloadUrl(latestApp.getAppDownloadUrl());
            retVersion.setAppForceUpdate(latestApp.getAppForceUpdate());
            retVersion.setAppVersionNumber(latestApp.getAppVersionNumber());
            retVersion.setMinimunSupportSystemVersion(latestApp.getMinimunSupportSystemVersion());
        }

        if(latestZip != null) {
            retVersion.setZipDesc(latestZip.getZipDesc());
            retVersion.setZipDownloadUrl(latestZip.getZipDownloadUrl());
            retVersion.setZipForceUpdate(latestZip.getZipForceUpdate());
            retVersion.setZipVersionNumber(latestZip.getZipVersionNumber());
        }

        Date serverModifiedon = null;
        if(latestApp == null && latestZip == null) {
            return null;
        } else if(latestApp != null && latestZip == null) {
            serverModifiedon = latestApp.getModifiedon();
        } else if(latestApp == null) {
            serverModifiedon = latestZip.getModifiedon();
        } else {
            serverModifiedon = latestZip.getModifiedon().before(latestApp.getModifiedon()) ?
                    latestApp.getModifiedon() :
                    latestZip.getModifiedon();
        }
        retVersion.setModifiedon(serverModifiedon);
        return retVersion;
    }

    @Override public JSONObject getMaxVersionBySoftWareId(String softWareId) {
        String maxAppVersionNumber = vcSoftwareVersionMapper.selectMaxAppVersionNumber(softWareId);
        JSONObject json = new JSONObject();
        json.put("maxAppVersion", StringUtils.isNotBlank(maxAppVersionNumber) ? maxAppVersionNumber : "无");
        String maxZipVersionNumber = vcSoftwareVersionMapper.selectMaxZipVersionNumber(softWareId);
        json.put("maxZipVersion", StringUtils.isNotBlank(maxZipVersionNumber) ? maxZipVersionNumber : "无");
        return json;
    }

    /**
     * @param softWareId VCProjectSoftware表主键
     * @Description 当且仅当版本有增加、修改和下架时，将软件设置为有更新
     */
    private void updateSoftwareForVersionChange(String softWareId) {
        VCProjectSoftwareModel vcProjectSoftwareModel = orikaBeanMapper
                .map(vcProjectSoftwareMapper.selectByPrimaryKey(softWareId), VCProjectSoftwareModel.class);
        vcProjectSoftwareModel.setModifiedon(new Date());
        vcProjectSoftwareMapper.updateByPrimaryKey(vcProjectSoftwareModel);
    }

    /**
     * 验证版本号，保证两个版本号有一个7有值，并且当前软件下不会出现两个相同版本号
     *
     * @param model 软件版本Model
     */
    private void validateVersionNumber(VCSoftwareVersionModel model) {

        /**
         * 如果app和zip版本号都为空，则返回客户端错误
         */
        if(StringUtils.isEmpty(model.getAppVersionNumber()) && StringUtils.isEmpty(model.getZipVersionNumber())) {
            throw new BusinessException(-1, "app版本号或zip版本号必填其一。");
        }

        if(!StringUtils.isEmpty(vcSoftwareVersionMapper.selectByVersionNumber(
                StringUtils.isEmpty(model.getVcProjectSoftwareId()) ?
                        StringUtils.EMPTY :
                        model.getVcProjectSoftwareId(), StringUtils.isEmpty(model.getVcSoftwareVersionId()) ?
                        StringUtils.EMPTY :
                        model.getVcSoftwareVersionId(),
                StringUtils.isEmpty(model.getZipVersionNumber()) ? StringUtils.EMPTY : model.getZipVersionNumber(),
                StringUtils.isEmpty(model.getAppVersionNumber()) ? StringUtils.EMPTY : model.getAppVersionNumber()))) {
            throw new BusinessException(-1, "添加的版本号已存在（包括已下架版本）。");
        }
    }
}
