/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:14:34
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
import org.pmp.dao.business.ILiftMeterItemDAO;
import org.pmp.util.Pager;
import org.pmp.vo.LiftMeterItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class LiftMeterDAO extends BaseDAO implements ILiftMeterItemDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(LiftMeterDAO.class.getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.ILiftMeterItemDAO#batchSaveLiftMeterItem(java.util.List)
     */
    @Override
    public void batchSaveLiftMeterItem(List<LiftMeterItem> list) {
	/* list.size will not be greater than 10, so we can use for cyclic sentence */
	String debugMsg = "save LiftMeterItem";
	try {
	    for(LiftMeterItem pmi : list){
		saveInstance(pmi, debugMsg);
	    }
	} catch (RuntimeException e){
	    throw e;
	}

    }

    /**
     * @see org.pmp.dao.business.ILiftMeterItemDAO#loadProMeterItemList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<LiftMeterItem> loadProMeterItemList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

}
