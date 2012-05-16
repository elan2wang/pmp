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
package org.pmp.service.impl.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IOwnerDAO2;
import org.pmp.service.business.IOwnerService2;
import org.pmp.util.Pager;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerService2 implements IOwnerService2 {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerService2.class.getName());

    //~ Instance Fields ================================================================================================
    private IOwnerDAO2 ownerDAO2;

    //~ Methods ========================================================================================================
    /**
     * @see org.pmp.service.business.IOwnerService2#addOwner(org.pmp.vo.Owner)
     */
    @Override
    public void addOwner(Owner instance) {
	ownerDAO2.saveOwner(instance);
    }

    /**
     * @see org.pmp.service.business.IOwnerService2#editOwner(org.pmp.vo.Owner)
     */
    @Override
    public void editOwner(Owner instance) {
	ownerDAO2.updateOwner(instance);
    }

    /**
     * @see org.pmp.service.business.IOwnerService2#batchDelete(java.util.List)
     */
    @Override
    public void batchDelete(List<Owner> list) {
	ownerDAO2.batchDelete(list);
    }

    /**
     * @see org.pmp.service.business.IOwnerService2#getOwner_ById(java.lang.Integer)
     */
    @Override
    public Owner getOwner_ById(Integer ownerId) {
	return ownerDAO2.getOwner_ById(ownerId);
    }

    /**
     * @see org.pmp.service.business.IOwnerService2#loadOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerDAO2.loadOwnerList_ByPro(proId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IOwnerService2#loadOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerDAO2.loadOwnerList_ByCom(comId, params, order, pager);
    }
    //~ Getters and Setters ============================================================================================

    public IOwnerDAO2 getOwnerDAO2() {
        return ownerDAO2;
    }

    public void setOwnerDAO2(IOwnerDAO2 ownerDAO2) {
        this.ownerDAO2 = ownerDAO2;
    }
}
