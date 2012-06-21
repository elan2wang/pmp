/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:25:24
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

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IRepairFeeDAO;
import org.pmp.util.Pager;
import org.pmp.vo.RepairFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RepairFeeDAO extends BaseDAO implements IRepairFeeDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(RepairFeeDAO.class
	    .getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IRepairFeeDAO#batchSaveRepairFee(java.util.List)
     */
    @Override
    public void batchSaveRepairFee(List<RepairFee> list) {
	/* list.size will not be greater than 10, so we can use for cyclic sentence */
	String debugMsg = "save LiftMeterItem";
	try {
	    for(RepairFee rf : list){
		saveInstance(rf, debugMsg);
	    }
	} catch (RuntimeException e){
	    throw e;
	}

    }

    /**
     * @see org.pmp.dao.business.IRepairFeeDAO#deleteRepairFee(org.pmp.vo.RepairFee)
     */
    @Override
    public void deleteRepairFee(RepairFee instance) {
	String debugMsg = "delete repairFee";
	String hql = "delete from RepairFee where rfId="+instance.getRfId();
	try {
	    deleteInstance(hql, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IRepairFeeDAO#loadRepairFeeList_ByOP(java.lang.Integer)
     */
    @Override
    public List<RepairFee> loadRepairFeeList_ByOP(Integer opId) {
	String debugMsg = "load repairFee list by OwnerRepair, opId="+opId;
	String hql = "from RepairFee where ownerRepair.opId="+opId+" order by rfId desc";
	List<RepairFee> list = null;
	try {
	    list = (List<RepairFee>) loadListByCondition(hql, new Pager(1000,1), debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }

}
