/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:32:26
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IGroupService;
import org.pmp.service.business.ICompanyService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.vo.Company;
import org.pmp.vo.TbGroup;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class GroupAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(GroupAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IGroupService groupService;
    private ICompanyService companyService;
    
    private TbGroup group;
    
    private Integer groupId;
    
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public String addGroup(){
	if(group.getGroupLevel().equals(1)){
	    group.setFatherGroupId(0);
	    group.setRefDomain("系统层");
	}
	else if(group.getGroupLevel().equals(2)){
	    group.setFatherGroupId(0);
	}
	groupService.addGroup(group);
	return SUCCESS;
    }

    public String getGroupByID(){
	TbGroup group = groupService.getGroupByID(groupId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("group", group);
	
	return SUCCESS;
    }
    
    public String editGroup(){
	if(group.getGroupLevel().equals(1)){
	    group.setFatherGroupId(0);
	    group.setRefDomain("系统层");
	}
	else if(group.getGroupLevel().equals(2)){
	    group.setFatherGroupId(0);
	}
	groupService.editGroup(group);
	return SUCCESS;
    }
    
    public String deleteGroup(){
	groupService.deleteGroup(groupId);
	return SUCCESS;
    }
    
    public String loadGroupList(){
	Pager pager = new Pager(1000,1);
	List groupList = groupService.getGroupList(pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("groupList", groupList);
	
	return SUCCESS;
    }
    
    public void loadFatherGroupList(){
	logger.debug("进入loadFatherGroupList");
	Pager pager = new Pager(1000,1);
	List list = groupService.getGroupListByLevel(pager, group.getGroupLevel()-1);
	
	Iterator ite = list.iterator();
	StringBuffer sb = new StringBuffer();
	if (ite.hasNext()){
	    sb.append("{\n  ");
	    sb.append(JsonConvert.toJson("Rows")+":[\n");
	    while(ite.hasNext()){
		TbGroup group = (TbGroup)ite.next();
		sb.append("    {");
		sb.append(JsonConvert.toJson("groupId")+":"+JsonConvert.toJson(group.getGroupId())+",");
		sb.append(JsonConvert.toJson("groupName")+":"+JsonConvert.toJson(group.getGroupName())+"},\n");
	    }
	    sb.deleteCharAt(sb.length()-2);
    	    sb.append("  ]\n}"); 
	}
        logger.debug(sb.toString());
	
	//output the Jason data
	try {    
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(sb.toString());
        } catch (IOException e) {                     
            e.printStackTrace();  
        } 
    }
   
    public void loadRefDomain(){
	logger.debug("进入loadRefDomain");
	StringBuffer sb = new StringBuffer();
	if (group.getGroupLevel().equals(2)){
	    Pager pager = new Pager(1000,1);
	    List companyList = companyService.loadCompanyList(pager);
	    logger.debug("companyList.size="+companyList.size());
	    Iterator ite = companyList.iterator();
	    if (ite.hasNext()){
		sb.append("{\n  ");
		sb.append(JsonConvert.toJson("Rows")+":[\n");
		while(ite.hasNext()){
	            Company company = (Company)ite.next();
		    sb.append("    {");
		    sb.append(JsonConvert.toJson("comId")+":"+JsonConvert.toJson(company.getComId())+",");
		    sb.append(JsonConvert.toJson("comName")+":"+JsonConvert.toJson(company.getComName())+"},\n");
                }
		sb.deleteCharAt(sb.length()-2);
	        sb.append("  ]\n}"); 
            }
	    logger.debug(sb.toString());
	}
	//output the Jason data
	try {
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
	    response.getWriter().println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    //~ Getters and Setters ============================================================================================
    public IGroupService getGroupService() {
        return groupService;
    }
    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }
    public TbGroup getGroup() {
        return group;
    }
    public void setGroup(TbGroup group) {
        this.group = group;
    }
  
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ICompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(ICompanyService companyService) {
        this.companyService = companyService;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
