package org.pmp.vo;

// Generated 2012-6-20 13:11:21 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * OwnerRepair generated by hbm2java
 */
public class OwnerRepair implements java.io.Serializable {

    private Integer opId;
    private HouseOwner houseOwner;
    private String opNum;
    private Date applyTime;
    private Date orderDate;
    private String orderTime;
    private String repairType;
    private String repairDetail;
    private String repairPerson;
    private String state;
    private Date finishDate;
    private String finishTime;
    private Boolean accepted;
    private String evaluate;
    private String evaluateDetail;
    private Double laborFee;
    private Double materialFee;
    private Double totalFee;
    private Boolean payed;

    public OwnerRepair() {
    }

    public OwnerRepair(Integer opId) {
	this.opId = opId;
    }

    public OwnerRepair(Integer opId, HouseOwner houseOwner, String opNum,
	    Date applyTime, Date orderDate, String orderTime,
	    String repairType, String repairDetail, String repairPerson,
	    String state, Date finishDate, String finishTime, Boolean accepted,
	    String evaluate, String evaluateDetail, Double laborFee,
	    Double materialFee, Double totalFee, Boolean payed) {
	this.opId = opId;
	this.houseOwner = houseOwner;
	this.opNum = opNum;
	this.applyTime = applyTime;
	this.orderDate = orderDate;
	this.orderTime = orderTime;
	this.repairType = repairType;
	this.repairDetail = repairDetail;
	this.repairPerson = repairPerson;
	this.state = state;
	this.finishDate = finishDate;
	this.finishTime = finishTime;
	this.accepted = accepted;
	this.evaluate = evaluate;
	this.evaluateDetail = evaluateDetail;
	this.laborFee = laborFee;
	this.materialFee = materialFee;
	this.totalFee = totalFee;
	this.payed = payed;
    }

    public Integer getOpId() {
	return this.opId;
    }

    public void setOpId(Integer opId) {
	this.opId = opId;
    }


    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public String getOpNum() {
	return this.opNum;
    }

    public void setOpNum(String opNum) {
	this.opNum = opNum;
    }

    public Date getApplyTime() {
	return this.applyTime;
    }

    public void setApplyTime(Date applyTime) {
	this.applyTime = applyTime;
    }

    public Date getOrderDate() {
	return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
    }

    public String getOrderTime() {
	return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
	this.orderTime = orderTime;
    }

    public String getRepairType() {
	return this.repairType;
    }

    public void setRepairType(String repairType) {
	this.repairType = repairType;
    }

    public String getRepairDetail() {
	return this.repairDetail;
    }

    public void setRepairDetail(String repairDetail) {
	this.repairDetail = repairDetail;
    }

    public String getRepairPerson() {
	return this.repairPerson;
    }

    public void setRepairPerson(String repairPerson) {
	this.repairPerson = repairPerson;
    }

    public String getState() {
	return this.state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public Date getFinishDate() {
	return this.finishDate;
    }

    public void setFinishDate(Date finishDate) {
	this.finishDate = finishDate;
    }

    public String getFinishTime() {
	return this.finishTime;
    }

    public void setFinishTime(String finishTime) {
	this.finishTime = finishTime;
    }

    public Boolean isAccepted() {
	return this.accepted;
    }

    public void setAccepted(Boolean accepted) {
	this.accepted = accepted;
    }

    public String getEvaluate() {
	return this.evaluate;
    }

    public void setEvaluate(String evaluate) {
	this.evaluate = evaluate;
    }

    public String getEvaluateDetail() {
	return this.evaluateDetail;
    }

    public void setEvaluateDetail(String evaluateDetail) {
	this.evaluateDetail = evaluateDetail;
    }

    public Double getLaborFee() {
	return this.laborFee;
    }

    public void setLaborFee(Double laborFee) {
	this.laborFee = laborFee;
    }

    public Double getMaterialFee() {
	return this.materialFee;
    }

    public void setMaterialFee(Double materialFee) {
	this.materialFee = materialFee;
    }

    public Double getTotalFee() {
	return this.totalFee;
    }

    public void setTotalFee(Double totalFee) {
	this.totalFee = totalFee;
    }

    public Boolean isPayed() {
	return this.payed;
    }

    public void setPayed(Boolean payed) {
	this.payed = payed;
    }

}
