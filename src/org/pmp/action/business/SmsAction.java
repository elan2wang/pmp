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

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> a95eeeb2b3e02abe6366158d5e4d9caaf6381304
import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IOwnerService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
<<<<<<< HEAD
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;
=======
>>>>>>> a95eeeb2b3e02abe6366158d5e4d9caaf6381304

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
<<<<<<< HEAD
		List<?> list = null;
		Pager pager = new Pager(10000,1);
		if (proId!=null && builId!=null){
			list = ownerService.loadOwnerList_ByBuilding(builId, pager);
		} else if (proId!=null && builId==null){
			list = ownerService.loadOwnerList_ByProject(proId, pager);
		} else {
			list = new ArrayList<Owner>();;
		}
		if(list!=null && list.size()!=0)
		{
			String data = JsonConvert.list2Json(list, "org.pmp.vo.Owner");
			logger.debug(data);
			JsonConvert.output(data);
		}
		else
		{
			System.out.println("list is null");
		}		
=======
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
>>>>>>> a95eeeb2b3e02abe6366158d5e4d9caaf6381304
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
