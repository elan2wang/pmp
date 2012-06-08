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
import org.pmp.vo.House;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IHouseService {
    public void addHouse(House house);
    public void editHouse(House house);
    public void batchDelete(List<House> list);

    public House getHouseByID(Integer houseId);
    public House getHouseByHouseNum(String houseNum);
    public List<House> loadHouseList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);   
    public List<House> loadHouseList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
    public List<House> loadHouseList_ByBuilding(Integer builId,Map<String,Object>params,String order,Pager pager);
}
