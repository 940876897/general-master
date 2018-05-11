package cn.ucmed.general.vc.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "version_info")
public class VersionInfo extends BaseEntity implements Serializable {
    /**
     * @Description
     */
    private static final long serialVersionUID = 2L;

    /**
     * 主键
     */
    @Id
    @Column(name = "version_info_id")
    private String versionInfoId;

    @Column(name = "software_id")
    private Integer softwareId;

    /**
     * 应用版本号［x.y.z］
     */
    @Column(name = "app_version_number")
    private String appVersionNumber;

    /**
     * 应用下载地址
     */
    @Column(name = "app_download_url")
    private String appDownloadUrl;

    /**
     * zip版本号［x.y.z］
     */
    @Column(name = "zip_version_number")
    private String zipVersionNumber;

    /**
     * zip下载地址
     */
    @Column(name = "zip_download_url")
    private String zipDownloadUrl;

    /**
     * app备注
     */
    @Column(name = "app_desc")
    private String appDesc;

    /**
     * zip备注
     */
    @Column(name = "zip_desc")
    private String zipDesc;

    /**
     * 最新强制更新版本
     */
    @Column(name = "latest_force_update_version")
    private String latestForceUpdateVersion;

    /**
     * 资源更新类型：S静默更新,A全包更新,I增量更新
     */
    @Column(name = "zip_version_state")
    private String zipVersionState;

    /**
     * 软件更新时间，用于和软件版本同步
     */
    @Column(name = "software_update_time")
    private Date softwareUpdateTime;

    /**
     * 此版本支持的最低系统版本
     */
    @Column(name = "minimum_support_system_version")
    private String minimunSupportSystemVersion;

    /**
     * 获取主键
     *
     * @return version_info_id - 主键
     */
    public String getVersionInfoId() {
        return versionInfoId;
    }

    /**
     * 设置主键
     *
     * @param versionInfoId 主键
     */
    public void setVersionInfoId(String versionInfoId) {
        this.versionInfoId = versionInfoId;
    }

    /**
     * @return software_id
     */
    public Integer getSoftwareId() {
        return softwareId;
    }

    /**
     * @param softwareId
     */
    public void setSoftwareId(Integer softwareId) {
        this.softwareId = softwareId;
    }

    /**
     * 获取应用版本号［x.y.z］
     *
     * @return app_version_number - 应用版本号［x.y.z］
     */
    public String getAppVersionNumber() {
        return appVersionNumber;
    }

    /**
     * 设置应用版本号［x.y.z］
     *
     * @param appVersionNumber 应用版本号［x.y.z］
     */
    public void setAppVersionNumber(String appVersionNumber) {
        this.appVersionNumber = appVersionNumber;
    }

    /**
     * 获取应用下载地址
     *
     * @return app_download_url - 应用下载地址
     */
    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    /**
     * 设置应用下载地址
     *
     * @param appDownloadUrl 应用下载地址
     */
    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    /**
     * 获取zip版本号［x.y.z］
     *
     * @return zip_version_number - zip版本号［x.y.z］
     */
    public String getZipVersionNumber() {
        return zipVersionNumber;
    }

    /**
     * 设置zip版本号［x.y.z］
     *
     * @param zipVersionNumber zip版本号［x.y.z］
     */
    public void setZipVersionNumber(String zipVersionNumber) {
        this.zipVersionNumber = zipVersionNumber;
    }

    /**
     * 获取zip下载地址
     *
     * @return zip_download_url - zip下载地址
     */
    public String getZipDownloadUrl() {
        return zipDownloadUrl;
    }

    /**
     * 设置zip下载地址
     *
     * @param zipDownloadUrl zip下载地址
     */
    public void setZipDownloadUrl(String zipDownloadUrl) {
        this.zipDownloadUrl = zipDownloadUrl;
    }

    /**
     * 获取app备注
     *
     * @return app_desc - app备注
     */
    public String getAppDesc() {
        return appDesc;
    }

    /**
     * 设置app备注
     *
     * @param appDesc app备注
     */
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    /**
     * 获取zip备注
     *
     * @return zip_desc - zip备注
     */
    public String getZipDesc() {
        return zipDesc;
    }

    /**
     * 设置zip备注
     *
     * @param zipDesc zip备注
     */
    public void setZipDesc(String zipDesc) {
        this.zipDesc = zipDesc;
    }

    /**
     * 获取最新强制更新版本
     *
     * @return latest_force_update_version - 最新强制更新版本
     */
    public String getLatestForceUpdateVersion() {
        return latestForceUpdateVersion;
    }

    /**
     * 设置最新强制更新版本
     *
     * @param latestForceUpdateVersion 最新强制更新版本
     */
    public void setLatestForceUpdateVersion(String latestForceUpdateVersion) {
        this.latestForceUpdateVersion = latestForceUpdateVersion;
    }

    /**
     * 获取资源更新类型：S静默更新,A全包更新,I增量更新
     *
     * @return zip_version_state - 资源更新类型：S静默更新,A全包更新,I增量更新
     */
    public String getZipVersionState() {
        return zipVersionState;
    }

    /**
     * 设置资源更新类型：S静默更新,A全包更新,I增量更新
     *
     * @param zipVersionState 资源更新类型：S静默更新,A全包更新,I增量更新
     */
    public void setZipVersionState(String zipVersionState) {
        this.zipVersionState = zipVersionState;
    }

    /**
     * 获取软件更新时间，用于和软件版本同步
     *
     * @return software_update_time - 软件更新时间，用于和软件版本同步
     */
    public Date getSoftwareUpdateTime() {
        return softwareUpdateTime;
    }

    /**
     * 设置软件更新时间，用于和软件版本同步
     *
     * @param softwareUpdateTime 软件更新时间，用于和软件版本同步
     */
    public void setSoftwareUpdateTime(Date softwareUpdateTime) {
        this.softwareUpdateTime = softwareUpdateTime;
    }

    /**
     * 获取此版本支持的最低系统版本
     *
     * @return latest_system_version - 此版本支持的最低系统版本
     */
    public String getMinimunSupportSystemVersion() {
        return minimunSupportSystemVersion;
    }

    /**
     * 设置此版本支持的最低系统版本
     *
     * @param minimunSupportSystemVersion 此版本支持的最低系统版本
     */
    public void setMinimunSupportSystemVersion(String minimunSupportSystemVersion) {
        this.minimunSupportSystemVersion = minimunSupportSystemVersion;
    }

}
