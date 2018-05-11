package cn.ucmed.general.vc.service;

import cn.ucmed.common.orika.OrikaBeanMapper;
import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.vc.dao.VCProjectSoftwareMapper;
import cn.ucmed.general.vc.model.VCProjectSoftware;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.common.util.PaginationResult;
import org.apache.shiro.SecurityUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class VCProjectSoftwareServiceImpl implements VCProjectSoftwareService {

    private VCProjectSoftwareMapper vcProjectSoftwareMapper;

    private VCSoftwareVersionService vcSoftwareVersionService;

    private OrikaBeanMapper orikaBeanMapper;

    public void setVcProjectSoftwareMapper(
            VCProjectSoftwareMapper vcProjectSoftwareMapper) {
        this.vcProjectSoftwareMapper = vcProjectSoftwareMapper;
    }

    public void setVcSoftwareVersionService(VCSoftwareVersionService vcSoftwareVersionService) {
        this.vcSoftwareVersionService = vcSoftwareVersionService;
    }

    public void setOrikaBeanMapper(OrikaBeanMapper orikaBeanMapper) {
        this.orikaBeanMapper = orikaBeanMapper;
    }

    @Override
    public void addProjectSoftware(VCProjectSoftware vcProjectSoftware) {
        vcProjectSoftware.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);
        vcProjectSoftware.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        vcProjectSoftware.setCreatedon(new Date());
        vcProjectSoftware.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        vcProjectSoftware.setModifiedon(new Date());
        vcProjectSoftware.setVcProjectSoftwareId(UUID.randomUUID().toString());
        vcProjectSoftwareMapper.insertProjectSoftware(vcProjectSoftware);
    }

    @Override
    public void updateProjectSoftware(VCProjectSoftware vcProjectSoftware) {
        vcProjectSoftware.setModifiedon(new Date());
        vcProjectSoftware.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        vcProjectSoftwareMapper.updateByPrimaryKey(vcProjectSoftware);
    }

    @Override
    public PaginationResult<VCProjectSoftwareModel> getProjectSoftwareList(
            String vcProjectId, Long pageNo, Long pageSize) {
        final Long count = vcProjectSoftwareMapper
                .countAllByProjectId(vcProjectId);
        List<VCProjectSoftwareModel> lstSoftWare = vcProjectSoftwareMapper.getProjectSoftwareList(
                vcProjectId, (pageNo - 1L) * pageSize, pageSize);
        for (VCProjectSoftwareModel vcProjectSoftwareModel : lstSoftWare) {
            vcProjectSoftwareModel.setLatestVersion(vcSoftwareVersionService
                    .getLatestVersionBySoftWareId(vcProjectSoftwareModel.getVcProjectSoftwareId()));
        }

        return new PaginationResult<VCProjectSoftwareModel>(
                lstSoftWare, count % pageSize > 0 ? count / pageSize + 1 : count / pageSize,
                count);
    }

    @Override
    public PaginationResult<VCProjectSoftwareModel> loadSoftwareList(Long pageNo, Long pageSize) {
        final Long count = vcProjectSoftwareMapper.countAll();
        List<VCProjectSoftwareModel> lstSoftWare = vcProjectSoftwareMapper.loadSoftwareList(
                (pageNo - 1L) * pageSize, pageSize);
        for (VCProjectSoftwareModel vcProjectSoftwareModel : lstSoftWare) {
            vcProjectSoftwareModel.setLatestVersion(vcSoftwareVersionService
                    .getLatestVersionBySoftWareId(vcProjectSoftwareModel.getVcProjectSoftwareId()));
        }

        return new PaginationResult<VCProjectSoftwareModel>(
                lstSoftWare, count % pageSize > 0 ? count / pageSize + 1 : count / pageSize,
                count);
    }


    @Override
    public PaginationResult<VCProjectSoftwareModel> seachSoftwareList(String seach, Long pageNo, Long pageSize) {
        final Long count = vcProjectSoftwareMapper.countSeachAll(seach);
        List<VCProjectSoftwareModel> lstSoftWare = vcProjectSoftwareMapper.seachSoftwareList(
                seach,(pageNo - 1L) * pageSize, pageSize);
        for (VCProjectSoftwareModel vcProjectSoftwareModel : lstSoftWare) {
            vcProjectSoftwareModel.setLatestVersion(vcSoftwareVersionService
                    .getLatestVersionBySoftWareId(vcProjectSoftwareModel.getVcProjectSoftwareId()));
        }

        return new PaginationResult<VCProjectSoftwareModel>(
                lstSoftWare, count % pageSize > 0 ? count / pageSize + 1 : count / pageSize,
                count);
    }

    @Override
    public VCProjectSoftwareModel findBySoftwareId(String softwareId) {
        VCProjectSoftwareModel projectSoftwareModel = orikaBeanMapper.map(vcProjectSoftwareMapper
                        .selectByPrimaryKey(softwareId),
                VCProjectSoftwareModel.class);
        projectSoftwareModel.setLatestVersion(vcSoftwareVersionService
                .getLatestVersionBySoftWareId(softwareId));
        return projectSoftwareModel;
    }

    @Override
    public VCProjectSoftwareModel loadByCommonName(String commonName) {
        return vcProjectSoftwareMapper.selectByCommonName(commonName);
    }
}