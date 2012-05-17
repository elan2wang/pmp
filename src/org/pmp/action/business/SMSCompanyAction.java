/**
 * Author            : Elan
 * Created On        : 2012-4-17 下午12:35:22
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.ISmsCompanyService;
import org.pmp.util.Pager;
import org.pmp.vo.Company;
import org.pmp.vo.SMSCompany;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsCompanyAction extends ActionSupport {

    private static final long serialVersionUID = 4641286597063236773L;
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SmsCompanyAction.class.getName());
    //~ Instance Fields ================================================================================================
    private ISmsCompanyService smsCompanyService;
    private ICompanyService companyService;
    
    private SMSCompany smsCompany;
    private Integer comId;
    private Integer smscId;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String addSMSCompany(){
	Company com = companyService.getCompanyByID(comId);
	smsCompany.setCompany(com);
	
	smsCompanyService.addSMSCompany(smsCompany);
	
	return SUCCESS;
    }
    
    public String editSMSCompany(){
	Company com = companyService.getCompanyByID(comId);
	smsCompany.setCompany(com);
	
	smsCompanyService.editSMSCompany(smsCompany);
	
	return SUCCESS;
    }
    
    public String deleteSMSCompany(){
	smsCompanyService.delereSMSCompany(smscId);
	return SUCCESS;
    }
    
    public String getSMSCompany(){
	SMSCompany smsCompany = smsCompanyService.getSMSCompanyByID(smscId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("smsCompany", smsCompany);
	return SUCCESS;
    }
    
    public String loadSMSCompanyList(){
	Pager pager = new Pager(1000,1);
	List<?> smsCompanyList = smsCompanyService.loadSMSCompanyList(pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("smsCompanyList", smsCompanyList);
	return SUCCESS;
    }

   
    //~ Getters and Setters ============================================================================================
    public ISmsCompanyService getSmsCompanyService() {
        return smsCompanyService;
    }

    public void setSmsCompanyService(ISmsCompanyService smsCompanyService) {
        this.smsCompanyService = smsCompanyService;
    }

    public SMSCompany getSmsCompany() {
        return smsCompany;
    }

    public void setSmsCompany(SMSCompany smsCompany) {
        this.smsCompany = smsCompany;
    }

    public Integer getSmscId() {
        return smscId;
    }

    public void setSmscId(Integer smscId) {
        this.smscId = smscId;
    }

    public ICompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(ICompanyService companyService) {
        this.companyService = companyService;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }
    
}
