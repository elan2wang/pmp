/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:13:31
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
import org.pmp.dao.business.IOwnerRepairDAO;
import org.pmp.service.business.IOwnerRepairService;
import org.pmp.util.Pager;
import org.pmp.vo.OwnerRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerRepairService implements IOwnerRepairService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerRepairService.class
	    .getName());

    //~ Instance Fields ================================================================================================
    private IOwnerRepairDAO ownerRepairDAO;
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IOwnerRepairService#addOwnerRepair(org.pmp.vo.OwnerRepair)
     */
    @Override
    public void addOwnerRepair(OwnerRepair instance) {
	ownerRepairDAO.saveOwnerRepair(instance);
    }

    /**
     * @see org.pmp.service.business.IOwnerRepairService#editOwnerRepair(org.pmp.vo.OwnerRepair)
     */
    @Override
    public void editOwnerRepair(OwnerRepair instance) {
	ownerRepairDAO.updateOwnerRepair(instance);
    }

    /**
     * @see org.pmp.service.business.IOwnerRepairService#batchDeleteOwnerRepair(java.util.List)
     */
    @Override
    public void batchDeleteOwnerRepair(List<OwnerRepair> list) {
	ownerRepairDAO.batchDeleteOwnerRepair(list);
    }

    /**
     * @see org.pmp.service.business.IOwnerRepairService#loadOwnerRepairList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<OwnerRepair> loadOwnerRepairList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerRepairDAO.loadOwnerRepairList_ByCompany(comId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IOwnerRepairService#loadOwnerRepairList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<OwnerRepair> loadOwnerRepairList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return ownerRepairDAO.loadOwnerRepairList_ByProject(proId, params, order, pager);
    }
    //~ Getters and Setters ============================================================================================

    public IOwnerRepairDAO getOwnerRepairDAO() {
        return ownerRepairDAO;
    }

    public void setOwnerRepairDAO(IOwnerRepairDAO ownerRepairDAO) {
        this.ownerRepairDAO = ownerRepairDAO;
    }

}
