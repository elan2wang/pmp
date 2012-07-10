/**
 * Author            : Elan
 * Created On        : 2012-7-10 下午12:40:18
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
import org.pmp.dao.business.IPublicRepairDAO;
import org.pmp.service.business.IPublicRepairService;
import org.pmp.util.Pager;
import org.pmp.vo.PublicRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PublicRepairService implements IPublicRepairService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PublicRepairService.class.getName());

    //~ Instance Fields ================================================================================================
    private IPublicRepairDAO publicRepairDAO;

    //~ Methods ========================================================================================================
    
    /**
     * @see org.pmp.service.business.IPublicRepairService#addPublicRepair(org.pmp.vo.PublicRepair)
     */
    @Override
    public void addPublicRepair(PublicRepair instance) {
	publicRepairDAO.savePublicRepair(instance);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairService#editPublicRepair(org.pmp.vo.PublicRepair)
     */
    @Override
    public void editPublicRepair(PublicRepair instance) {
	publicRepairDAO.updatePublicRepair(instance);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairService#batchDeletePublicRepair(java.util.List)
     */
    @Override
    public void batchDeletePublicRepair(List<PublicRepair> list) {
	publicRepairDAO.batchDeletePublicRepair(list);

    }

    /**
     * @see org.pmp.service.business.IPublicRepairService#getPublicRepairByID(java.lang.Integer)
     */
    @Override
    public PublicRepair getPublicRepairByID(Integer fbId) {
	return publicRepairDAO.getPublicRepairByID(fbId);
    }

    /**
     * @see org.pmp.service.business.IPublicRepairService#loadPublicRepairList_ByFBI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<PublicRepair> loadPublicRepairList_ByFBI(Integer fbiId,
	    Map<String, Object> params, String order, Pager pager) {
	return publicRepairDAO.loadPublicRepairList_ByFBI(fbiId, params, order, pager);
    }
    //~ Getters and Setters ============================================================================================

    public IPublicRepairDAO getPublicRepairDAO() {
        return publicRepairDAO;
    }

    public void setPublicRepairDAO(IPublicRepairDAO publicRepairDAO) {
        this.publicRepairDAO = publicRepairDAO;
    }

}
