package org.pmp.vo;

// Generated 2012-4-19 19:48:26 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Company generated by hbm2java
 */
public class Company implements java.io.Serializable {
    
    private Integer comId;
    private String comName;
    private String comLegal;
    private String comLicense;
    private String comPhone;
    private String comAddress;
    private Date registerTime;
    private Integer registerMoney;
    private String comDesc;
    private boolean enabled;

    public String toString(){
	StringBuilder sb = new StringBuilder();
	sb.append("[comId]:"+comId+"[comName]:"+comName+"[comLegal]:"+comLegal+"[comLicense]:"+comLicense);
	sb.append("[comPhone]:"+comPhone+"[comAddress]:"+comAddress+"[registerTime]:"+registerTime.toString());
	sb.append("[regsterMoney]:"+registerMoney+"[comDesc]:"+comDesc+"[enabled]:"+enabled);
	return sb.toString();
    }
    
    public Company() {
    }

    public Company(Integer comId) {
	this.comId = comId;
    }

    public Company(Integer comId, String comName, String comLegal,
	    String comLicense, String comPhone, String comAddress,
	    Date registerTime, Integer registerMoney, String comDesc,
	    boolean enabled) {
	this.comId = comId;
	this.comName = comName;
	this.comLegal = comLegal;
	this.comLicense = comLicense;
	this.comPhone = comPhone;
	this.comAddress = comAddress;
	this.registerTime = registerTime;
	this.registerMoney = registerMoney;
	this.comDesc = comDesc;
	this.enabled = enabled;
    }

    public Integer getComId() {
	return this.comId;
    }

    public void setComId(Integer comId) {
	this.comId = comId;
    }

    public String getComName() {
	return this.comName;
    }

    public void setComName(String comName) {
	this.comName = comName;
    }

    public String getComLegal() {
	return this.comLegal;
    }

    public void setComLegal(String comLegal) {
	this.comLegal = comLegal;
    }

    public String getComLicense() {
	return this.comLicense;
    }

    public void setComLicense(String comLicense) {
	this.comLicense = comLicense;
    }

    public String getComPhone() {
	return this.comPhone;
    }

    public void setComPhone(String comPhone) {
	this.comPhone = comPhone;
    }

    public String getComAddress() {
	return this.comAddress;
    }

    public void setComAddress(String comAddress) {
	this.comAddress = comAddress;
    }

    public Date getRegisterTime() {
	return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
	this.registerTime = registerTime;
    }

    public Integer getRegisterMoney() {
	return this.registerMoney;
    }

    public void setRegisterMoney(Integer registerMoney) {
	this.registerMoney = registerMoney;
    }

    public String getComDesc() {
	return this.comDesc;
    }

    public void setComDesc(String comDesc) {
	this.comDesc = comDesc;
    }

    public boolean isEnabled() {
	return this.enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

}
