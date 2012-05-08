/**
 * Author            : Jason
 * Created On        : 2012-4-9 下午04:40:48
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IHouseOwnerDAO {
    public void addHouseOwner(HouseOwner houseOwner);
    public void updateHouseOwner(HouseOwner houseOwner);
    public HouseOwner getOwnerByHouse(House house);
    public HouseOwner getHouseByOwner(Owner owner);
    public void deleteHouseOwner(Integer hoId);
    public void batchAddHouseOwner(List<Integer> ownerIdList,Map<?,?> map);
}
