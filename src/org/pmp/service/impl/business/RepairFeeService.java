/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:15:59
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

import org.apache.log4j.Logger;
import org.pmp.dao.business.IRepairFeeDAO;
import org.pmp.service.business.IRepairFeeService;
import org.pmp.vo.RepairFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RepairFeeService implements IRepairFeeService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(RepairFeeService.class
	    .getName());

    //~ Instance Fields ================================================================================================
    private IRepairFeeDAO repairFeeDAO;
    
    //~ Methods ========================================================================================================
    
    /**
     * @see org.pmp.service.business.IRepairFeeService#batchAddRepairFee(java.util.List)
     */
    @Override
    public void batchAddRepairFee(List<RepairFee> list) {
	repairFeeDAO.batchSaveRepairFee(list);
    }

    /**
     * @see org.pmp.service.business.IRepairFeeService#deleteRepairFee(org.pmp.vo.RepairFee)
     */
    @Override
    public void deleteRepairFee(RepairFee instance) {
	repairFeeDAO.deleteRepairFee(instance);

    }

    /**
     * @see org.pmp.service.business.IRepairFeeService#loadRepairFeeList_ByOP(java.lang.Integer)
     */
    @Override
    public List<RepairFee> loadRepairFeeList_ByOP(Integer opId) {
	return repairFeeDAO.loadRepairFeeList_ByOP(opId);
    }
    //~ Getters and Setters ============================================================================================

    public IRepairFeeDAO getRepairFeeDAO() {
        return repairFeeDAO;
    }

    public void setRepairFeeDAO(IRepairFeeDAO repairFeeDAO) {
        this.repairFeeDAO = repairFeeDAO;
    }

}
