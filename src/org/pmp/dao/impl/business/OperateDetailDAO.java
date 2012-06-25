/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:38:25
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
import org.pmp.dao.business.IOperateDetailDAO;
import org.pmp.util.Pager;
import org.pmp.vo.OperateDetail;
import org.pmp.vo.RepairFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OperateDetailDAO extends BaseDAO implements IOperateDetailDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OperateDetailDAO.class.getName());

    //~ Instance Fields ================================================================================================
    
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IOperateDetailDAO#saveOperateDetail(org.pmp.vo.OperateDetail)
     */
    @Override
    public void saveOperateDetail(OperateDetail instance) {
	String debugMsg = "save operateDetail";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IOperateDetailDAO#loadOperateDetailList_ByOP(java.lang.Integer)
     */
    @Override
    public List<OperateDetail> loadOperateDetailList_ByOP(Integer opId) {
	String debugMsg = "load operateDetail list by OwnerRepair, opId="+opId;
	String hql = "from OperateDetail where ownerRepair.opId="+opId+" order by orId desc";
	List<OperateDetail> list = null;
	try {
	    list = (List<OperateDetail>) loadListByCondition(hql, new Pager(1000,1), debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }

}
