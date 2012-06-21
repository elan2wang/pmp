/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:20:13
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IOwnerRepairDAO;
import org.pmp.util.Pager;
import org.pmp.vo.OwnerRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerRepairDAO extends BaseDAO implements IOwnerRepairDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerRepairDAO.class
	    .getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#saveOwnerRepair(org.pmp.vo.OwnerRepair)
     */
    @Override
    public void saveOwnerRepair(OwnerRepair instance) {
	String debugMsg = "save ownerRepair";
	try {
	    saveInstance(instance, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#updateOwnerRepair(org.pmp.vo.OwnerRepair)
     */
    @Override
    public void updateOwnerRepair(OwnerRepair instance) {
	String debugMsg = "update ownerRepair";
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#batchDeleteOwnerRepair(java.util.List)
     */
    @Override
    public void batchDeleteOwnerRepair(List<OwnerRepair> list) {
	// TODO Auto-generated method stub

    }

    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#loadOwnerRepairList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<OwnerRepair> loadOwnerRepairList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#loadOwnerRepairList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<OwnerRepair> loadOwnerRepairList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }
}
