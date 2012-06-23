/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:24:58
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
import org.pmp.dao.business.IElectricFeeDAO;
import org.pmp.service.business.IElectricFeeService;
import org.pmp.util.Pager;
import org.pmp.vo.ElectricFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeService implements IElectricFeeService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeService.class.getName());

    //~ Instance Fields ================================================================================================
    private IElectricFeeDAO electricFeeDAO;
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IElectricFeeService#generateElectricFee(java.lang.Integer)
     */
    @Override
    public void generateElectricFee(Integer efiId) {
	electricFeeDAO.generateElectricFee(efiId);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeService#batchEditElectricFee(java.util.List)
     */
    @Override
    public void batchEditElectricFee(List<ElectricFee> list) {
	electricFeeDAO.batchUpdateElectricFee(list);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeService#batchDeleteElectricFee(java.util.List)
     */
    @Override
    public void batchDeleteElectricFee(List<ElectricFee> list) {
	electricFeeDAO.batchDeleteElectricFee(list);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeService#loadElectricFeeList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	return electricFeeDAO.loadElectricFeeList_ByEFI(efiId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeService#loadElectricFeeList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return electricFeeDAO.loadElectricFeeList_ByCompany(comId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.IElectricFeeService#loadElectricFeeList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return electricFeeDAO.loadElectricFeeList_ByProject(proId, params, order, pager);
    }
    
    /**
     * @see org.pmp.service.business.IElectricFeeService#loadElectricFeeList_ByHouse(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByHouse(Integer houseId,
	    Map<String, Object> params, String order, Pager pager) {
	return electricFeeDAO.loadElectricFeeList_ByHouse(houseId, params, order, pager);
    }

    
    //~ Getters and Setters ============================================================================================

    public IElectricFeeDAO getElectricFeeDAO() {
        return electricFeeDAO;
    }

    public void setElectricFeeDAO(IElectricFeeDAO electricFeeDAO) {
        this.electricFeeDAO = electricFeeDAO;
    }

   
}
