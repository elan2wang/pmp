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

import org.pmp.dao.business.IBuildingDAO;
import org.pmp.service.business.IBuildingService;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingService implements IBuildingService {
	IBuildingDAO buildingDao;
	

	/**
	 * @Title: saveBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void saveBuilding(Building building) {
		buildingDao.saveBuilding(building);
		saveBuildingHouse(building);
	}

	/**
	 * @Title: updateBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void updateBuilding(Building building) {
		buildingDao.updateBuilding(building);

	}

	/**
	 * @Title: deleteBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteBuilding(Integer builId) {
		buildingDao.deleteBuilding(builId);

	}

	/**
	 * @Title: getBuildingById
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Building getBuildingById(Integer buildId) {
		return buildingDao.getBuildingByID(buildId);
	}
	
	/**
	 * @Title: getBuildingByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getBuildingByProject(Project project)
	{
		return buildingDao.getBuildingByPro(project);
	}
	/**
	 * @Title: getAllBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getAllBuilding() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Title: loadProjectList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadBuildingList(Pager pager) {
		return buildingDao.loadBuildingList(pager);
	}
	/**
	 * @Title: loadBuildingListByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadBuildingListByProject(Pager pager, Integer projectId) {
		return buildingDao.loadBuildingListByProject(pager, projectId);
	}
	public void saveBuildingHouse(Building building){
//		private Integer builId;
//	    private Integer builNum;
//	    private Integer floorCount;
//	    private Integer housesPer;
//	    private Integer unitCount;
		int unit = building.getUnitCount();
		int floor = building.getFloorCount();
		int housePer = building.getHousesPer();
		
		ArrayList<String> list = new ArrayList<String>();
		
		if(building.getUnitTag().equals("数字")){
			for(int i=1;i<=unit;i++){
				for(int j=1;j<=floor;j++){
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
		
		buildingDao.saveBuildingHouse(list, building);
	}

	/**
	 * @param buildingDao the buildingDao to set
	 */
	public void setBuildingDao(IBuildingDAO buildingDao) {
		this.buildingDao = buildingDao;
	}

	/**
	 * @Title: batchSaveBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchSaveBuilding(List<Building> buildingList) {
		buildingDao.batchSaveBuilding(buildingList);
	}

	/**
	 * @Title: getBuildingByProjectIdAndBuildingNum
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,
			Integer buildingNum) {
		return buildingDao.getBuildingByProjectIdAndBuildingNum(projectId, buildingNum);
	}
}
