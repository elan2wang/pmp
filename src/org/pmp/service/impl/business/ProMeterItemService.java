/**
 * Author            : Elan
 * Created On        : 2012-6-21 上午11:11:26
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
import org.pmp.dao.business.IProMeterItemDAO;
import org.pmp.service.business.IProMeterItemService;
import org.pmp.util.Pager;
import org.pmp.vo.ProMeterItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ProMeterItemService implements IProMeterItemService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ProMeterItemService.class.getName());

    //~ Instance Fields ================================================================================================
    private IProMeterItemDAO proMeterItemDAO;

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IProMeterItemService#batchAddProMeterItem(java.util.List)
     */
    @Override
    public void batchAddProMeterItem(List<ProMeterItem> list) {
	proMeterItemDAO.batchSaveProMeterItem(list);
    }

    /**
     * @see org.pmp.service.business.IProMeterItemService#loadProMeterItemList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ProMeterItem> loadProMeterItemList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	return proMeterItemDAO.loadProMeterItemList_ByEFI(efiId, params, order, pager);
    }

    //~ Getters and Setters ============================================================================================

    public IProMeterItemDAO getProMeterItemDAO() {
        return proMeterItemDAO;
    }

    public void setProMeterItemDAO(IProMeterItemDAO proMeterItemDAO) {
        this.proMeterItemDAO = proMeterItemDAO;
    }

}
