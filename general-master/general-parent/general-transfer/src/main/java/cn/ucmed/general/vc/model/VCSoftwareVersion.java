package cn.ucmed.general.vc.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "vc_software_version")
public class VCSoftwareVersion extends BaseEntity implements Serializable {
    /**
     * @Description
     */
    private static final long serialVersionUID = 5875913102425126512L;

    /**
     * 主键
     */
    @Id
    @Column(name = "vc_software_version_id")
    private String vcSoftwareVersionId;

    /**
     * vc_project_software表主键
     */
    @Column(name = "vc_project_software_id")
    private String vcProjectSoftwareId;

    /**
     * 掌上医院版本号
     */
    @Column(name = "version_number")
    private String versionNumber;

    /**
     * 应用版本号［x.y.z］
     */
    @Column(name = "app_version_number")
    private String appVersionNumber;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 应用下载地址
     */
    @Column(name = "app_download_url")
    private String appDownloadUrl;

    /**
     * 强制更新，F：强制更新；R：提醒更新；
     */
    @Column(name = "app_force_update")
    private String appForceUpdate;

    /**
     * zip版本号［x.y.z］
     */
    @Column(name = "zip_version_number")
    private String zipVersionNumber;

    /**
     * zip描述
     */
    @Column(name = "zip_desc")
    private String zipDesc;

    /**
     * 市场描述
     */
    @Column(name = "app_desc")
    private String appDesc;

    /**
     * zip下载地址
     */
    @Column(name = "zip_download_url")
    private String zipDownloadUrl;

    /**
     * 上架状态,0未上架，1已上架，2已下架
     */
    @Column(name = "is_off_shelves")
    private String isOffShelves;

    /**
     * 添加该条记录时的最大app版本号
     */
    @Column(name = "current_max_app")
    private String currentMaxApp;

    /**
     * 添加该条记录时的最大zip版本号
     */
    @Column(name = "current_max_zip")
    private String currentMaxZip;

    /**
     * 状态,0应用，1资源,2应用和资源
     */
    @Column(name = "is_zip")
    private String isZip;

    /**
     * 资源更新类型：S静默更新,A全包更新,I增量更新
     */
    @Column(name = "zip_force_update")
    private String zipForceUpdate;

    /**
     * 此版本支持的最低系统版本
     */
    @Column(name = "minimum_support_system_version")
    private String minimunSupportSystemVersion;

    /**
     * 获取主键
     *
     * @return vc_software_version_id - 主键
     */
    public String getVcSoftwareVersionId() {
        return vcSoftwareVersionId;
    }

    /**
     * 设置主键
     *
     * @param vcSoftwareVersionId 主键
     */
    public void setVcSoftwareVersionId(String vcSoftwareVersionId) {
        this.vcSoftwareVersionId = vcSoftwareVersionId;
    }

    /**
     * 获取vc_project_software表主键
     *
     * @return vc_project_software_id - vc_project_software表主键
     */
    public String getVcProjectSoftwareId() {
        return vcProjectSoftwareId;
    }

    /**
     * 设置vc_project_software表主键
     *
     * @param vcProjectSoftwareId vc_project_software表主键
     */
    public void setVcProjectSoftwareId(String vcProjectSoftwareId) {
        this.vcProjectSoftwareId = vcProjectSoftwareId;
    }

    /**
     * 获取掌上医院版本号
     *
     * @return version_number - 掌上医院版本号
     */
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * 设置掌上医院版本号
     *
     * @param versionNumber 掌上医院版本号
     */
    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
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
     * 获取发布时间
     *
     * @return release_time - 发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     *
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
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
     * 获取强制更新，F：强制更新；R：提醒更新；
     *
     * @return app_force_update - 强制更新，F：强制更新；R：提醒更新；
     */
    public String getAppForceUpdate() {
        return appForceUpdate;
    }

    /**
     * 设置强制更新，F：强制更新；R：提醒更新；
     *
     * @param appForceUpdate 强制更新，F：强制更新；R：提醒更新；
     */
    public void setAppForceUpdate(String appForceUpdate) {
        this.appForceUpdate = appForceUpdate;
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
     * 获取zip描述
     *
     * @return zip_desc - zip描述
     */
    public String getZipDesc() {
        return zipDesc;
    }

    /**
     * 设置zip描述
     *
     * @param zipDesc zip描述
     */
    public void setZipDesc(String zipDesc) {
        this.zipDesc = zipDesc;
    }

    /**
     * 获取市场描述
     *
     * @return app_desc - 市场描述
     */
    public String getAppDesc() {
        return appDesc;
    }

    /**
     * 设置市场描述
     *
     * @param appDesc 市场描述
     */
    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
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
     * 获取上架状态,0未上架，1已上架，2已下架
     *
     * @return is_off_shelves - 上架状态,0未上架，1已上架，2已下架
     */
    public String getIsOffShelves() {
        return isOffShelves;
    }

    /**
     * 设置上架状态,0未上架，1已上架，2已下架
     *
     * @param isOffShelves 上架状态,0未上架，1已上架，2已下架
     */
    public void setIsOffShelves(String isOffShelves) {
        this.isOffShelves = isOffShelves;
    }

    /**
     * 获取添加该条记录时的最大app版本号
     *
     * @return current_max_app - 添加该条记录时的最大app版本号
     */
    public String getCurrentMaxApp() {
        return currentMaxApp;
    }

    /**
     * 设置添加该条记录时的最大app版本号
     *
     * @param currentMaxApp 添加该条记录时的最大app版本号
     */
    public void setCurrentMaxApp(String currentMaxApp) {
        this.currentMaxApp = currentMaxApp;
    }

    /**
     * 获取添加该条记录时的最大zip版本号
     *
     * @return current_max_zip - 添加该条记录时的最大zip版本号
     */
    public String getCurrentMaxZip() {
        return currentMaxZip;
    }

    /**
     * 设置添加该条记录时的最大zip版本号
     *
     * @param currentMaxZip 添加该条记录时的最大zip版本号
     */
    public void setCurrentMaxZip(String currentMaxZip) {
        this.currentMaxZip = currentMaxZip;
    }

    /**
     * 获取状态,0应用，1资源,2应用和资源
     *
     * @return is_zip - 状态,0应用，1资源,2应用和资源
     */
    public String getIsZip() {
        return isZip;
    }

    /**
     * 设置状态,0应用，1资源,2应用和资源
     *
     * @param isZip 状态,0应用，1资源,2应用和资源
     */
    public void setIsZip(String isZip) {
        this.isZip = isZip;
    }

    /**
     * 获取资源更新类型：S静默更新,A全包更新,I增量更新
     *
     * @return zip_force_update - 资源更新类型：S静默更新,A全包更新,I增量更新
     */
    public String getZipForceUpdate() {
        return zipForceUpdate;
    }

    /**
     * 设置资源更新类型：S静默更新,A全包更新,I增量更新
     *
     * @param zipForceUpdate 资源更新类型：S静默更新,A全包更新,I增量更新
     */
    public void setZipForceUpdate(String zipForceUpdate) {
        this.zipForceUpdate = zipForceUpdate;
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
