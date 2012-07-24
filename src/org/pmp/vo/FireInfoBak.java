package org.pmp.vo;

// Generated 2012-5-25 12:51:23 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * FireInfo generated by hbm2java
 */
public class FireInfoBak implements java.io.Serializable {

    private Integer fireId;
    private Zone zone;
    private String deviceNumber;
    private Date receiveTime;
    private String receiveInfo;
    private Integer state;
    private Date disposeTime;
    private String operator;

    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+fireId+","+zone+","+deviceNumber+","+receiveTime+","+receiveInfo+","+state+","+disposeTime+","+operator+" ]");
    	return sb.toString();
    }
    
    public FireInfoBak() {
    }

    public FireInfoBak(Integer fireId) {
	this.fireId = fireId;
    }

    public FireInfoBak(Integer fireId, Zone zone, String deviceNumber,
	    Date receiveTime, String receiveInfo, Integer state,Date disposeTime,String operator) {
	this.fireId = fireId;
	this.zone = zone;
	this.deviceNumber = deviceNumber;
	this.receiveTime = receiveTime;
	this.receiveInfo = receiveInfo;
	this.state = state;
	this.disposeTime=disposeTime;
	this.operator=operator;
    }

    public Integer getFireId() {
	return this.fireId;
    }

    public void setFireId(Integer fireId) {
	this.fireId = fireId;
    }
    
    public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getDeviceNumber() {
	return this.deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
	this.deviceNumber = deviceNumber;
    }

    public Date getReceiveTime() {
	return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
	this.receiveTime = receiveTime;
    }

    public String getReceiveInfo() {
	return this.receiveInfo;
    }

    public void setReceiveInfo(String receiveInfo) {
	this.receiveInfo = receiveInfo;
    }

    public Integer getState() {
	return this.state;
    }

    public void setState(Integer state) {
	this.state = state;
    }

	public Date getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(Date disposeTime) {
		this.disposeTime = disposeTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}

