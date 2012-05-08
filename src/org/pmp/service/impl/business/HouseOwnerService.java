/**
 * Author            : Jason
 * Created On        : 2012-4-15 下午02:44:12
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

import org.pmp.dao.impl.business.HouseOwnerDAO;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class HouseOwnerService implements IHouseOwnerService {
	
	private HouseOwnerDAO houseOwnerDao;


	/**
	 * @Title: saveHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void saveHouseOwner(HouseOwner houseOwner) {
		houseOwnerDao.addHouseOwner(houseOwner);
	}

	/**
	 * @Title: updateHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void updateHouseOwner(HouseOwner houseOwner) {
		houseOwnerDao.updateHouseOwner(houseOwner);
	}

	/**
	 * @Title: getOwnerByHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public HouseOwner getOwnerByHouse(House house) {
		return houseOwnerDao.getOwnerByHouse(house);
	}
	
	/**
	 * @Title: getHouseByOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public HouseOwner getHouseByOwner(Owner owner)
	{
		return houseOwnerDao.getHouseByOwner(owner);
	}
	
	/**
	 * @Title: deleteHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteHouseOwner(Integer hoId) {
		houseOwnerDao.deleteHouseOwner(hoId);
	}
	
	/**
	 * @param houseOwnerDao the houseOwnerDao to set
	 */
	public void setHouseOwnerDao(HouseOwnerDAO houseOwnerDao) {
		this.houseOwnerDao = houseOwnerDao;
	}

	/**
	 * @Title: batchAddHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchAddHouseOwner(List<Integer> ownerIdList, Map map) {
		houseOwnerDao.batchAddHouseOwner(ownerIdList, map);
	}

}
