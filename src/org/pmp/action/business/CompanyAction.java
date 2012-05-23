/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午02:34:09
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICompanyService;
import org.pmp.util.Pager;
import org.pmp.util.JsonConvert;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.TbRole;

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
	private Integer page;	
	private Integer rp;
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
    	Pager pager2 = new Pager(1000,1);
    	Map<String,Object> params = new HashMap<String,Object>();
    	String order = "order by comId asc";
    	companyList = companyService.loadCompanyList_ByChinaMobile(params, order, pager2);
    	return SUCCESS;
    }
    
    public void loadCompanyList(){
    	logger.debug("进入loadCompanyList");
    	Pager pager = new Pager(rp,page);
    	TbRole role = SessionHandler.getUserRole();
    	List<Company> companyList = new ArrayList<Company>();
    	//如果是公司管理员
    	if(role.getRoleLevel()==2)
    	{
    		Company com = (Company)SessionHandler.getUserRefDomain();
    		companyList.add(com);
    	}
    	else if(role.getRoleLevel()==1)
    	{  		
    		Pager pager2 = new Pager(1000,1);
    		Map<String,Object> params = new HashMap<String,Object>();
    		String order = "order by comId asc";
    		companyList = companyService.loadCompanyList_ByChinaMobile(params, order, pager2);
    	}
    	//invoke JsonConvert.list2Jason method to get JsonData
    	pager.setRowsCount(companyList.size());
    	String data = JsonConvert.list2FlexJson(pager, companyList, "org.pmp.vo.Company");
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


    
    public List<?> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<?> companyList) {
        this.companyList = companyList;
    }

    /**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the rp
	 */
	public Integer getRp() {
		return rp;
	}

	/**
	 * @param rp the rp to set
	 */
	public void setRp(Integer rp) {
		this.rp = rp;
	}

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

}
