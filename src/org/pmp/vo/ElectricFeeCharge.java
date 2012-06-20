package org.pmp.vo;

// Generated 2012-6-20 13:11:21 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * ElectricFeeCharge generated by hbm2java
 */
public class ElectricFeeCharge implements java.io.Serializable {

    private Integer efcId;
    private HouseOwner houseOwner;
    private Date chargeTime;
    private Double chargeMoney;
    private String inputPerson;
    private String auditPerson;
    private Date auditTime;
    private String state;
    private String comment;

    public ElectricFeeCharge() {
    }

    public ElectricFeeCharge(Integer efcId) {
	this.efcId = efcId;
    }

    public ElectricFeeCharge(Integer efcId, HouseOwner houseOwner, Date chargeTime,
	    Double chargeMoney, String inputPerson, String auditPerson,
	    Date auditTime, String state, String comment) {
	this.efcId = efcId;
	this.houseOwner = houseOwner;
	this.chargeTime = chargeTime;
	this.chargeMoney = chargeMoney;
	this.inputPerson = inputPerson;
	this.auditPerson = auditPerson;
	this.auditTime = auditTime;
	this.state = state;
	this.comment = comment;
    }

    public Integer getEfcId() {
	return this.efcId;
    }

    public void setEfcId(Integer efcId) {
	this.efcId = efcId;
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Date getChargeTime() {
	return this.chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
	this.chargeTime = chargeTime;
    }

    public Double getChargeMoney() {
	return this.chargeMoney;
    }

    public void setChargeMoney(Double chargeMoney) {
	this.chargeMoney = chargeMoney;
    }

    public String getInputPerson() {
	return this.inputPerson;
    }

    public void setInputPerson(String inputPerson) {
	this.inputPerson = inputPerson;
    }

    public String getAuditPerson() {
	return this.auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
	this.auditPerson = auditPerson;
    }

    public Date getAuditTime() {
	return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
	this.auditTime = auditTime;
    }

    public String getState() {
	return this.state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getComment() {
	return this.comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

}