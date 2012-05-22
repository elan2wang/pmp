/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午01:22:09
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IHouseDAO;
import org.pmp.dao.business.IHouseOwnerDAO;
import org.pmp.dao.business.IMemberDAO;
import org.pmp.dao.business.IOwnerDAO2;
import org.pmp.service.business.IOwnerService;
import org.pmp.util.Pager;
import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerService implements IOwnerService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerService.class.getName());

    //~ Instance Fields ================================================================================================
    private IOwnerDAO2 ownerDAO;
    private IMemberDAO memberDAO;
    private IHouseOwnerDAO houseOwnerDAO;
    private IHouseDAO houseDAO;

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IOwnerService#addOwner(org.pmp.vo.Owner, java.util.List, java.lang.Integer)
     */
    @Override
    public void addOwner(Owner instance, List<Member> list, Integer houseId) {
	/* save owner instance */
	ownerDAO.saveOwner(instance);
	
	/* set property owner to member instance */
	Iterator<Member> ite = list.iterator();
	while(ite.hasNext()){
	    ite.next().setOwner(instance);
	}
	memberDAO.batchSave(list);
	
	/* update house info */
	House house = houseDAO.getHouseByID(houseId);
	house.setHouseArea(instance.getHouseArea());
	house.setIsempty(false);
	houseDAO.updateHouse(house);
	
	/* set houseOwner instance */
	HouseOwner ho = new HouseOwner();
	ho.setOwner(instance);
	ho.setHouse(houseDAO.getHouseByID(houseId));
	/* save houseOwner instance */
	houseOwnerDAO.saveHouseOwner(ho);
	
    }

    /**
     * @see org.pmp.service.business.IOwnerService#editOwner(org.pmp.vo.Owner, java.util.List, java.lang.Integer)
     */
    @Override
    public void editOwner(Owner instance, List<Member> list, Integer houseId) {
	/* update owner info */
	ownerDAO.updateOwner(instance);
	
	/* set property owner to member instance */
	Iterator<Member> ite = list.iterator();
	while(ite.hasNext()){
	    ite.next().setOwner(instance);
	}
	memberDAO.batchUpdate(list);
	
	/* update old house info */
	House oldHouse = houseOwnerDAO.getHouseOwner_ByOwner(instance.getOwnerId()).getHouse();
	oldHouse.setIsempty(true);
	houseDAO.updateHouse(oldHouse);
	
	/* update new house info */
	House newHouse = houseDAO.getHouseByID(houseId);
	newHouse.setHouseArea(instance.getHouseArea());
	newHouse.setIsempty(false);
	houseDAO.updateHouse(newHouse);
	
	/* set houseOwner instance */
	HouseOwner ho = houseOwnerDAO.getHouseOwner_ByOwner(instance.getOwnerId());
	ho.setHouse(houseDAO.getHouseByID(houseId));
	/* save houseOwner instance */
	houseOwnerDAO.updateHouseOwner(ho);
    }

    /**
     * @see org.pmp.service.business.IOwnerService#batchSave(java.util.List,java.util.Map)
     */
    @Override
    public void batchSave(List<Owner> list) {
	/* batch save owner instance , and return the generated key */
	List<Integer> idList = ownerDAO.batchSave(list);
	
	/* call procedure to generated HouseOwner instance list */
	houseOwnerDAO.batchSave(idList);
    }

    /**
     * @see org.pmp.service.business.IOwnerService#batchDelete(java.util.List)
     */
    @Override
    public void batchDelete(List<Owner> list) {
	ownerDAO.batchDelete(list);
    }

    /**
     * @see org.pmp.service.business.IOwnerService#getOwner_ById(java.lang.Integer)
     */
    @Override
    public Owner getOwner_ById(Integer ownerId) {
	return ownerDAO.getOwner_ById(ownerId);
    }
    

    /**
     * @see org.pmp.service.business.IOwnerService#loadOwnerList_ByBuil(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByBuil(Integer builId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerDAO.loadOwnerList_ByBuil(builId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IOwnerService#loadOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerDAO.loadOwnerList_ByPro(proId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IOwnerService#loadOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerDAO.loadOwnerList_ByCom(comId, params, order, pager);
    }
    //~ Getters and Setters ============================================================================================

    public IOwnerDAO2 getOwnerDAO() {
        return ownerDAO;
    }

    public void setOwnerDAO(IOwnerDAO2 ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    public IMemberDAO getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(IMemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public IHouseOwnerDAO getHouseOwnerDAO() {
        return houseOwnerDAO;
    }

    public void setHouseOwnerDAO(IHouseOwnerDAO houseOwnerDAO) {
        this.houseOwnerDAO = houseOwnerDAO;
    }

    public IHouseDAO getHouseDAO() {
        return houseDAO;
    }

    public void setHouseDAO(IHouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

}
