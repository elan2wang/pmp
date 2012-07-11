/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:21:25
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
import org.pmp.dao.business.IElectricFeeDAO;
import org.pmp.dao.business.IElectricFeeItemDAO;
import org.pmp.service.business.IElectricFeeItemService;
import org.pmp.util.Pager;
import org.pmp.vo.ElectricFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeItemService implements IElectricFeeItemService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeItem.class.getName());

    //~ Instance Fields ================================================================================================
    private IElectricFeeItemDAO electricFeeItemDAO;
    private IElectricFeeDAO electricFeeDAO;
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IElectricFeeItemService#addElectricFeeItem(org.pmp.vo.ElectricFeeItem)
     */
    @Override
    public void addElectricFeeItem(ElectricFeeItem instance) {
	electricFeeItemDAO.saveElectricFeeItem(instance);

    }
    
    /**
     * @see org.pmp.service.business.IElectricFeeItemService#editElectricFeeItem(org.pmp.vo.ElectricFeeItem)
     */
    @Override
    public void editElectricFeeItem(ElectricFeeItem instance) {
	electricFeeItemDAO.updateElectricFeeItem(instance);
    }
    
    /**
     * @see org.pmp.service.business.IElectricFeeItemService#deleteElectricFeeItem(org.pmp.vo.ElectricFeeItem)
     */
    @Override
    public void deleteElectricFeeItem(ElectricFeeItem instance) {
	electricFeeItemDAO.deleteElectricFeeItem(instance);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeItemService#getElectricFeeItemByID(java.lang.Integer)
     */
    @Override
    public org.pmp.vo.ElectricFeeItem getElectricFeeItemByID(Integer efiId) {
	return electricFeeItemDAO.getElectricFeeItemByID(efiId);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeItemService#loadElectricFeeItemList_ByCompany(java.lang.Integer, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<org.pmp.vo.ElectricFeeItem> loadElectricFeeItemList_ByCompany(
	    Integer comId, String order, Pager pager) {
	return electricFeeItemDAO.loadElectricFeeItemList_ByCompany(comId, order, pager);
    }
    
    /**
     * @see org.pmp.service.business.IElectricFeeItemService#loadElectricFeeItemList_ByProject(java.lang.Integer, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFeeItem> loadElectricFeeItemList_ByProject(
	    Integer proId, String order, Pager pager) {
	return electricFeeItemDAO.loadElectricFeeItemList_ByProject(proId, order, pager);
    }


    //~ Getters and Setters ============================================================================================

    public IElectricFeeItemDAO getElectricFeeItemDAO() {
        return electricFeeItemDAO;
    }

    public void setElectricFeeItemDAO(IElectricFeeItemDAO electricFeeItemDAO) {
        this.electricFeeItemDAO = electricFeeItemDAO;
    }

    public IElectricFeeDAO getElectricFeeDAO() {
        return electricFeeDAO;
    }

    public void setElectricFeeDAO(IElectricFeeDAO electricFeeDAO) {
        this.electricFeeDAO = electricFeeDAO;
    }

   
}
