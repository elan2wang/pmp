/**
 * Author            : Jason
 * Created On        : 2012-3-28 下午04:36:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

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
	 * @param houseDao the houseDao to set
	 */
	public void setHouseDao(IHouseDAO houseDao) {
		this.houseDao = houseDao;
	}

	
	/**
	 * @Title: loadHouseList_ByCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<House> loadHouseList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager)
	{
		return houseDao.loadHouseList_ByCompany(comId, params, order, pager);
	}
	/**
	 * @Title: loadHouseList_ByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<House> loadHouseList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager)
	{
		return houseDao.loadHouseList_ByProject(proId, params, order, pager);
	}

	/**
	 * @Title: loadHouseList_ByBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
    public List<House> loadHouseList_ByBuilding(Integer builId,Map<String,Object>params,String order,Pager pager)
    {
    	return houseDao.loadHouseList_ByBuilding(builId, params, order, pager);
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
