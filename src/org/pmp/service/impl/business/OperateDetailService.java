/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:22:21
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
import org.pmp.dao.business.IOperateDetailDAO;
import org.pmp.service.business.IOperateDetailService;
import org.pmp.vo.OperateDetail;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OperateDetailService implements IOperateDetailService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OperateDetailService.class
	    .getName());

    //~ Instance Fields ================================================================================================
    private IOperateDetailDAO operateDetailDAO;
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IOperateDetailService#addOperateDetail(org.pmp.vo.OperateDetail)
     */
    @Override
    public void addOperateDetail(OperateDetail instance) {
	operateDetailDAO.saveOperateDetail(instance);
    }

    /**
     * @see org.pmp.service.business.IOperateDetailService#loadOperateDetailList_ByOP(java.lang.Integer)
     */
    @Override
    public List<OperateDetail> loadOperateDetailList_ByOP(Integer opId) {
	return operateDetailDAO.loadOperateDetailList_ByOP(opId);
    }
    
    //~ Getters and Setters ============================================================================================

    public IOperateDetailDAO getOperateDetailDAO() {
        return operateDetailDAO;
    }

    public void setOperateDetailDAO(IOperateDetailDAO operateDetailDAO) {
        this.operateDetailDAO = operateDetailDAO;
    }

}
