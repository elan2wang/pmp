/**
 * Author            : Elan
 * Created On        : 2012-4-17 下午12:35:22
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.ISmsCompanyService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.SMSCompany;
import org.pmp.vo.TbRole;

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
    /* used when batchDeleteSMSCompany */
    private String idStr;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
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
	smsCompanyService.delereSMSCompany(smsCompanyService.getSMSCompanyByID(smscId));
	return SUCCESS;
    }
    
    public void batchDeleteSMSCompany(){
    	List<SMSCompany> smscompany = new ArrayList<SMSCompany>();
    	String []ids = idStr.split(",");
    	for(int i=0;i<ids.length;++i){
    	    SMSCompany smscom = smsCompanyService.getSMSCompanyByID(Integer.parseInt(ids[i]));
    	    smscompany.add(smscom);
    	}
    	smsCompanyService.batchDeleteSMSCompany(smscompany);
     }
    
    public String getSMSCompany(){
	SMSCompany smsCompany = smsCompanyService.getSMSCompanyByID(smscId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("smsCompany", smsCompany);
	return SUCCESS;
    }
    
    public void loadSMSCompanyList(){
    	Pager pager = new Pager(rp,page);
    	Object obj = SessionHandler.getUserRole();
    	TbRole role = (TbRole)obj;
    	List<SMSCompany> smsCompanyList = null;
    	//移动公司管理员
    	if(role.getRoleLevel()==1)
    	    smsCompanyList = smsCompanyService.loadSMSCompanyList(pager);
    	else
    	    smsCompanyList = new ArrayList<SMSCompany>();
	
    	String data = JsonConvert.list2FlexJson(pager, smsCompanyList, "org.pmp.vo.SMSCompany");
	logger.debug(data);
	JsonConvert.output(data);
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

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRp() {
        return rp;
    }

    public void setRp(Integer rp) {
        this.rp = rp;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }
}
