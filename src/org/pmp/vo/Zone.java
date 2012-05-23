package org.pmp.vo;

// Generated 2012-5-21 12:04:16 by Hibernate Tools 3.4.0.CR1

/**
 * Zone generated by hbm2java
 */
public class Zone implements java.io.Serializable {

    private Integer zoneId;
    private String zoneName;
    private Project project;
    private String zoneType;
    private String zoneImgUrl;
    private String zoneConfigUrl;
    private String zoneDesc;
   
    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+zoneId+","+zoneName+","+project+","+zoneType+","+zoneImgUrl+","+zoneConfigUrl+","+zoneDesc+" ]");
    	return sb.toString();
    }
    
    public Zone() {
    }

    public Zone(Integer zoneId) {
	this.zoneId = zoneId;
    }

    public Zone(Integer zoneId, String zoneName, Project project,
	    String zoneType, String zoneImgUrl, String zoneConfigUrl,
	    String zoneDesc) {
	this.zoneId = zoneId;
	this.zoneName = zoneName;
	this.project = project;
	this.zoneType = zoneType;
	this.zoneImgUrl = zoneImgUrl;
	this.zoneConfigUrl = zoneConfigUrl;
	this.zoneDesc = zoneDesc;
    }

    public Integer getZoneId() {
	return this.zoneId;
    }

    public void setZoneId(Integer zoneId) {
	this.zoneId = zoneId;
    }

    public String getZoneName() {
	return this.zoneName;
    }

    public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
    }

    public String getZoneType() {
	return this.zoneType;
    }

    public void setZoneType(String zoneType) {
	this.zoneType = zoneType;
    }

    public String getZoneImgUrl() {
	return this.zoneImgUrl;
    }

    public void setZoneImgUrl(String zoneImgUrl) {
	this.zoneImgUrl = zoneImgUrl;
    }

    public String getZoneConfigUrl() {
	return this.zoneConfigUrl;
    }

    public void setZoneConfigUrl(String zoneConfigUrl) {
	this.zoneConfigUrl = zoneConfigUrl;
    }

    public String getZoneDesc() {
	return this.zoneDesc;
    }

    public void setZoneDesc(String zoneDesc) {
	this.zoneDesc = zoneDesc;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
