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
import org.pmp.util.ParamsToString;
import org.pmp.vo.CondoFee;
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
     * @see org.pmp.dao.business.IOwnerRepairDAO#getOwnerRepair_ByID(java.lang.Integer)
     */
    @Override
    public OwnerRepair getOwnerRepair_ByID(Integer opId) {
	String debugMsg = "get ownerRepair by id, opId="+opId;
	String hql = "from OwnerRepair where opId="+opId;
	OwnerRepair instance = null;
	try {
	    instance = (OwnerRepair)getInstance(hql, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return instance;
    }
    
    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#loadOwnerRepairList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<OwnerRepair> loadOwnerRepairList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<OwnerRepair> list = null;
	String debugMsg = "load ownerRepair list by company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	hql.append("from OwnerRepair where houseOwner.house.houseId in (select houseId from House where building.builId in (" +
		   "select builId from Building where project.proId in (select proId from Project where company.comId="+comId+")))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by applyTime desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<OwnerRepair>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IOwnerRepairDAO#loadOwnerRepairList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<OwnerRepair> loadOwnerRepairList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	List<OwnerRepair> list = null;
	String debugMsg = "load ownerRepair list by project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	hql.append("from OwnerRepair where houseOwner.house.houseId in (select houseId from House where building.builId in (" +
		   "select builId from Building where project.proId="+proId+"))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by applyTime desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<OwnerRepair>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

}
