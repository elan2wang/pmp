package org.pmp.vo;

// Generated 2012-4-19 19:48:26 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Owner generated by hbm2java
 */
public class Owner implements java.io.Serializable {

    private Integer ownerId;
    private String ownerName;
    private String gender;
    private String nationality;
    private String native_;
    private Date birthday;
    private Boolean ismarried;
    private String organization;
    private String hobby;
    private String identityType;
    private String identityCode;
    private String homePhone;
    private String mobile;
    private Date getTime;
    private Date decorateTime;
    private Date inTime;
    private String parkNum;
    private String carNum;
    private String carType;
    private String storeroom;
    private Double houseArea;
    private String useStyle;
    private String otherAddress;
    private String otherPostcode;
    private String emergencyContact;
    private String emergencyPhone;
    private String houseNum;
    private String ownerDesc;
    
    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+ownerId+","+ownerName+","+gender+","+nationality+","+native_+","+birthday);
    	sb.append(","+ismarried+","+organization+","+hobby+","+identityType+","+identityCode+","+homePhone);
    	sb.append(","+mobile+","+getTime+","+decorateTime+","+inTime+","+parkNum+","+carNum);
    	sb.append(","+carType+","+storeroom+","+houseArea+","+useStyle+","+otherAddress+","+otherPostcode);
    	sb.append(","+emergencyContact+","+emergencyPhone+","+houseNum+","+ownerDesc);
    	sb.append(" ]");
    	return sb.toString();
    }
    
    public Owner() {
    }

    public Owner(Integer ownerId) {
	this.ownerId = ownerId;
    }

    public Owner(Integer ownerId, String ownerName, String gender,
	    String nationality, String native_, Date birthday,
	    boolean ismarried, String organization, String hobby,
	    String identityType, String identityCode, String homePhone,
	    String mobile, Date getTime, Date decorateTime, Date inTime,
	    String parkNum, String carNum, String carType, String storeroom,
	    Double houseArea, String useStyle, String otherAddress,
	    String otherPostcode, String emergencyContact,
	    String emergencyPhone, String houseNum, String ownerDesc) {
	this.ownerId = ownerId;
	this.ownerName = ownerName;
	this.gender = gender;
	this.nationality = nationality;
	this.native_ = native_;
	this.birthday = birthday;
	this.ismarried = ismarried;
	this.organization = organization;
	this.hobby = hobby;
	this.identityType = identityType;
	this.identityCode = identityCode;
	this.homePhone = homePhone;
	this.mobile = mobile;
	this.getTime = getTime;
	this.decorateTime = decorateTime;
	this.inTime = inTime;
	this.parkNum = parkNum;
	this.carNum = carNum;
	this.carType = carType;
	this.storeroom = storeroom;
	this.houseArea = houseArea;
	this.useStyle = useStyle;
	this.otherAddress = otherAddress;
	this.otherPostcode = otherPostcode;
	this.emergencyContact = emergencyContact;
	this.emergencyPhone = emergencyPhone;
	this.houseNum = houseNum;
	this.ownerDesc = ownerDesc;
    }

    public Integer getOwnerId() {
	return this.ownerId;
    }

    public void setOwnerId(Integer ownerId) {
	this.ownerId = ownerId;
    }

    public String getOwnerName() {
	return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    public String getGender() {
	return this.gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getNationality() {
	return this.nationality;
    }

    public void setNationality(String nationality) {
	this.nationality = nationality;
    }

    public String getNative_() {
	return this.native_;
    }

    public void setNative_(String native_) {
	this.native_ = native_;
    }

    public Date getBirthday() {
	return this.birthday;
    }

    public void setBirthday(Date birthday) {
	this.birthday = birthday;
    }

    public boolean isIsmarried() {
	return this.ismarried;
    }

    public void setIsmarried(boolean ismarried) {
	this.ismarried = ismarried;
    }

    public String getOrganization() {
	return this.organization;
    }

    public void setOrganization(String organization) {
	this.organization = organization;
    }

    public String getHobby() {
	return this.hobby;
    }

    public void setHobby(String hobby) {
	this.hobby = hobby;
    }

    public String getIdentityType() {
	return this.identityType;
    }

    public void setIdentityType(String identityType) {
	this.identityType = identityType;
    }

    public String getIdentityCode() {
	return this.identityCode;
    }

    public void setIdentityCode(String identityCode) {
	this.identityCode = identityCode;
    }

    public String getHomePhone() {
	return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
	this.homePhone = homePhone;
    }

    public String getMobile() {
	return this.mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public Date getGetTime() {
	return this.getTime;
    }

    public void setGetTime(Date getTime) {
	this.getTime = getTime;
    }

    public Date getDecorateTime() {
	return this.decorateTime;
    }

    public void setDecorateTime(Date decorateTime) {
	this.decorateTime = decorateTime;
    }

    public Date getInTime() {
	return this.inTime;
    }

    public void setInTime(Date inTime) {
	this.inTime = inTime;
    }

    public String getParkNum() {
	return this.parkNum;
    }

    public void setParkNum(String parkNum) {
	this.parkNum = parkNum;
    }

    public String getCarNum() {
	return this.carNum;
    }

    public void setCarNum(String carNum) {
	this.carNum = carNum;
    }

    public String getCarType() {
	return this.carType;
    }

    public void setCarType(String carType) {
	this.carType = carType;
    }

    public String getStoreroom() {
	return this.storeroom;
    }

    public void setStoreroom(String storeroom) {
	this.storeroom = storeroom;
    }

    public Double getHouseArea() {
	return this.houseArea;
    }

    public void setHouseArea(Double houseArea) {
	this.houseArea = houseArea;
    }

    public String getUseStyle() {
	return this.useStyle;
    }

    public void setUseStyle(String useStyle) {
	this.useStyle = useStyle;
    }

    public String getOtherAddress() {
	return this.otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
	this.otherAddress = otherAddress;
    }

    public String getOtherPostcode() {
	return this.otherPostcode;
    }

    public void setOtherPostcode(String otherPostcode) {
	this.otherPostcode = otherPostcode;
    }

    public String getEmergencyContact() {
	return this.emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
	this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
	return this.emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
	this.emergencyPhone = emergencyPhone;
    }

    public String getHouseNum() {
	return this.houseNum;
    }

    public void setHouseNum(String houseNum) {
	this.houseNum = houseNum;
    }

    public String getOwnerDesc() {
	return this.ownerDesc;
    }

    public void setOwnerDesc(String ownerDesc) {
	this.ownerDesc = ownerDesc;
    }

}
