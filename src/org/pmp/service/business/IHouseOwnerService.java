/**
 * Author            : Elan
 * Created On        : 2012-4-15 下午02:28:00
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.HouseOwner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IHouseOwnerService {
    public void batchAdd(List<Integer> ownerIdList);
    public void addHouseOwner(HouseOwner instance);
    public void editHouseOwner(HouseOwner instance);
    public void deleteHouseOwner(HouseOwner instance);
    
    public HouseOwner getHouseOwner_ByHouse(Integer houseId);
    public HouseOwner getHouseOwner_ByOwner(Integer ownerId);
    
    public List<Map<String, Object>> loadOwnerList_ByPro(Integer proId,Map<String,Object> params,String order,Pager pager);
    public List<Map<String, Object>> loadOwnerList_ByCom(Integer proId,Map<String,Object> params,String order,Pager pager);
    
    public List<HouseOwner> loadHouseOwnerList_ByPro(Integer proId,Map<String,Object> params,String order,Pager pager);
    public List<HouseOwner> loadHouseOwnerList_ByCom(Integer comId,Map<String,Object> params,String order,Pager pager);

}
