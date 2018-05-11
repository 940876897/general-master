package cn.ucmed.general.vc.model;

import java.io.Serializable;
import java.util.List;

public class VCProjectModel extends VCProject implements Serializable {

    private List<VCProjectSoftwareModel> projectSoftwareList;

    private List<VCProjectModel> projectList;

    public List<VCProjectSoftwareModel> getProjectSoftwareList() {
        return projectSoftwareList;
    }

    public void setProjectSoftwareList(
            List<VCProjectSoftwareModel> projectSoftwareList) {
        this.projectSoftwareList = projectSoftwareList;
    }

    public void setProjectList(List<VCProjectModel> projectList) {
        this.projectList = projectList;
    }

    public List<VCProjectModel> getProjectList() {
        return projectList;
    }
}