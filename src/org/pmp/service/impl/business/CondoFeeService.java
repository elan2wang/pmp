/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:26:27
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.ICondoFeeDAO;
import org.pmp.service.business.ICondoFeeService;
import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeService implements ICondoFeeService {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CondoFeeService.class.getName());

    //~ Instance Fields ================================================================================================
    private ICondoFeeDAO condoFeeDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    
    public void batchSetOughtMoney(List<CondoFee> list){
	condoFeeDAO.batchSetOughtMoney(list);
    }
    
    public void batchInput(List<CondoFee> list){
	condoFeeDAO.batchInput(list);
    }
    
    public void batchAudit(List<CondoFee> list){
	condoFeeDAO.batchAudit(list);
    }
    public void batchDelete(List<CondoFee> list) {
	condoFeeDAO.batchDelete(list);
    }
    
    /**
     * @see org.pmp.service.business.ICondoFeeService#getCondoFee_ById(java.lang.Integer)
     */
    @Override
    public CondoFee getCondoFee_ById(Integer cfId) {
	return this.condoFeeDAO.getCondoFee_ById(cfId);
    }

    
    @Override
    public List<CondoFee> loadCondoFeeList_ByHouse(Integer houseId,Map<String,Object>params,String order,Pager pager)
    {
	return condoFeeDAO.loadCondoFeeList_ByHouse(houseId, params, order, pager);
    }
    
    /**
     * @see org.pmp.service.business.ICondoFeeService#loadCondoFeeList_ByIds(java.util.List)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByIds(List<Integer> ids) {
	return condoFeeDAO.loadCondoFeeList_ByIds(ids);
    }

    /**
     * @see org.pmp.service.business.ICondoFeeService#loadCondoFeeList_ByCFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByCFI(Integer cfiId,
	    Map<String, Object> params, String order, Pager pager) {
	return condoFeeDAO.loadCondoFeeList_ByCFI(cfiId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.ICondoFeeService#loadCondoFeeList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	return condoFeeDAO.loadCondoFeeList_ByCompany(comId, params, order, pager);
    }

    /**
     * @see org.pmp.service.business.ICondoFeeService#loadCondoFeeList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	return condoFeeDAO.loadCondoFeeList_ByProject(proId, params, order, pager);
    }

    //~ Getters and Setters ============================================================================================

    public ICondoFeeDAO getCondoFeeDAO() {
        return condoFeeDAO;
    }

    public void setCondoFeeDAO(ICondoFeeDAO condoFeeDAO) {
        this.condoFeeDAO = condoFeeDAO;
    }

}
