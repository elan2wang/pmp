/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:27:52
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.action.business;

import org.apache.log4j.Logger;
import org.pmp.service.impl.business.ElectricFeeItemService;
import org.pmp.vo.ElectricFeeItem;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeItemAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeItemAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private ElectricFeeItemService electricFeeItemService;
    
    private ElectricFeeItem electricFeeItem;
    private Integer proId;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String addElectricFeeItem(){
	
	
	return SUCCESS;
    }
    
    //~ Getters and Setters ============================================================================================

}
