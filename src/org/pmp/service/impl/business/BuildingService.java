/**
 * Author            : Elan
 * Created On        : 2012-6-28 上午10:53:54
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.impl.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IBuildingDAO;
import org.pmp.dao.business.IHouseDAO;
import org.pmp.service.business.IBuildingService;
import org.pmp.util.Pager;
import org.pmp.vo.Building;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class BuildingService implements IBuildingService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(BuildingService.class.getName());

    //~ Instance Fields ================================================================================================
    private IBuildingDAO buildingDAO;
    private IHouseDAO houseDAO;
    
    //~ Methods ========================================================================================================
   
    /**
     * @see org.pmp.service.business.IBuildingService#batchAddBuilding(java.util.List)
     */
    @Override
    public void batchAddBuilding(List<Building> buildingList) {
	/* 批量保存楼宇信息,并获得自动生成的楼宇编号 */
	List<Integer> idList = buildingDAO.batchSaveBuilding(buildingList);
	
	/* 调用存储过程生成房屋信息 */
	houseDAO.generateHouse(idList);
    }

    /**
     * @see org.pmp.service.business.IBuildingService#addBuilding(org.pmp.vo.Building)
     */
    @Override
    public void addBuilding(Building building) {
	/* 保存楼宇信息 */
	buildingDAO.saveBuilding(building);
	List<Integer> idList = new ArrayList<Integer>();
	idList.add(building.getBuilId());
	/* 调用存储过程生成房屋信息 */
	houseDAO.generateHouse(idList);
    }

    /**
     * @see org.pmp.service.business.IBuildingService#editBuilding(org.pmp.vo.Building)
     */
    @Override
    public void editBuilding(Building building) {
	buildingDAO.updateBuilding(building);
    }

    /**
     * @see org.pmp.service.business.IBuildingService#deleteBuilding(org.pmp.vo.Building)
     */
    @Override
    public void deleteBuilding(Building building) {
	buildingDAO.deleteBuilding(building);
    }
    
    /**
     * @see org.pmp.service.business.IBuildingService#getBuildingById(java.lang.Integer)
     */
    @Override
    public Building getBuildingById(Integer builId) {
	return buildingDAO.getBuildingByID(builId);
    }

    /**
     * @see org.pmp.service.business.IBuildingService#loadBuildingList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Building> loadBuildingList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return buildingDAO.loadBuildingList_ByCompany(comId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IBuildingService#loadBuildingList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Building> loadBuildingList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return buildingDAO.loadBuildingList_ByProject(proId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IBuildingService#getBuildingByProjectIdAndBuildingNum(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,
	    Integer buildingNum) {
	return buildingDAO.getBuildingByProjectIdAndBuildingNum(projectId, buildingNum);
    }
    //~ Getters and Setters ============================================================================================

    public IBuildingDAO getBuildingDAO() {
        return buildingDAO;
    }

    public void setBuildingDAO(IBuildingDAO buildingDAO) {
        this.buildingDAO = buildingDAO;
    }

    public IHouseDAO getHouseDAO() {
        return houseDAO;
    }

    public void setHouseDAO(IHouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

}
