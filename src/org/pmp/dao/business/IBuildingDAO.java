/**
 * Author            : Jason
 * Created On        : 2012-3-21 下午08:39:00
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IBuildingDAO {
    public void saveBuilding(Building building);
    public void updateBuilding(Building building);
    public void deleteBuilding(Integer buildingID);
    public Building getBuildingByID(Integer buildingID);
    public Building getBuildingByBuilNum(Integer bulNum);
    public List<Building> loadBuildingList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
    
    public List<Building> loadBuildingList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
    public void saveBuildingHouse(List<String> houseNum,Building building);
    public void batchSaveBuilding(List<Building> buildingList);
    public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,Integer buildingNum);
}
