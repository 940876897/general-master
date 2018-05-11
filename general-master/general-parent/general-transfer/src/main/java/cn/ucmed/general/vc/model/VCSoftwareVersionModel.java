package cn.ucmed.general.vc.model;

public class VCSoftwareVersionModel extends VCSoftwareVersion {

    private String projectName;

    private String softwareName;

    private String commonName;

    private String currentMaxAppVersion;

    private String currentMaxZipVersion;

    private String softwareType;

    public String getCurrentMaxAppVersion() {
        return currentMaxAppVersion;
    }

    public void setCurrentMaxAppVersion(String currentMaxAppVersion) {
        this.currentMaxAppVersion = currentMaxAppVersion;
    }

    public String getCurrentMaxZipVersion() {
        return currentMaxZipVersion;
    }

    public void setCurrentMaxZipVersion(String currentMaxZipVersion) {
        this.currentMaxZipVersion = currentMaxZipVersion;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSoftwareType() {
        return softwareType;
    }

    public void setSoftwareType(String softwareType) {
        this.softwareType = softwareType;
    }


}