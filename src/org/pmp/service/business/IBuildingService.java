/**
 * Author            : Jason
 * Created On        : 2012-3-23 下午05:08:34
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Building;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IBuildingService {
    public void saveBuilding(Building building);
    public void updateBuilding(Building building);
    public void deleteBuilding(Integer builId);
    public Building getBuildingById(Integer buildId);
    public List getAllBuilding();
    public List loadBuildingList(Pager pager);
    public List loadBuildingListByProject(Pager pager,Integer projectId);
    public void batchSaveBuilding(List<Building> buildingList);
    public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,Integer buildingNum);
}
