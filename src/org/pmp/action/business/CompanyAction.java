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
    private Integer comid;
    private List<?> companyList;
    private String companyName;

    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
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
	companyService.deleteCompany(companyService.getCompanyByID(comid));
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
    	    String order = null;
    	    Map<String,Object> params = new HashMap<String,Object>();
    	    if (!qtype.equals("")&&!query.equals("")){
    		params.put(qtype, query);
    	    }
    	    if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
    		order= "order by "+sortname+" "+sortorder;
    	    } else{
    		order = "order by comName asc";
    	    }
    	    companyList = companyService.loadCompanyList_ByChinaMobile(params, order, pager);
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
    
    public void checkCompanyByName(){
	try{
	    companyName = new String(companyName.getBytes("ISO-8859-1"),"UTF-8");
    	}catch(Exception e){}
    	Company company = companyService.getCompanyByName(companyName);
    	String data = null;
    	if(company!=null){
    	    data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Failed")+"}";
        } else {
            data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Success")+"}";
    	}
     	logger.debug(data);
    	JsonConvert.output(data);
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

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
