/**
 * Author            : Elan
 * Created On        : 2012-4-9 下午04:40:48
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.HouseOwner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IHouseOwnerDAO {
    public void batchSave(List<Integer> ownerIdList);
    public void saveHouseOwner(HouseOwner instance);
    public void updateHouseOwner(HouseOwner instance);
    public void deleteHouseOwner(HouseOwner instance);
    
    public HouseOwner getHouseOwner_ByHouse(Integer houseId);
    public HouseOwner getHouseOwner_ByOwner(Integer ownerId);
    
    public List<HouseOwner> loadHouseOwnerList_ByPro(Integer proId,Map<String,Object> params,String order,Pager pager);
    public List<HouseOwner> loadHouseOwnerList_ByCom(Integer comId,Map<String,Object> params,String order,Pager pager);
}
