/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午10:40:02
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IFixedParkFeeItemDAO;
import org.pmp.service.business.IFixedParkFeeItemService;
import org.pmp.util.Pager;
import org.pmp.vo.FixedParkFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class FixedParkFeeItemService implements IFixedParkFeeItemService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(FixedParkFeeItemService.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IFixedParkFeeItemDAO fpfiDAO;
    //~ Methods ========================================================================================================
    public void saveFixedParkFeeItem(FixedParkFeeItem instance){
	fpfiDAO.saveFixedParkFeeItem(instance);
    }
    public void editFixedParkFeeItem(FixedParkFeeItem instance){
	fpfiDAO.updateFixedParkFeeItem(instance);
    }
    public void deleteFixedParkFeeItem(Integer fpfiId){
	fpfiDAO.deleteFixedParkFeeItem(fpfiId);
    }
    
    public FixedParkFeeItem getFixedParkFeeItemByID(Integer fpfiId){
	return fpfiDAO.getFixedParkFeeItemByID(fpfiId);
    }
    
    public List<?> loadFixedParkFeeItemList_ByPro(Pager pager,Integer proId){
	return fpfiDAO.loadFixedParkFeeItemList_ByPro(pager, proId);
    }
    public List<?> loadFixedParkFeeItemList_ByCom(Pager pager,Integer comId){
	return fpfiDAO.loadFixedParkFeeItemList_ByCom(pager, comId);
    }
    
    //~ Getters and Setters ============================================================================================
    public IFixedParkFeeItemDAO getFpfiDAO() {
        return fpfiDAO;
    }
    public void setFpfiDAO(IFixedParkFeeItemDAO fpfiDAO) {
        this.fpfiDAO = fpfiDAO;
    }

}
