/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:43:21
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
import org.pmp.dao.business.IRepairAttachDAO;
import org.pmp.util.Pager;
import org.pmp.vo.RepairAttach;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RepairAttachDAO extends BaseDAO implements IRepairAttachDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(RepairAttachDAO.class
	    .getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IRepairAttachDAO#saveRepairAttach(org.pmp.vo.RepairAttach)
     */
    @Override
    public void saveRepairAttach(RepairAttach instance) {
	String debugMsg = "save repairAttach";
	try {
	    saveInstance(instance, debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IRepairAttachDAO#getRepairAttach_ByID(java.lang.Integer)
     */
    @Override
    public RepairAttach getRepairAttach_ByID(Integer raId) {
	String debugMsg = "get repairAttach by id, raId="+raId;
	String hql = "from RepairAttach where raId="+raId;
	RepairAttach ra = null;
	try {
	    ra = (RepairAttach)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return ra;
    }

    /**
     * @see org.pmp.dao.business.IRepairAttachDAO#deleteRepairAttach(org.pmp.vo.RepairAttach)
     */
    @Override
    public void deleteRepairAttach(RepairAttach instance) {
	String debugMsg = "delete repairAttach, raId="+instance.getRaId();
	String hql = "delete from RepairAttach where raId="+instance.getRaId();
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IRepairAttachDAO#loadRepairAttachList_ByOP(java.lang.Integer)
     */
    @Override
    public List<RepairAttach> loadRepairAttachList_ByOP(Integer opId) {
	String debugMsg = "load repairAttach list by OwnerRepair, opId="+opId;
	String hql = "from RepairAttach where ownerRepair.opId="+opId+" order by raId desc";
	List<RepairAttach> list = null;
	try {
	    list = (List<RepairAttach>) loadListByCondition(hql, new Pager(1000,1), debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }

}
