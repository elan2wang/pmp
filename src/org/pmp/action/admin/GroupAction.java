/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:32:26
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IGroupService;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
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
    private IProjectService projectService;
    
    private TbGroup group;
    
    private Integer groupId;
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    
    private Integer level;
    private Integer fatherGroup;
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public String addGroup(){
	groupService.addGroup(group);
	return SUCCESS;
    }

    public String getGroupByID(){
	TbGroup group = groupService.getGroupByID(groupId);
	HttpServletRequest request = ServletActionContext.getRequest();
	
	if (group.getGroupLevel().equals(3)){
	    Pager pager = new Pager(1000,1);
	    List<?> groupList = groupService.getGroupListByLevel(pager, 2);
            logger.debug("groupList.size="+groupList.size());
            
	    TbGroup father = groupService.getGroupByID(group.getFatherGroupId());
	    logger.debug("fatherID="+group.getFatherGroupId());
	    Integer comId = companyService.getCompanyByName(father.getRefDomain()).getComId();
	    logger.debug("comID="+comId);
	    List<?> projectList = projectService.loadProjectList_ByCompany(comId, new HashMap<String,Object>(), "", pager);
	    logger.debug("projectList.size="+projectList.size());
	    request.setAttribute("groupList", groupList);
	    request.setAttribute("projectList", projectList);
	}
	if (group.getGroupLevel().equals(2)){
	    Pager pager = new Pager(1000,1);
	    List<?> companyList = companyService.loadCompanyList(pager);
	    request.setAttribute("companyList", companyList);
	}
	
	request.setAttribute("group", group);
	
	return SUCCESS;
    }
    
    public String editGroup(){
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
    
    public void levelChange(){
	logger.debug("进入levelChange");
	if (level.equals(2)){
	    Pager pager = new Pager(1000,1);
	    List<?> companyList = companyService.loadCompanyList(pager);
	    logger.debug("companyList.size="+companyList.size());
	    String[] attrs = {"comId","comName"};
	    List<String> show = Arrays.asList(attrs);
	    String data = JsonConvert.list2Json(companyList, "org.pmp.vo.Company", show);
	    logger.debug(data);
            //output the JsonData
            JsonConvert.output(data);
	} else if(level.equals(3)){
            Pager pager = new Pager(1000,1);
            List<?> groupList = groupService.getGroupListByLevel(pager, level-1);
            Integer comId = companyService.getCompanyByName(((TbGroup)groupList.get(0)).getRefDomain()).getComId();
            List<?> proList = projectService.loadProjectList_ByCompany(comId, new HashMap<String,Object>(), "", pager);
            String[] attrs1 = {"groupId","groupName"};
            String[] attrs2 = {"proId","proName"};
            List<String> show1 = Arrays.asList(attrs1);
            List<String> show2 = Arrays.asList(attrs2);
            
            String data1 = JsonConvert.list2Json(groupList, "org.pmp.vo.TbGroup", show1, "group");
            String data2 = JsonConvert.list2Json(proList, "org.pmp.vo.Project", show2, "project");
            String[] data = {data1,data2}; 
            JsonConvert.output(JsonConvert.merge(data));
	}
    }
   
    public void fatherChange(){
	Pager pager = new Pager(1000,1);
	String comName = ((TbGroup)groupService.getGroupByID(fatherGroup)).getRefDomain();
	Integer comId = companyService.getCompanyByName(comName).getComId();
	List<?> proList = projectService.loadProjectList_ByCompany(comId, new HashMap<String,Object>(), "", pager);
        String[] attrs = {"proId","proName"};
        List<String> show = Arrays.asList(attrs);
        String data = JsonConvert.list2Json(proList, "org.pmp.vo.Project", show);
        logger.debug(data);
        JsonConvert.output(data);
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    public Integer getFatherGroup() {
        return fatherGroup;
    }

    public void setFatherGroup(Integer fatherGroup) {
        this.fatherGroup = fatherGroup;
    }

}
