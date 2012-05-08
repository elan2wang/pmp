/**
 * Author            : Elan
 * Created On        : 2012-5-8 下午03:26:51
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

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IOwnerService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsAction {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(SmsAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IOwnerService ownerService;
    private IProjectService projectService;
    private IBuildingService buildingService;

    private Integer proId;
    private Integer builId;
    
    //~ Methods ========================================================================================================
    public void load_owner_list(){
	List<?> list = null;
	Pager pager = new Pager(10000,1);
	if (proId==null && builId==null){
	    list = ownerService.loadOwnerList(pager);
	} else if (proId!=null && builId==null){
	    list = ownerService.loadOwnerList_ByProject(proId, pager);
	} else {
	    list = ownerService.loadOwnerList_ByBuilding(builId, pager);
	}
	
	String data = JsonConvert.list2Json(list, "org.pmp.vo.Owner");
	logger.debug(data);
	JsonConvert.output(data);
    }
    //~ Getters and Setters ============================================================================================

    public IOwnerService getOwnerService() {
        return ownerService;
    }

    public void setOwnerService(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
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
