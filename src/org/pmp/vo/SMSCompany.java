package org.pmp.vo;

// Generated 2012-4-19 19:48:26 by Hibernate Tools 3.4.0.CR1

/**
 * SMS_Company generated by hbm2java
 */
public class SMSCompany implements java.io.Serializable {

    private Integer smscId;
    private Company company;
    private String smscName;
    private String smsUpUrl;
    private String smsDownUrl;
    private String username;
    private String password;
    private String extendCode;

    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+smscId+","+company+","+smscName+","+smsUpUrl+","+smsDownUrl+","+username);
    	sb.append(","+password+","+extendCode);
    	sb.append(" ]");
    	return sb.toString();
    }
    
    public SMSCompany() {
    }

    public SMSCompany(Integer smscId) {
	this.smscId = smscId;
    }

    public SMSCompany(Integer smscId, Company company, String smscName,
	    String smsUpUrl, String smsDownUrl, String username,
	    String password, String extendCode) {
	this.smscId = smscId;
	this.company = company;
	this.smscName = smscName;
	this.smsUpUrl = smsUpUrl;
	this.smsDownUrl = smsDownUrl;
	this.username = username;
	this.password = password;
	this.extendCode = extendCode;
    }

    public Integer getSmscId() {
	return this.smscId;
    }

    public void setSmscId(Integer smscId) {
	this.smscId = smscId;
    }

    public Company getCompany() {
	return this.company;
    }

    public void setCompany(Company company) {
	this.company = company;
    }

    public String getSmscName() {
	return this.smscName;
    }

    public void setSmscName(String smscName) {
	this.smscName = smscName;
    }

    public String getSmsUpUrl() {
	return this.smsUpUrl;
    }

    public void setSmsUpUrl(String smsUpUrl) {
	this.smsUpUrl = smsUpUrl;
    }

    public String getSmsDownUrl() {
	return this.smsDownUrl;
    }

    public void setSmsDownUrl(String smsDownUrl) {
	this.smsDownUrl = smsDownUrl;
    }

    public String getUsername() {
	return this.username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getExtendCode() {
	return this.extendCode;
    }

    public void setExtendCode(String extendCode) {
	this.extendCode = extendCode;
    }

}
