/**
 * Author            : Jason
 * Created On        : 2012-3-21 下午08:39:00
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Building;
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
    public List<?> getBuildingByPro(Project project);
    public List<?> getBuildingByBuilType(String builType);
    public List<?> loadBuildingList(Pager pager);
    public List<?> loadEnabledBuildingList(Pager pager);
    public List<?> loadDisabledBuildingList(Pager pager);
    public List<?> loadBuildingListByProject(Pager pager,Integer projectId);
    public void saveBuildingHouse(List<String> houseNum,Building building);
    public void batchSaveBuilding(List<Building> buildingList);
    public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,Integer buildingNum);
}
