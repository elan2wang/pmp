/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午11:02:36
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IFixedParkFeeDAO;
import org.pmp.service.business.IFixedParkFeeService;
import org.pmp.util.Pager;
import org.pmp.vo.FixedParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class FixedParkFeeService implements IFixedParkFeeService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger
	    .getLogger(FixedParkFeeService.class.getName());
    //~ Instance Fields ================================================================================================
    private IFixedParkFeeDAO fpfDAO;
   
    //~ Methods ========================================================================================================
    public FixedParkFee getFixedParkFeeByID(Integer fpfId){
	return fpfDAO.getFixedParkFeeByID(fpfId);
    }
    public void editFixedParkFee(FixedParkFee instance){
	fpfDAO.updateFixedParkFee(instance);
    }
    public void deleteFixedParkFee(Integer fpfId){
	fpfDAO.deleteFixedParkFee(fpfId);
    }
    
    public List<?> loadFixedParkFeeListBy_fpfiId(Pager pager,Integer fpfiId){
	return fpfDAO.loadFixedParkFeeListBy_fpfiId(pager, fpfiId);
    }
    //~ Getters and Setters ============================================================================================
    public IFixedParkFeeDAO getFpfDAO() {
        return fpfDAO;
    }
    public void setFpfDAO(IFixedParkFeeDAO fpfDAO) {
        this.fpfDAO = fpfDAO;
    }

}
