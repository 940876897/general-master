package cn.ucmed.general.vc.service;

import cn.ucmed.general.vc.dao.VersionInfoMapper;
import cn.ucmed.general.vc.model.VersionInfoModel;

public class VersionControlServiceImpl implements VersionControlService {

    private VersionInfoMapper versionInfoMapper;

    public void setVersionInfoMapper(VersionInfoMapper versionInfoMapper) {
        this.versionInfoMapper = versionInfoMapper;
    }

    @Override
    public VersionInfoModel getLatestVersion(String softWareId) {
        return versionInfoMapper.selectBySoftWareId(softWareId);
    }
}
