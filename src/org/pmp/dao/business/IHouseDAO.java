/**
 * Author            : ELAN
 * Created On        : 2012-3-22 下午02:15:38
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.House;

/**
 * @author ELAN
 * @version 1.0
 * @update TODO
 */
public interface IHouseDAO {
    public void saveHouse(House house);
    public void updateHouse(House house);
    public void deleteHouse(Integer houseID);
    public House getHouseByID(Integer houseId);
    public House getHouseByHouseNum(String houseNum);
    public List<House> loadHouseList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);   
    public List<House> loadHouseList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
    public List<House> loadHouseList_ByBuilding(Integer builId,Map<String,Object>params,String order,Pager pager);
}
