package org.pmp.vo;

// Generated 2012-4-19 19:48:26 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * FixedParkFee generated by hbm2java
 */
public class FixedParkFee implements java.io.Serializable {

    private Integer fpfId;
    private FixedParkFeeItem fixedParkFeeItem;
    private Owner owner;
    private Date startDate;
    private Date endDate;
    private Date fetchDate;
    private Double parkFeeRate;
    private Double oughtMoney;
    private Double fetchMoney;
    private String fetchTicket;
    private String fetchPerson;
    private String recordPerson;
    private String auditPerson;
    private Date generateTime;
    private Date recordTime;
    private Date auditTime;
    private String state;

    public FixedParkFee() {
    }

    public FixedParkFee(Integer fpfId) {
	this.fpfId = fpfId;
    }

    public FixedParkFee(Integer fpfId, FixedParkFeeItem fixedParkFeeItem,
	    Owner owner, Date startDate, Date endDate, Date fetchDate,
	    Double parkFeeRate, Double oughtMoney, Double fetchMoney,
	    String fetchTicket, String fetchPerson, String recordPerson,
	    String auditPerson, Date generateTime, Date recordTime,
	    Date auditTime, String state) {
	this.fpfId = fpfId;
	this.fixedParkFeeItem = fixedParkFeeItem;
	this.owner = owner;
	this.startDate = startDate;
	this.endDate = endDate;
	this.fetchDate = fetchDate;
	this.parkFeeRate = parkFeeRate;
	this.oughtMoney = oughtMoney;
	this.fetchMoney = fetchMoney;
	this.fetchTicket = fetchTicket;
	this.fetchPerson = fetchPerson;
	this.recordPerson = recordPerson;
	this.auditPerson = auditPerson;
	this.generateTime = generateTime;
	this.recordTime = recordTime;
	this.auditTime = auditTime;
	this.state = state;
    }

    public Integer getFpfId() {
	return this.fpfId;
    }

    public void setFpfId(Integer fpfId) {
	this.fpfId = fpfId;
    }

    public FixedParkFeeItem getFixedParkFeeItem() {
	return this.fixedParkFeeItem;
    }

    public void setFixedParkFeeItem(FixedParkFeeItem fixedParkFeeItem) {
	this.fixedParkFeeItem = fixedParkFeeItem;
    }

    public Owner getOwner() {
	return this.owner;
    }

    public void setOwner(Owner owner) {
	this.owner = owner;
    }

    public Date getStartDate() {
	return this.startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getEndDate() {
	return this.endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public Date getFetchDate() {
	return this.fetchDate;
    }

    public void setFetchDate(Date fetchDate) {
	this.fetchDate = fetchDate;
    }

    public Double getParkFeeRate() {
	return this.parkFeeRate;
    }

    public void setParkFeeRate(Double parkFeeRate) {
	this.parkFeeRate = parkFeeRate;
    }

    public Double getOughtMoney() {
	return this.oughtMoney;
    }

    public void setOughtMoney(Double oughtMoney) {
	this.oughtMoney = oughtMoney;
    }

    public Double getFetchMoney() {
	return this.fetchMoney;
    }

    public void setFetchMoney(Double fetchMoney) {
	this.fetchMoney = fetchMoney;
    }

    public String getFetchTicket() {
	return this.fetchTicket;
    }

    public void setFetchTicket(String fetchTicket) {
	this.fetchTicket = fetchTicket;
    }

    public String getFetchPerson() {
	return this.fetchPerson;
    }

    public void setFetchPerson(String fetchPerson) {
	this.fetchPerson = fetchPerson;
    }

    public String getRecordPerson() {
	return this.recordPerson;
    }

    public void setRecordPerson(String recordPerson) {
	this.recordPerson = recordPerson;
    }

    public String getAuditPerson() {
	return this.auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
	this.auditPerson = auditPerson;
    }

    public Date getGenerateTime() {
	return this.generateTime;
    }

    public void setGenerateTime(Date generateTime) {
	this.generateTime = generateTime;
    }

    public Date getRecordTime() {
	return this.recordTime;
    }

    public void setRecordTime(Date recordTime) {
	this.recordTime = recordTime;
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

}
