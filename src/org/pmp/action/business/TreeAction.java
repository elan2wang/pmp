/**
 * Author            : Elan
 * Created On        : 2012-5-8 上午09:22:17
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
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.service.business.ICondoFeeService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
import org.pmp.vo.House;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class TreeAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(TreeAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IProjectService projectService;
    private IBuildingService buildingService;
    private IHouseService houseService;
    private ICondoFeeItemService condoFeeItemService;
    
    //~ Methods ========================================================================================================
    public void houseTree(){
	Object obj = SessionHandler.getUserRefDomain();
	List<String> nodes = new ArrayList<String>();
	Pager pager = new Pager(10000,1);
	List<Project> proList = new ArrayList<Project>();
	
	if (obj instanceof Company){
	    proList = (List<Project>) projectService.loadProjectByComID(pager, ((Company)obj).getComId());
	}
	if (obj instanceof Project){
	    Project pro = (Project)obj;
	    proList.add(pro);
	}
	Iterator<Project> ite = proList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Project pro = ite.next();
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "", 
		    "", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	    List<Building> builList = buildingService.loadBuildingListByProject(pager, pro.getProId());
	    Iterator<Building> ite1 = builList.iterator();
	    Integer pid1 = index-1;
	    while(ite1.hasNext()){
		Building buil = ite1.next();
		nodes.add(JsonConvert.toJsonTreeNode(index++, pid1, buil.getBuilNum()+"号楼", "", 
			    buil.getBuilType(), "", "../Images/dtree/buil.jpg", "../Images/dtree/buil.jpg", false));
		List<House> houseList = houseService.getHouseByBuilding(buil);
		Iterator<House> ite2 = houseList.iterator();
		Integer pid2 = index-1;
		while(ite2.hasNext()){
		    House house = ite2.next();
		    
		    nodes.add(JsonConvert.toJsonTreeNode(index++, pid2, house.getHouseNum(), "loadCondoFeeList_ByHouse?houseId="+house.getHouseId(),
			    "", "condoFeeList", "../Images/dtree/house.jpg", "../Images/dtree/house.jpg", false));
		}
            }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public void monthTree(){
	/* get current user's refDomain */
	Object obj = SessionHandler.getUserRefDomain();
        /* tree nodes */
	List<String> nodes = new ArrayList<String>();
	/* get all the year that has condoFee item */
	List<?> yearList = condoFeeItemService.getCondoFeeYear();
	Iterator<Integer> ite = (Iterator<Integer>) yearList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Integer year = ite.next();
	    /* add the first level node year */
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, year.toString()+"年物业费清单", "",
		    "", "", "", "", false));
	    /* add second level node month */
	    Integer pid=index-1;
	    Integer month = 13;
	    while(month-- > 1){
		StringBuilder url = new StringBuilder();
		if (obj instanceof Project){
		    url.append("loadCondoFeeList_ByProject?proId="+((Project)obj).getProId());
		} else if (obj instanceof Company){
		    url.append("loadCondoFeeList_ByCompany?comId="+((Company)obj).getComId());
		}
		nodes.add(JsonConvert.toJsonTreeNode(index++, pid, month+"月份清单", 
			url.append("&year="+year+"&month="+month).toString(),"", "condoFeeList", "", "", false));
	    }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    //~ Getters and Setters ============================================================================================
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
    public IHouseService getHouseService() {
        return houseService;
    }
    public void setHouseService(IHouseService houseService) {
        this.houseService = houseService;
    }

    public ICondoFeeItemService getCondoFeeItemService() {
        return condoFeeItemService;
    }

    public void setCondoFeeItemService(ICondoFeeItemService condoFeeItemService) {
        this.condoFeeItemService = condoFeeItemService;
    }
    
}
