/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:16:35
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
import org.pmp.dao.business.IBuilFeeRate;
import org.pmp.vo.BuilFeeRate;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class BuilFeeRateDAO extends BaseDAO implements IBuilFeeRate {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(BuilFeeRateDAO.class.getName());

    //~ Instance Fields ================================================================================================
    
    //~ Methods ========================================================================================================

    
    /**
     * @see org.pmp.dao.business.IBuilFeeRate#batchSaveBuilFeeRate(java.util.List)
     */
    @Override
    public void batchSaveBuilFeeRate(List<BuilFeeRate> list) {
	/* list.size will not be greater than 10, so we can use for cyclic sentence */
	String debugMsg = "save BuilFeeRate";
	try {
	    for(BuilFeeRate pmi : list){
		saveInstance(pmi, debugMsg);
	    }
	} catch (RuntimeException e){
	    throw e;
	}

    }

    /**
     * @see org.pmp.dao.business.IBuilFeeRate#loadBuilFeeRateList_ByEFIandBuilID(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BuilFeeRate> loadBuilFeeRateList_ByEFIandBuilID(Integer efiId,
	    Integer builId) {
	// TODO Auto-generated method stub
	return null;
    }

}
