/**
 * Author            : Elan
 * Created On        : 2012-5-18 下午04:31:55
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IHouseOwnerDAO;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.util.Pager;
import org.pmp.vo.HouseOwner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class HouseOwnerService implements IHouseOwnerService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(HouseOwnerService.class.getName());

    //~ Instance Fields ================================================================================================
    private IHouseOwnerDAO houseOwnerDAO; 
    
    //~ Methods ========================================================================================================
    /**
     * @see org.pmp.service.business.IHouseOwnerService#batchAdd(java.util.List)
     */
    @Override
    public void batchAdd(List<Integer> ownerIdList) {
	houseOwnerDAO.batchSave(ownerIdList);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#addHouseOwner(org.pmp.vo.HouseOwner)
     */
    @Override
    public void addHouseOwner(HouseOwner instance) {
	houseOwnerDAO.saveHouseOwner(instance);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#editHouseOwner(org.pmp.vo.HouseOwner)
     */
    @Override
    public void editHouseOwner(HouseOwner instance) {
	houseOwnerDAO.updateHouseOwner(instance);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#deleteHouseOwner(org.pmp.vo.HouseOwner)
     */
    @Override
    public void deleteHouseOwner(HouseOwner instance) {
	houseOwnerDAO.deleteHouseOwner(instance);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#getHouseOwner_ByHouse(java.lang.Integer)
     */
    @Override
    public HouseOwner getHouseOwner_ByHouse(Integer houseId) {
	return houseOwnerDAO.getHouseOwner_ByHouse(houseId);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#getHouseOwner_ByOwner(java.lang.Integer)
     */
    @Override
    public HouseOwner getHouseOwner_ByOwner(Integer ownerId) {
	return houseOwnerDAO.getHouseOwner_ByOwner(ownerId);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#loadHouseOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<HouseOwner> loadHouseOwnerList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseOwnerDAO.loadHouseOwnerList_ByPro(proId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#loadHouseOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<HouseOwner> loadHouseOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseOwnerDAO.loadHouseOwnerList_ByCom(comId, params, order, pager);
    }
    
    /**
     * @see org.pmp.service.business.IHouseOwnerService#loadOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Map<String, Object>> loadOwnerList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseOwnerDAO.loadOwnerList_ByPro(proId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IHouseOwnerService#loadOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Map<String, Object>> loadOwnerList_ByCom(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return houseOwnerDAO.loadOwnerList_ByCom(proId, params, order, pager);
    }

    //~ Getters and Setters ============================================================================================

    public IHouseOwnerDAO getHouseOwnerDAO() {
        return houseOwnerDAO;
    }

    public void setHouseOwnerDAO(IHouseOwnerDAO houseOwnerDAO) {
        this.houseOwnerDAO = houseOwnerDAO;
    }

}
