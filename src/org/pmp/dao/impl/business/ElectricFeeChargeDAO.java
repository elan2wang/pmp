/**
 * Author            : Elan
 * Created On        : 2012-6-24 下午10:14:32
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
import org.pmp.dao.business.IElectricFeeChargeDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.ElectricFeeCharge;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeChargeDAO extends BaseDAO implements
	IElectricFeeChargeDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeChargeDAO.class
	    .getName());


    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IElectricFeeChargeDAO#saveElectricFeeCharge(org.pmp.vo.ElectricFeeCharge)
     */
    @Override
    public void saveElectricFeeCharge(ElectricFeeCharge instance) {
	String debugMsg = "save electricFeeCharge";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}

    }

    /**
     * @see org.pmp.dao.business.IElectricFeeChargeDAO#batchUpdateElectricFeeCharge(java.util.List)
     */
    @Override
    public void batchUpdateElectricFeeCharge(List<ElectricFeeCharge> list) {
	// TODO Auto-generated method stub
	
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeChargeDAO#deleteElectricFeeCharge(org.pmp.vo.ElectricFeeCharge)
     */
    @Override
    public void deleteElectricFeeCharge(ElectricFeeCharge instance) {
	String debugMsg = "delete electricFeeCharge";
	try {
	    deleteInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}

    }

    /**
     * @see org.pmp.dao.business.IElectricFeeChargeDAO#getElectricFeeCharge_ByID(java.lang.Integer)
     */
    @Override
    public ElectricFeeCharge getElectricFeeCharge_ByID(Integer efcId) {
	String debugMsg = "get electricFeeCharg by ID, efcId="+efcId;
	String hql = "from ElectricFeeCharge where efcId="+efcId;
	ElectricFeeCharge efc = null;
	try {
	    efc = (ElectricFeeCharge)getInstance(hql, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return efc;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeChargeDAO#loadElectricFeeChargeList_ByHouse(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFeeCharge>  loadElectricFeeChargeList_ByHouse(Integer houseId,
	    Map<String, Object> params, String order, Pager pager) {
	List<ElectricFeeCharge> list = null;
	String debugMsg = "load ElectricFeeCharge list by house, houseId="+houseId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFeeCharge where houseOwner.house.houseId="+houseId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by efcId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<ElectricFeeCharge>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;

    }

}
