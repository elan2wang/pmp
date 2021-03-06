package org.pmp.vo;

// Generated 2012-4-19 19:48:26 by Hibernate Tools 3.4.0.CR1


/**
 * Building generated by hbm2java
 */
public class Building implements java.io.Serializable {
    
    private Integer builId;
    private Project project;
    private String builNum;
    private String builType;
    private Integer floorCount;
    private String skipFloor;
    private Integer housesPer;
    private Integer unitCount;
    private String unitTag;
    private Double condoFeeRate;
    private String builDesc;
    private Boolean enabled;

    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+builId+","+project+","+builNum+","+builType+","+floorCount+","+skipFloor);
    	sb.append(","+housesPer+","+unitCount+","+unitTag+","+condoFeeRate+","+builDesc+","+enabled);
    	sb.append(" ]");
    	return sb.toString();
    }
    
    public Building() {
    }

    public Building(Integer builId) {
	this.builId = builId;
    }

    public Building(Integer builId, Project project, String builNum,
	    String builType, Integer floorCount, String skipFloor,
	    Integer housesPer, Integer unitCount, String unitTag,
	    Double condoFeeRate, String builDesc, Boolean enabled) {
	this.builId = builId;
	this.project = project;
	this.builNum = builNum;
	this.builType = builType;
	this.floorCount = floorCount;
	this.skipFloor = skipFloor;
	this.housesPer = housesPer;
	this.unitCount = unitCount;
	this.unitTag = unitTag;
	this.condoFeeRate = condoFeeRate;
	this.builDesc = builDesc;
	this.enabled = enabled;
    }

    public Integer getBuilId() {
	return this.builId;
    }

    public void setBuilId(Integer builId) {
	this.builId = builId;
    }

    public Project getProject() {
	return this.project;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public String getBuilNum() {
	return this.builNum;
    }

    public void setBuilNum(String builNum) {
	this.builNum = builNum;
    }

    public String getBuilType() {
	return this.builType;
    }

    public void setBuilType(String builType) {
	this.builType = builType;
    }

    public Integer getFloorCount() {
	return this.floorCount;
    }

    public void setFloorCount(Integer floorCount) {
	this.floorCount = floorCount;
    }

    public String getSkipFloor() {
	return this.skipFloor;
    }

    public void setSkipFloor(String skipFloor) {
	this.skipFloor = skipFloor;
    }

    public Integer getHousesPer() {
	return this.housesPer;
    }

    public void setHousesPer(Integer housesPer) {
	this.housesPer = housesPer;
    }

    public Integer getUnitCount() {
	return this.unitCount;
    }

    public void setUnitCount(Integer unitCount) {
	this.unitCount = unitCount;
    }

    public String getUnitTag() {
	return this.unitTag;
    }

    public void setUnitTag(String unitTag) {
	this.unitTag = unitTag;
    }

    public Double getCondoFeeRate() {
	return this.condoFeeRate;
    }

    public void setCondoFeeRate(Double condoFeeRate) {
	this.condoFeeRate = condoFeeRate;
    }

    public String getBuilDesc() {
	return this.builDesc;
    }

    public void setBuilDesc(String builDesc) {
	this.builDesc = builDesc;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
