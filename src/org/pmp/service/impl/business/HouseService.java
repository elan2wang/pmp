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
    private IHouseDAO houseDAO;

    /**
     * @see org.pmp.service.business.IHouseService#addHouse(org.pmp.vo.House)
     */
    @Override
    public void addHouse(House house) {
	houseDAO.saveHouse(house);
    }

    /**
     * @see org.pmp.service.business.IHouseService#editHouse(org.pmp.vo.House)
     */
    @Override
    public void editHouse(House house) {
	houseDAO.updateHouse(house);
    }

    /**
     * @see org.pmp.service.business.IHouseService#batchDelete(java.util.List)
     */
    @Override
    public void batchDelete(List<House> list) {
	houseDAO.batchDelete(list);
    }

    /**
     * @see org.pmp.service.business.IHouseService#getHouseByID(java.lang.Integer)
     */
    @Override
    public House getHouseByID(Integer houseId) {
	return houseDAO.getHouseByID(houseId);
    }

    /**
     * @see org.pmp.service.business.IHouseService#getHouseByHouseNum(java.lang.String)
     */
    @Override
    public House getHouseByHouseNum(String houseNum) {
	return houseDAO.getHouseByHouseNum(houseNum);
    }

    /**
     * @see org.pmp.service.business.IHouseService#loadHouseList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<House> loadHouseList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseDAO.loadHouseList_ByCompany(comId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IHouseService#loadHouseList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<House> loadHouseList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseDAO.loadHouseList_ByProject(proId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IHouseService#loadHouseList_ByBuilding(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<House> loadHouseList_ByBuilding(Integer builId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseDAO.loadHouseList_ByBuilding(builId, params, order, pager);
    }

    //~ getters and setters ========================================================================================================================
    public IHouseDAO getHouseDAO() {
        return houseDAO;
    }

    public void setHouseDAO(IHouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }
    
}
