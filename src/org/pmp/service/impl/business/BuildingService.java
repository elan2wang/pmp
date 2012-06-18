/**
 * Author            : Jason
 * Created On        : 2012-3-27 下午04:00:06
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pmp.dao.business.IBuildingDAO;
import org.pmp.service.business.IBuildingService;
import org.pmp.util.Pager;
import org.pmp.vo.Building;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingService implements IBuildingService {
	IBuildingDAO buildingDAO;
	
	@Override
	public void addBuilding(Building building) {
		buildingDAO.saveBuilding(building);
		addBuildingHouse(building);
	}
	public void addBuildingHouse(Building building){
		int unit = building.getUnitCount();
		int floor = building.getFloorCount();
		int housePer = building.getHousesPer();
		int skipfloor = -1;
		if (building.getSkipFloor()!=null&&!building.getSkipFloor().equals("")){
		    skipfloor = Integer.parseInt(building.getSkipFloor());
		}
		ArrayList<String> list = new ArrayList<String>();
		
		if(building.getUnitTag().equals("数字")){
			for(int i=1;i<=unit;i++){
				for(int j=1;j<=floor;j++){
					if(j==skipfloor)
						continue;
					for(int k=1;k<=housePer;k++){
						if(k<10){
							String houseNum = building.getBuilNum()+"-"+String.valueOf(i)+"-"+String.valueOf(j)+"0"+String.valueOf(k);
							list.add(houseNum);
						}else{
							String houseNum = building.getBuilNum()+"-"+String.valueOf(i)+"-"+String.valueOf(j)+String.valueOf(k);
							list.add(houseNum);
						}
					}
				}
			}
		}else{
			for(int i=1;i<=unit;i++){
				for(int j=1;j<=floor;j++){
					if(j==skipfloor)
						continue;
					for(int k=1;k<=housePer;k++){
						if(k<10){
							String houseNum = building.getBuilNum()+"-"+String.valueOf((char)(64+i))+"-"+String.valueOf(j)+"0"+String.valueOf(k);
							list.add(houseNum);
						}else{
							String houseNum = building.getBuilNum()+"-"+String.valueOf((char)(64+i))+"-"+String.valueOf(j)+String.valueOf(k);
							list.add(houseNum);
						}
					}
				}
			}
		}
		
		buildingDAO.saveBuildingHouse(list, building);
	}

	public void editBuilding(Building building) {
		buildingDAO.updateBuilding(building);
	}

	public void deleteBuilding(Building building) {
		buildingDAO.deleteBuilding(building);

	}

	public Building getBuildingById(Integer buildId) {
		return buildingDAO.getBuildingByID(buildId);
	}
	
	public List<Building> loadBuildingList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager)
	{
		return buildingDAO.loadBuildingList_ByCompany(comId, params, order, pager);
	}
	
	public List<Building> loadBuildingList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager)
	{
		return buildingDAO.loadBuildingList_ByProject(proId, params, order, pager);
	}
	
	public void setBuildingDAO(IBuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}

	public void batchSaveBuilding(List<Building> buildingList) {
		buildingDAO.batchSaveBuilding(buildingList);
	}

	public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,
			Integer buildingNum) {
		return buildingDAO.getBuildingByProjectIdAndBuildingNum(projectId, buildingNum);
	}
}
