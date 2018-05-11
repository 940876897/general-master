package cn.ucmed.general.vc.service;

public interface VersionInfoService {

    /**
     * @Description 将有更新的软件的最新版本更新到只读表Version_Info中
     */
    void updateToLatestVersion();

}
