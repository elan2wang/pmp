package org.pmp.vo;

// Generated 2012-6-20 13:11:21 by Hibernate Tools 3.4.0.CR1

/**
 * ElectricFee generated by hbm2java
 */
public class ElectricFee implements java.io.Serializable {

    private Integer efId;
    private ElectricFeeItem electricFeeItem;
    private HouseOwner houseOwner;
    private Double proMeterFee;
    private Double liftMeterFee;
    private Double totalMoney;
    
    private String comment;

    public ElectricFee() {
    }

    public ElectricFee(Integer efId) {
	this.efId = efId;
    }

    public ElectricFee(Integer efId, ElectricFeeItem electricFeeItem,
	    HouseOwner houseOwner, Double proMeterFee, Double liftMeterFee,
	    Double totalMoney, String comment) {
	this.efId = efId;
	this.electricFeeItem = electricFeeItem;
	this.houseOwner = houseOwner;
	this.proMeterFee = proMeterFee;
	this.liftMeterFee = liftMeterFee;
	this.totalMoney = totalMoney;
	this.comment = comment;
    }

    public Integer getEfId() {
	return this.efId;
    }

    public void setEfId(Integer efId) {
	this.efId = efId;
    }

    public ElectricFeeItem getElectricFeeItem() {
	return this.electricFeeItem;
    }

    public void setElectricFeeItem(ElectricFeeItem electricFeeItem) {
	this.electricFeeItem = electricFeeItem;
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Double getProMeterFee() {
        return proMeterFee;
    }

    public void setProMeterFee(Double proMeterFee) {
        this.proMeterFee = proMeterFee;
    }

    public Double getLiftMeterFee() {
        return liftMeterFee;
    }

    public void setLiftMeterFee(Double liftMeterFee) {
        this.liftMeterFee = liftMeterFee;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getComment() {
	return this.comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

}
