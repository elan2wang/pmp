/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午02:34:09
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.MyJson;
import org.pmp.service.business.ICompanyService;
import org.pmp.util.Pager;
import org.pmp.util.JsonConvert;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.TbRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CompanyAction extends BaseAction {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CompanyAction.class.getName ());
    //~ Instance Fields ================================================================================================
    private ICompanyService companyService;
    private Company company;
    
    //~ URL Fields
    private Integer comid;
    private String companyName;

    //~ Methods ========================================================================================================
    public String addCompany(){
	company.setEnabled(true);
	companyService.addCompany(company);
	return SUCCESS;
    }
    
    public String editCompany(){
	companyService.editCompany(company);
	return SUCCESS;
    }
    
    public void deleteCompany(){
    	companyService.deleteCompany(companyService.getCompanyByID(comid));
    	Map<String,String> result = new HashMap<String, String>();
    	MyJson json = new MyJson();
    	result.put("msg", "物业公司删除成功");
    	json.output(json.toJson(result));
    }
    
    public void loadCompanyList(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	
    	TbRole role = SessionHandler.getUserRole();
    	List<Company> companyList = new ArrayList<Company>();
    	//如果是公司管理员
    	if(role.getRoleLevel()==2)
    	{
    	    Company com = (Company)SessionHandler.getUserRefDomain();
    	    companyList.add(com);
    	}
    	else if(role.getRoleLevel()==1){
    	    companyList = companyService.loadCompanyList_ByChinaMobile(params, order, pager);
    	}
    	
    	MyJson json = new MyJson();
    	String data = json.toJson(companyList, "", pager);
    	MyJson.print(data);
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

}
