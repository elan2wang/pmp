/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午02:19:36
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
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
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PageTriggerAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PageTriggerAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IProjectService projectService;
    private IHouseService houseService;
    private IBuildingService buildingService;
    
    private Integer proId;
    private Integer builId;
    
    //~ Methods ========================================================================================================
    public String selectProject_ByAuth(){
	List<Project> list = null;
	Object obj = SessionHandler.getUserRefDomain();
	if (obj instanceof Company){
	    Pager pager = new Pager(1000,1);
	    list = projectService.loadProjectList_ByCompany(((Company)obj).getComId(), new HashMap<String,Object>(), "", pager);
	} else{
	    list = new ArrayList<Project>();
	    list.add((Project)obj);
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("proList", list);
	return SUCCESS;
    }
    
    public void selectHouse_ByBuil(){
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("isempty", 1);
        List<?> houseList = houseService.loadHouseList_ByBuilding(builId, map, "order by houseNum asc", new Pager(1000,1));
        String[] attrs = {"houseId","houseNum"};
        List<String> show = Arrays.asList(attrs);
        String data = JsonConvert.list2Json(houseList, "org.pmp.vo.House", show);
        JsonConvert.output(data);
    }
    
    public void selectBuilding_ByPro(){
	List<?> buildingList = buildingService.loadBuildingList_ByProject(proId, new HashMap<String,Object>(), "order by builNum asc", new Pager(1000,1));
	String[] attrs = {"builId","builNum"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2Json(buildingList, "org.pmp.vo.Building", show);
	JsonConvert.output(data);
    }
    //~ Getters and Setters ============================================================================================

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    public IHouseService getHouseService() {
        return houseService;
    }

    public void setHouseService(IHouseService houseService) {
        this.houseService = houseService;
    }

    public IBuildingService getBuildingService() {
        return buildingService;
    }

    public void setBuildingService(IBuildingService buildingService) {
        this.buildingService = buildingService;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getBuilId() {
        return builId;
    }

    public void setBuilId(Integer builId) {
        this.builId = builId;
    }

}
