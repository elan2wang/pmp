/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午05:51:18
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.business.ICondoFeeDAO;
import org.pmp.dao.business.ICondoFeeItemDAO;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.util.Pager;
import org.pmp.vo.CondoFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeItemService implements ICondoFeeItemService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CondoFeeItemService.class.getName());
    //~ Instance Fields ================================================================================================
    private ICondoFeeItemDAO condoFeeItemDAO;
    private ICondoFeeDAO condoFeeDAO;
    
    //~ Methods ========================================================================================================
    public void addCondoFeeItem(CondoFeeItem instance){
	condoFeeItemDAO.saveCondoFeeItem(instance);
	logger.debug(instance.getCfiId());
	condoFeeDAO.generateCondoFee(instance.getCfiId());
    }
    
    public void deleteCondoFeeItem(Integer cfiId){
	condoFeeItemDAO.deleteCondoFeeItem(cfiId);
    }
    
    public List<?> getCondoFeeYear(){
	return condoFeeItemDAO.getCondoFeeYear();
    }
    
    public CondoFeeItem getCondoFeeItemByID(Integer cfiId){
	return condoFeeItemDAO.getCondoFeeItemByID(cfiId);
    }
    
    public List<?> loadCondoFeeItemListBy_ComID(Pager pager,Integer comId){
	return condoFeeItemDAO.loadCondoFeeItemListBy_ComID(pager, comId);
    }
    
    //~ Getters and Setters ============================================================================================

    public ICondoFeeItemDAO getCondoFeeItemDAO() {
        return condoFeeItemDAO;
    }

    public void setCondoFeeItemDAO(ICondoFeeItemDAO condoFeeItemDAO) {
        this.condoFeeItemDAO = condoFeeItemDAO;
    }

    public ICondoFeeDAO getCondoFeeDAO() {
        return condoFeeDAO;
    }

    public void setCondoFeeDAO(ICondoFeeDAO condoFeeDAO) {
        this.condoFeeDAO = condoFeeDAO;
    }

}
