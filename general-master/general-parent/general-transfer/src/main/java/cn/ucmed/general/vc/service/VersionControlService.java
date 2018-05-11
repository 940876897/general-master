package cn.ucmed.general.vc.service;

import cn.ucmed.general.vc.model.VersionInfoModel;

public interface VersionControlService {

    VersionInfoModel getLatestVersion(String softWareId);
}
