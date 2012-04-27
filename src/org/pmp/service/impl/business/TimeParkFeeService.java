/**
 * Author            : Elan
 * Created On        : 2012-4-18 上午11:30:40
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.business.ITimeParkFeeDAO;
import org.pmp.service.business.ITimeParkFeeService;
import org.pmp.util.Pager;
import org.pmp.vo.TimeParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class TimeParkFeeService implements ITimeParkFeeService {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(TimeParkFeeService.class.getName());
    //~ Instance Fields ================================================================================================
    private ITimeParkFeeDAO timeParkFeeDAO;
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public void saveTimeParkFee(TimeParkFee instance){
	timeParkFeeDAO.saveTimeParkFee(instance);
    }
    public void batchAdd(List<TimeParkFee> list){
	timeParkFeeDAO.batchAdd(list);
    }
    public void editTimeParkFee(TimeParkFee instance){
	timeParkFeeDAO.updateTimeParkFee(instance);
    }
    public void deleteTimeParkFee(Integer tpfId){
	timeParkFeeDAO.deleteTimeParkFee(tpfId);
    }
    public TimeParkFee getTimeParkFeeByID(Integer tpfId){
	return timeParkFeeDAO.getTimeParkFeeByID(tpfId);
    }
    
    public List<?> loadByProject(Pager pager,Integer proId){
	return timeParkFeeDAO.loadByProject(pager, proId);
    }
    
    //~ Getters and Setters ============================================================================================
    public ITimeParkFeeDAO getTimeParkFeeDAO() {
        return timeParkFeeDAO;
    }
    public void setTimeParkFeeDAO(ITimeParkFeeDAO timeParkFeeDAO) {
        this.timeParkFeeDAO = timeParkFeeDAO;
    }
    
}
