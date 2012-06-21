/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:26:18
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
import org.pmp.dao.business.IRepairAttachDAO;
import org.pmp.service.business.IRepairAttachService;
import org.pmp.vo.RepairAttach;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RepairAttachService implements IRepairAttachService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(RepairAttachService.class
	    .getName());

    //~ Instance Fields ================================================================================================
    private IRepairAttachDAO repairAttachDAO;

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IRepairAttachService#addRepairAttach(org.pmp.vo.RepairAttach)
     */
    @Override
    public void addRepairAttach(RepairAttach instance) {
	repairAttachDAO.saveRepairAttach(instance);
    }

    /**
     * @see org.pmp.service.business.IRepairAttachService#deleteRepairAttach(org.pmp.vo.RepairAttach)
     */
    @Override
    public void deleteRepairAttach(RepairAttach instance) {
	repairAttachDAO.deleteRepairAttach(instance);
    }

    /**
     * @see org.pmp.service.business.IRepairAttachService#loadRepairAttachList_ByOP(java.lang.Integer)
     */
    @Override
    public List<RepairAttach> loadRepairAttachList_ByOP(Integer opId) {
	return repairAttachDAO.loadRepairAttachList_ByOP(opId);
    }

    //~ Getters and Setters ============================================================================================

    public IRepairAttachDAO getRepairAttachDAO() {
        return repairAttachDAO;
    }

    public void setRepairAttachDAO(IRepairAttachDAO repairAttachDAO) {
        this.repairAttachDAO = repairAttachDAO;
    }
    
}
