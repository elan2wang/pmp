package org.pmp.vo;

// Generated 2012-6-20 13:11:21 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * RepairAttach generated by hbm2java
 */
public class RepairAttach implements java.io.Serializable {

    private Integer raId;
    private OwnerRepair ownerRepair;
    private String attachName;
    private String attachUrl;
    private Date uploadTime;
    private String uploadPerson;

    public RepairAttach() {
    }

    public RepairAttach(Integer raId) {
	this.raId = raId;
    }

    public RepairAttach(Integer raId, OwnerRepair ownerRepair,
	    String attachName, String attachUrl, Date uploadTime,
	    String uploadPerson) {
	this.raId = raId;
	this.ownerRepair = ownerRepair;
	this.attachName = attachName;
	this.attachUrl = attachUrl;
	this.uploadTime = uploadTime;
	this.uploadPerson = uploadPerson;
    }

    public Integer getRaId() {
	return this.raId;
    }

    public void setRaId(Integer raId) {
	this.raId = raId;
    }

    public OwnerRepair getOwnerRepair() {
	return this.ownerRepair;
    }

    public void setOwnerRepair(OwnerRepair ownerRepair) {
	this.ownerRepair = ownerRepair;
    }

    public String getAttachName() {
	return this.attachName;
    }

    public void setAttachName(String attachName) {
	this.attachName = attachName;
    }

    public String getAttachUrl() {
	return this.attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
	this.attachUrl = attachUrl;
    }

    public Date getUploadTime() {
	return this.uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
	this.uploadTime = uploadTime;
    }

    public String getUploadPerson() {
	return this.uploadPerson;
    }

    public void setUploadPerson(String uploadPerson) {
	this.uploadPerson = uploadPerson;
    }

}