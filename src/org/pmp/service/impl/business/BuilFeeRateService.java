/**
 * Author            : Elan
 * Created On        : 2012-6-21 上午11:09:23
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
import org.pmp.dao.business.IBuilFeeRateDAO;
import org.pmp.service.business.IBuilFeeRateService;
import org.pmp.vo.BuilFeeRate;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class BuilFeeRateService implements IBuilFeeRateService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(BuilFeeRateService.class.getName());

    //~ Instance Fields ================================================================================================
    private IBuilFeeRateDAO builFeeRateDAO;

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IBuilFeeRateService#batchAddBuilFeeRate(java.util.List)
     */
    @Override
    public void batchAddBuilFeeRate(List<BuilFeeRate> list) {
	builFeeRateDAO.batchSaveBuilFeeRate(list);
    }

    /**
     * @see org.pmp.service.business.IBuilFeeRateService#loadBuilFeeRateList_ByEFIandBuilID(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BuilFeeRate> loadBuilFeeRateList_ByEFIandBuilID(Integer efiId,
	    Integer builId) {
	return builFeeRateDAO.loadBuilFeeRateList_ByEFIandBuilID(efiId, builId);
    }

    //~ Getters and Setters ============================================================================================

    public IBuilFeeRateDAO getBuilFeeRateDAO() {
        return builFeeRateDAO;
    }

    public void setBuilFeeRateDAO(IBuilFeeRateDAO builFeeRateDAO) {
        this.builFeeRateDAO = builFeeRateDAO;
    }

}
