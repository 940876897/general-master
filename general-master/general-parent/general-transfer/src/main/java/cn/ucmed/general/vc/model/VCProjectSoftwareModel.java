package cn.ucmed.general.vc.model;

import java.io.Serializable;

public class VCProjectSoftwareModel extends VCProjectSoftware implements Serializable {

    private VCSoftwareVersionModel latestVersion;

    /**
     * @Description 版本检测API使用该字段判断是否强制更新
     */
    private String latestForceUpdateVersion;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column vc_project.project_name
     *
     * @FieldComment 项目名称
     * @mbggenerated
     */
    private String projectName;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database column vc_project.hospital_name
     *
     * @FieldComment 医院名称
     * @mbggenerated
     */
    private String hospitalName;

    public String getLatestForceUpdateVersion() {
        return latestForceUpdateVersion;
    }

    public void setLatestForceUpdateVersion(String latestForceUpdateVersion) {
        this.latestForceUpdateVersion = latestForceUpdateVersion;
    }

    public VCSoftwareVersionModel getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(VCSoftwareVersionModel latestVersion) {
        this.latestVersion = latestVersion;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalName() {
        return hospitalName;
    }
}