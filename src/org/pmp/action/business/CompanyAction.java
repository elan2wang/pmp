/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午02:34:09
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICompanyService;
import org.pmp.util.Pager;
import org.pmp.util.JsonConvert;
import org.pmp.vo.Company;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CompanyAction extends ActionSupport {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CompanyAction.class.getName ());
    //~ Instance Fields ================================================================================================
    private ICompanyService companyService;
    private Company company;
    
    //~ URL Fields
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    private Integer comid;
    private List<?> companyList;

    //~ Methods ========================================================================================================
    public String addCompany(){
	company.setEnabled(true);
	companyService.addCompany(company);
	return SUCCESS;
    }
    
    public String updateCompany(){
	companyService.editCompany(company);
	return SUCCESS;
    }
    
    public String deleteCompany(){
	companyService.deleteCompany(comid);
	return SUCCESS;
    }
    
    public String getAllCompany(){
	Pager pager = new Pager(1000,1);
    	companyList = companyService.loadCompanyList(pager);
    	return SUCCESS;
    }
    
    public void loadCompanyList(){
	logger.debug("进入loadCompanyList");
	Pager pager = new Pager(pageSize,currentPage);
	List<?> companyList = companyService.loadCompanyList(pager);
	
	//invoke JsonConvert.list2Jason method to get JsonData
	String data = JsonConvert.list2Json(pager, companyList, "org.pmp.vo.Company");
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public String getCompanyByID(){
	HttpServletRequest request = ServletActionContext.getRequest();
	Company company = companyService.getCompanyByID(comid);
	
	request.setAttribute("company", company);
	return SUCCESS;
    }
    
    //~ Getters and Setters ============================================================================================

    public ICompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(ICompanyService companyService) {
        this.companyService = companyService;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    
    public List<?> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<?> companyList) {
        this.companyList = companyList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

}
