/**
 * Author            : Elan
 * Created On        : 2012-7-10 上午09:42:07
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
import org.pmp.dao.business.IPublicRepairItemDAO;
import org.pmp.service.business.IPublicRepairItemService;
import org.pmp.util.Pager;
import org.pmp.vo.PublicRepairItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PublicRepairItemService implements IPublicRepairItemService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PublicRepairItemService.class.getName());

    //~ Instance Fields ================================================================================================
    public IPublicRepairItemDAO publicRepairItemDAO;
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IPublicRepairItemService#addPublicRepairItem(org.pmp.vo.PublicRepairItem)
     */
    @Override
    public void addPublicRepairItem(PublicRepairItem instance) {
	publicRepairItemDAO.savePublicRepairItem(instance);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairItemService#editPublicRepairItem(org.pmp.vo.PublicRepairItem)
     */
    @Override
    public void editPublicRepairItem(PublicRepairItem instance) {
	publicRepairItemDAO.updatePublicRepairItem(instance);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairItemService#batchDeletePublicRepairItem(java.util.List)
     */
    @Override
    public void batchDeletePublicRepairItem(List<PublicRepairItem> list) {
	publicRepairItemDAO.batchDeletePublicRepairItem(list);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairItemService#getPublicRepairItemByID(java.lang.Integer)
     */
    @Override
    public PublicRepairItem getPublicRepairItemByID(Integer fbiId) {
	return publicRepairItemDAO.getPublicRepairItemByID(fbiId);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairItemService#loadPublicRepairItemList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<PublicRepairItem> loadPublicRepairItemList_ByCompany(
	    Integer comId, Map<String, Object> params, String order, Pager pager) {
	return publicRepairItemDAO.loadPublicRepairItemList_ByCompany(comId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairItemService#loadPublicRepairItemList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<PublicRepairItem> loadPublicRepairItemList_ByProject(
	    Integer proId, Map<String, Object> params, String order, Pager pager) {
	return publicRepairItemDAO.loadPublicRepairItemList_ByProject(proId, params, order, pager);
    }
    
    //~ Getters and Setters ============================================================================================

    public IPublicRepairItemDAO getPublicRepairItemDAO() {
        return publicRepairItemDAO;
    }

    public void setPublicRepairItemDAO(IPublicRepairItemDAO publicRepairItemDAO) {
        this.publicRepairItemDAO = publicRepairItemDAO;
    }

}
