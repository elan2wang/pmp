/**
 * Author            : Jason
 * Created On        : 2012-3-28 下午04:36:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.pmp.dao.business.IHouseDAO;
import org.pmp.service.business.IHouseService;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.House;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class HouseService implements IHouseService {
	
	private IHouseDAO houseDao;

	

	/**
	 * @Title: saveHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void saveHouse(House house) {
		houseDao.saveHouse(house);
	}

	/**
	 * @Title: deleteHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteHouse(Integer houseId) {
		houseDao.deleteHouse(houseId);
	}

	/**
	 * @Title: updateHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void updateHouse(House house) {
		houseDao.updateHouse(house);
	}

	/**
	 * @Title: getHouseById
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public House getHouseById(Integer houseId) {
		return houseDao.getHouseByID(houseId);
	}

	/**
	 * @Title: loadHouseList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadHouseList(Pager pager) {
		return houseDao.loadHouseList(pager);
	}

	/**
	 * @param houseDao the houseDao to set
	 */
	public void setHouseDao(IHouseDAO houseDao) {
		this.houseDao = houseDao;
	}

	/**
	 * @Title: getHouseByProjectOrBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getHouseByProjectOrBuilding(Integer projectId,
			Integer buildingId, Pager pager) {
		return houseDao.getHouseByProjectOrBuilding(projectId, buildingId, pager);
	}

	/**
	 * @Title: getHouseByBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getHouseByBuilding(Building building) {
		return houseDao.getHouseByBuilding(building);
	}

	/**
	 * @Title: getHouseByHouseNum
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public House getHouseByHouseNum(String houseNum) {
		return houseDao.getHouseByHouseNum(houseNum);
	}

	/**
	 * @Title: getHouseByBuildingIdAndHouseNum
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public House getHouseByBuildingIdAndHouseNum(Integer buildingId,
			String houseNum) {
		return houseDao.getHouseByBuildingIdAndHouseNum(buildingId, houseNum);
	}
}
