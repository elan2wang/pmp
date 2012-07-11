/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午02:37:16
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
import org.pmp.dao.business.IElectricFeeItemDAO;
import org.pmp.util.Pager;
import org.pmp.vo.ElectricFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeItemDAO extends BaseDAO implements IElectricFeeItemDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeItemDAO.class.getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IElectricFeeItemDAO#saveElectricFeeItem(org.pmp.vo.ElectricFeeItem)
     */
    @Override
    public void saveElectricFeeItem(ElectricFeeItem instance) {
	String debugMsg = "save electricFeeItem";
	try {
	    saveInstance(instance, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeItemDAO#updateElectricFeeItem(org.pmp.vo.ElectricFeeItem)
     */
    @Override
    public void updateElectricFeeItem(ElectricFeeItem instance) {
	String debugMsg = "update electricFeeItem";
	try {
	    updateInstance(instance, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeItemDAO#deleteElectricFeeItem(org.pmp.vo.ElectricFeeItem)
     */
    @Override
    public void deleteElectricFeeItem(ElectricFeeItem instance) {
	String debugMsg = "delete electricFeeItem, efiId="+instance.getEfiId();
	try {
	    deleteInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeItemDAO#getElectricFeeItemByID(java.lang.Integer)
     */
    @Override
    public ElectricFeeItem getElectricFeeItemByID(Integer efiId) {
	String debugMsg = "get electricFeeItem by ID, efiId="+efiId;
	String hql = "from ElectricFeeItem where efiId="+efiId;
	ElectricFeeItem instance = null;
	try {
	    instance = (ElectricFeeItem)getInstance(hql, debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return instance;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeItemDAO#loadElectricFeeItemList_ByCompany(java.lang.Integer, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFeeItem> loadElectricFeeItemList_ByCompany(
	    Integer comId, String order, Pager pager) {
	String debugMsg = "load electricFeeItem list by company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFeeItem where project.proId in " +
	           "(select proId from Project where company.comId="+comId+")");
	if(order != null){
	    hql.append(" "+order);
	} else {
	    hql.append(" order by efiId desc");
	}
	List<ElectricFeeItem> efiList = null;
	try {
	    efiList = (List<ElectricFeeItem>) loadListByCondition(hql.toString(), pager, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return efiList;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeItemDAO#loadElectricFeeItemList_ByProject(java.lang.Integer, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFeeItem> loadElectricFeeItemList_ByProject(
	    Integer proId, String order, Pager pager) {
	String debugMsg = "load electricFeeItem list by project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFeeItem where project.proId="+proId);
	if(order != null){
	    hql.append(" "+order);
	} else {
	    hql.append(" order by efiId desc");
	}
	List<ElectricFeeItem> efiList = null;
	try {
	    efiList = (List<ElectricFeeItem>) loadListByCondition(hql.toString(), pager, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return efiList;
    }

}
