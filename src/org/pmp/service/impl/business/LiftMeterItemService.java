/**
 * Author            : Elan
 * Created On        : 2012-6-21 上午11:13:45
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
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.ILiftMeterItemDAO;
import org.pmp.service.business.ILiftMeterItemService;
import org.pmp.util.Pager;
import org.pmp.vo.LiftMeterItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class LiftMeterItemService implements ILiftMeterItemService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(LiftMeterItemService.class
	    .getName());

    //~ Instance Fields ================================================================================================
    private ILiftMeterItemDAO liftMeterItemDAO;
    
    //~ Methods ========================================================================================================

    
    /**
     * @see org.pmp.service.business.ILiftMeterItemService#batchAddLiftMeterItem(java.util.List)
     */
    @Override
    public void batchAddLiftMeterItem(List<LiftMeterItem> list) {
	liftMeterItemDAO.batchSaveLiftMeterItem(list);
    }

    /**
     * @see org.pmp.service.business.ILiftMeterItemService#loadProMeterItemList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<LiftMeterItem> loadProMeterItemList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	return liftMeterItemDAO.loadProMeterItemList_ByEFI(efiId, params, order, pager);
    }
    
    //~ Getters and Setters ============================================================================================

    public ILiftMeterItemDAO getLiftMeterItemDAO() {
        return liftMeterItemDAO;
    }

    public void setLiftMeterItemDAO(ILiftMeterItemDAO liftMeterItemDAO) {
        this.liftMeterItemDAO = liftMeterItemDAO;
    }

}
