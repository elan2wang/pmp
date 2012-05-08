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
    public List<?> loadCondoFeeListBy_cfiId(Integer cfiId, Pager pager) {
	return condoFeeDAO.loadCondoFeeListBy_cfiId(cfiId, pager);
    }
    
    public CondoFee getCondoFeeByID(Integer cfId) {
	return condoFeeDAO.getCondoFeeByID(cfId);
    }
    public void inputCondoFee(CondoFee instance) {
	condoFeeDAO.updateCondoFee(instance);
    }
    public void auditCondoFee(CondoFee instance) {
	condoFeeDAO.updateCondoFee(instance);
    }

    public void deleteCondoFee(Integer cfId) {
	condoFeeDAO.deleteCondoFee(cfId);
    }

    public List<?> loadPayedCondoFeeList(Integer cfiId,Pager pager){
	return condoFeeDAO.loadPayedCondoFeeList(cfiId, pager);
    }
    
    public List<?> loadNonePayedCondoFeeList(Integer cfiId,Pager pager){
	return condoFeeDAO.loadNonePayedCondoFeeList(cfiId, pager);
    }
    
    @Override
    public List<CondoFee> loadCondoFeeList_ByOwner(Integer ownerId,Map<String,Object>params,String order,Pager pager)
    {
	return condoFeeDAO.loadCondoFeeList_ByOwner(ownerId, params, order, pager);
    }
    
    //~ Getters and Setters ============================================================================================

    public ICondoFeeDAO getCondoFeeDAO() {
        return condoFeeDAO;
    }

    public void setCondoFeeDAO(ICondoFeeDAO condoFeeDAO) {
        this.condoFeeDAO = condoFeeDAO;
    }

}
