/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����02:15:38
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.House;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IHouseDAO {
    public void saveHouse(House house);
    public void updateHouse(House house);
    public void deleteHouse(Integer houseID);
    public House getHouseByID(Integer houseId);
    public House getHouseByHouseNum(String houseNum);
    public List<?> getHouseByBuilding(Building building);
    public List<?> getHouseByOwner(Owner owner);
    public List<?> getHouseByIsempty(Boolean isempty);
    public List<?> getHouseByProjectOrBuilding(Integer projectId,Integer buildingId,Pager pager);
    public List<?> loadHouseList(Pager pager);
    public List<?> loadEnabledHouseList(Pager pager);
    public List<?> loadDisabledHouseList(Pager pager);
    public House getHouseByBuildingIdAndHouseNum(Integer buildingId,String houseNum);
}
