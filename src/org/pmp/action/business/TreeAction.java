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
	Integer i = 1;
	while(ite.hasNext()){
	    Project pro = ite.next();
	    nodes.add(JsonConvert.toJsonTreeNode(i, 0, pro.getProName(), "loadList?proId="+pro.getProId(), 
		    "", "", "", "", false));
	    List<Building> builList = buildingService.loadBuildingListByProject(pager, pro.getProId());
	    Iterator<Building> ite1 = builList.iterator();
	    Integer j=i+1;
	    while(ite1.hasNext()){
		Building buil = ite1.next();
		nodes.add(JsonConvert.toJsonTreeNode(j, i, buil.getBuilNum()+"号楼", "loadList?builId="+buil.getBuilId(), 
			    buil.getBuilType(), "", "", "", false));
		List<House> houseList = houseService.getHouseByBuilding(buil);
		Iterator<House> ite2 = houseList.iterator();
		Integer k = j+1;
		while(ite2.hasNext()){
		    House house = ite2.next();
		    nodes.add(JsonConvert.toJsonTreeNode(k, j, house.getHouseNum(), "loadList?houseId="+house.getHouseId(),
			    "", "", "", "", false));
		    k++;
		}
		j++;
            }
	    i++;
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
    
}
