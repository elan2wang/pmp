/**
 * Author            : Jason
 * Created On        : 2012-4-15 下午02:28:00
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

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
public interface IHouseOwnerService {
    public void saveHouseOwner(HouseOwner houseOwner);
    public void updateHouseOwner(HouseOwner houseOwner);
    public HouseOwner getOwnerByHouse(House house);
    public HouseOwner getHouseByOwner(Owner owner);
    public void deleteHouseOwner(Integer hoId);
    public void batchAddHouseOwner(List<Integer> ownerIdList,Map map);
}
