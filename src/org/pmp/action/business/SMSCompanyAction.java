/**
 * Author            : Elan
 * Created On        : 2012-4-17 下午12:35:22
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.ISMSCompanyService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.SMSCompany;
import org.pmp.vo.TbRole;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SMSCompanyAction extends BaseAction {

    private static final long serialVersionUID = 4641286597063236773L;
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SMSCompanyAction.class.getName());
    //~ Instance Fields ================================================================================================
    private ISMSCompanyService smsCompanyService;
    private ICompanyService companyService;
    
    private SMSCompany smsCompany;
    private Integer comId;
    private Integer smscId;
    /* used when batchDeleteSMSCompany */
    private String idStr;
    
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
    	Map<String,String> params = new HashMap<String, String>();
    	List<SMSCompany> smscompany = new ArrayList<SMSCompany>();
    	String []ids = idStr.split(",");
    	for(int i=0;i<ids.length;++i){
    	    SMSCompany smscom = smsCompanyService.getSMSCompanyByID(Integer.parseInt(ids[i]));
    	    smscompany.add(smscom);
    	}
    	smsCompanyService.batchDeleteSMSCompany(smscompany);
    	MyJson json = new MyJson();
    	params.put("msg", "短信平台删除成功");
    	MyJson.print(json.toJson(params));
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
	
    	String[] attrs= {"smscId","company.comName","smscName","smsUpUrl","smsDownUrl","username","extendCode"};
    	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(smsCompanyList, "", pager);
	MyJson.print(data);
    }

   
    //~ Getters and Setters ============================================================================================
    public ISMSCompanyService getSmsCompanyService() {
        return smsCompanyService;
    }

    public void setSmsCompanyService(ISMSCompanyService smsCompanyService) {
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
}
