package cn.ucmed.general.vc.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "vc_project")
public class VCProject extends BaseEntity implements Serializable {

    /**
     * @Description
     */
    private static final long serialVersionUID = -353713520323955411L;

    /**
     * 主键
     */
    @Id
    @Column(name = "vc_project_id")
    private String vcProjectId;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 医院名称
     */
    @Column(name = "hospital_name")
    private String hospitalName;

    /**
     * 获取主键
     *
     * @return vc_project_id - 主键
     */
    public String getVcProjectId() {
        return vcProjectId;
    }

    /**
     * 设置主键
     *
     * @param vcProjectId 主键
     */
    public void setVcProjectId(String vcProjectId) {
        this.vcProjectId = vcProjectId;
    }

    /**
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取医院名称
     *
     * @return hospital_name - 医院名称
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * 设置医院名称
     *
     * @param hospitalName 医院名称
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * 后台生产请求地址
     */
    private String requestUrl;

    /**
     * 项目类型（1.平台，2.单医院）
     */
    private String projectType;

    /**
     * 用于组织架构
     */
    private String owner;


    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getOwner() {
        return owner;
    }
}
