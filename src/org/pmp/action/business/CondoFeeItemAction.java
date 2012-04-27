/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午05:55:32
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.CondoFeeItem;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeItemAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static final long serialVersionUID = -523253506798519261L;
    private static Logger logger = Logger.getLogger(CondoFeeItemAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private ICondoFeeItemService condoFeeItemService;
    
    private CondoFeeItem condoFeeItem;
    
    //~ Methods ========================================================================================================
    public String addCondoFeeItem(){
	/* set the related project with the value retrieved from SessionHandler */
	Project project = (Project)SessionHandler.getUserRefDomain();
	condoFeeItem.setProject(project);
	/* set generateTime */
	condoFeeItem.setGenerateTime(new Date());
	condoFeeItemService.addCondoFeeItem(condoFeeItem);
	
	return SUCCESS;
    }
    
    public String loadCondoFeeItemList(){
	Pager pager = new Pager(10000,1);
	//set proId/comId with the value retrieved from SessionHandler
	Integer proId = ((Project)SessionHandler.getUserRefDomain()).getProId();
	
	List condoFeeItemList = condoFeeItemService.loadCondoFeeItemListBy_ProID(pager,proId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("condoFeeItemList", condoFeeItemList);
	return SUCCESS;
    }
    
    //~ Getters and Setters ============================================================================================

    public ICondoFeeItemService getCondoFeeItemService() {
        return condoFeeItemService;
    }

    public void setCondoFeeItemService(ICondoFeeItemService condoFeeItemService) {
        this.condoFeeItemService = condoFeeItemService;
    }

    public CondoFeeItem getCondoFeeItem() {
        return condoFeeItem;
    }

    public void setCondoFeeItem(CondoFeeItem condoFeeItem) {
        this.condoFeeItem = condoFeeItem;
    }
}
