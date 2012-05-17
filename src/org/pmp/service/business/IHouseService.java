/**
 * Author            : Jason
 * Created On        : 2012-3-28 下午04:31:49
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.House;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IHouseService {
    public void saveHouse(House house);
    public void deleteHouse(Integer houseId);
    public void updateHouse(House house);
    public House getHouseByHouseNum(String houseNum);
    public House getHouseById(Integer houseId);
    public List<House> loadHouseList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);   
    public List<House> loadHouseList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
    public List<House> loadHouseList_ByBuilding(Integer builId,Map<String,Object>params,String order,Pager pager);
    public House getHouseByBuildingIdAndHouseNum(Integer buildingId,String houseNum);
}
