package org.pmp.vo;

// Generated 2012-6-20 13:11:21 by Hibernate Tools 3.4.0.CR1

/**
 * RepairFee generated by hbm2java
 */
public class RepairFee implements java.io.Serializable {

    private Integer rfId;
    private OwnerRepair ownerRepair;
    private String rfName;
    private Integer amount;
    private Double money;
    private String comment;

    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+rfId+","+ownerRepair+","+rfName+","+amount+","+money+","+comment);
    	sb.append(" ]");
    	return sb.toString();
    }
    
    public RepairFee() {
    }

    public RepairFee(Integer rfId) {
	this.rfId = rfId;
    }

    public RepairFee(Integer rfId, OwnerRepair ownerRepair, String rfName,
	    Integer amount, Double money, String comment) {
	this.rfId = rfId;
	this.ownerRepair = ownerRepair;
	this.rfName = rfName;
	this.amount = amount;
	this.money = money;
	this.comment = comment;
    }

    public Integer getRfId() {
	return this.rfId;
    }

    public void setRfId(Integer rfId) {
	this.rfId = rfId;
    }

    public OwnerRepair getOwnerRepair() {
	return this.ownerRepair;
    }

    public void setOwnerRepair(OwnerRepair ownerRepair) {
	this.ownerRepair = ownerRepair;
    }

    public String getRfName() {
	return this.rfName;
    }

    public void setRfName(String rfName) {
	this.rfName = rfName;
    }

    public Integer getAmount() {
	return this.amount;
    }

    public void setAmount(Integer amount) {
	this.amount = amount;
    }

    public Double getMoney() {
	return this.money;
    }

    public void setMoney(Double money) {
	this.money = money;
    }

    public String getComment() {
	return this.comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

}
