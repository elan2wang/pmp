/**
 * Author            : Elan
 * Created On        : 2012-6-24 下午10:28:03
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
import org.pmp.dao.business.IElectricFeeChargeDAO;
import org.pmp.service.business.IElectricFeeChargeService;
import org.pmp.util.Pager;
import org.pmp.vo.ElectricFeeCharge;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeChargeService implements IElectricFeeChargeService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger
	    .getLogger(ElectricFeeChargeService.class.getName());

    //~ Instance Fields ================================================================================================
    private IElectricFeeChargeDAO electricFeeChargeDAO;

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IElectricFeeChargeService#addElectricFeeCharge(org.pmp.vo.ElectricFeeCharge)
     */
    @Override
    public void addElectricFeeCharge(ElectricFeeCharge instance) {
	electricFeeChargeDAO.saveElectricFeeCharge(instance);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeChargeService#batchEditElectricFeeCharge(java.util.List)
     */
    @Override
    public void batchEditElectricFeeCharge(List<ElectricFeeCharge> list) {
	electricFeeChargeDAO.batchUpdateElectricFeeCharge(list);
	
    }

    /**
     * @see org.pmp.service.business.IElectricFeeChargeService#deleteElectricFeeCharge(org.pmp.vo.ElectricFeeCharge)
     */
    @Override
    public void deleteElectricFeeCharge(ElectricFeeCharge instance) {
	electricFeeChargeDAO.deleteElectricFeeCharge(instance);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeChargeService#getElectricFeeCharge_ByID(java.lang.Integer)
     */
    @Override
    public ElectricFeeCharge getElectricFeeCharge_ByID(Integer efcId) {
	return electricFeeChargeDAO.getElectricFeeCharge_ByID(efcId);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeChargeService#loadElectricFeeChargeList_ByHouse(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFeeCharge> loadElectricFeeChargeList_ByHouse(
	    Integer houseId, Map<String, Object> params, String order,
	    Pager pager) {
	return electricFeeChargeDAO.loadElectricFeeChargeList_ByHouse(houseId, params, order, pager);
    }
    //~ Getters and Setters ============================================================================================

    public IElectricFeeChargeDAO getElectricFeeChargeDAO() {
        return electricFeeChargeDAO;
    }

    public void setElectricFeeChargeDAO(IElectricFeeChargeDAO electricFeeChargeDAO) {
        this.electricFeeChargeDAO = electricFeeChargeDAO;
    }

}
