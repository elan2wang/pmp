/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:08:15
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
import org.pmp.dao.business.IProMeterItemDAO;
import org.pmp.util.Pager;
import org.pmp.vo.ProMeterItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ProMeterItemDAO extends BaseDAO implements IProMeterItemDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ProMeterItemDAO.class.getName());

    //~ Instance Fields ================================================================================================
  
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IProMeterItemDAO#batchSaveProMeterItem(java.util.List)
     */
    @Override
    public void batchSaveProMeterItem(List<ProMeterItem> list) {
	/* list.size will not be greater than 10, so we can use for cyclic sentence */
	String debugMsg = "save proMeterItem";
	try {
	    for(ProMeterItem pmi : list){
		saveInstance(pmi, debugMsg);
	    }
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IProMeterItemDAO#loadProMeterItemList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ProMeterItem> loadProMeterItemList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

}
