/**
 * Author            : Elan
 * Created On        : 2012-4-9 下午04:40:48
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

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
    
}
