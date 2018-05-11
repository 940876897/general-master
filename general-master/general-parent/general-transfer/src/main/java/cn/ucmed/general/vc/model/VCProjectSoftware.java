package cn.ucmed.general.vc.model;

import cn.ucmed.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "vc_project_software")
public class VCProjectSoftware extends BaseEntity implements Serializable{

    /**
     * 主键
     */
    @Id
    @Column(name = "vc_project_software_id")
    private String vcProjectSoftwareId;

    /**
     * vc_project表主键
     */
    @Column(name = "vc_project_id")
    private String vcProjectId;

    /**
     * 软件名称（如，掌上浙一）
     */
    @Column(name = "software_name")
    private String softwareName;

    /**
     * 通用名称（如，zszy）
     */
    @Column(name = "common_name")
    private String commonName;

    /**
     * 软件类型（Android、iOS、Win8）
     */
    @Column(name = "software_type")
    private String softwareType;

    /**
     * 软件ID
     */
    @Column(name = "software_id")
    private Integer softwareId;

    /**
     * 获取主键
     *
     * @return vc_project_software_id - 主键
     */
    public String getVcProjectSoftwareId() {
        return vcProjectSoftwareId;
    }

    /**
     * 设置主键
     *
     * @param vcProjectSoftwareId 主键
     */
    public void setVcProjectSoftwareId(String vcProjectSoftwareId) {
        this.vcProjectSoftwareId = vcProjectSoftwareId;
    }

    /**
     * 获取vc_project表主键
     *
     * @return vc_project_id - vc_project表主键
     */
    public String getVcProjectId() {
        return vcProjectId;
    }

    /**
     * 设置vc_project表主键
     *
     * @param vcProjectId vc_project表主键
     */
    public void setVcProjectId(String vcProjectId) {
        this.vcProjectId = vcProjectId;
    }

    /**
     * 获取软件名称（如，掌上浙一）
     *
     * @return software_name - 软件名称（如，掌上浙一）
     */
    public String getSoftwareName() {
        return softwareName;
    }

    /**
     * 设置软件名称（如，掌上浙一）
     *
     * @param softwareName 软件名称（如，掌上浙一）
     */
    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    /**
     * 获取通用名称（如，zszy）
     *
     * @return common_name - 通用名称（如，zszy）
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * 设置通用名称（如，zszy）
     *
     * @param commonName 通用名称（如，zszy）
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * 获取软件类型（Android、iOS、Win8）
     *
     * @return software_type - 软件类型（Android、iOS、Win8）
     */
    public String getSoftwareType() {
        return softwareType;
    }

    /**
     * 设置软件类型（Android、iOS、Win8）
     *
     * @param softwareType 软件类型（Android、iOS、Win8）
     */
    public void setSoftwareType(String softwareType) {
        this.softwareType = softwareType;
    }

    /**
     * 获取软件ID
     *
     * @return software_id - 软件ID
     */
    public Integer getSoftwareId() {
        return softwareId;
    }

    /**
     * 设置软件ID
     *
     * @param softwareId 软件ID
     */
    public void setSoftwareId(Integer softwareId) {
        this.softwareId = softwareId;
    }
}
