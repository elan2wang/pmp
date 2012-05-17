/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午05:03:03
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.jms.JmsPublisher;
import org.pmp.service.business.ISmsSendService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Project;
import org.pmp.vo.SMSSend;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsSendAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(SmsSendAction.class.getName());
    //~ Instance Fields ================================================================================================
    private ISmsSendService smsSendService;
    private SMSSend smsSend;
    
    //~ Methods ========================================================================================================
    public String addSmsSend(){
	/* set the SMSCompany for smsSend with the value retrieved from SessionHandler */
	smsSend.setSMSCompany(SessionHandler.getSMSCompany());
	/* set the smssPerson for smsSend with the value retrieved from SessionHandler */
	smsSend.setSmssPerson(SessionHandler.getUser().getUsername());
	/* set the smssTime with currentTime */
	smsSend.setSmssTime(new Date());
	/* set the smssState to new */
	smsSend.setSmssState("new");
	smsSendService.saveSmsSend(smsSend);
	
	/* after saving the smsSend got its id */
	/* invoke JmsPublisher.sendMessgae() method to send a message to the message queue */
	JmsPublisher.sendMessgae(smsSend.getSmssId().toString());
	
	return SUCCESS;
    }
    
    public void loadHistory(){
	logger.debug("aaaaaaaa");
	List<?> list = null;
	Map<String,Object> params = new HashMap<String,Object>();
	String order = null;
	Pager pager = new Pager(1000,1);
	
	Object obj = SessionHandler.getUserRefDomain();
	if (obj instanceof Company){
	    logger.debug("company user");
	    list = smsSendService.loadSmsSend_ByCompany(((Company)obj).getComId(), pager, params, order);
	}
	else if (obj instanceof Project){
	    /* retrieve username from SessionHandler */
	    logger.debug("project user");
	    String username = SessionHandler.getUser().getUsername();
	    list = smsSendService.loadSmsSend_ByPerson(username, pager, params, order);
	} else {
	    list = smsSendService.loadAllSmsSend(pager, params, order);
	}
	
	String data = JsonConvert.list2FlexJson(pager, list, "org.pmp.vo.SMSSend");
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    //~ Getters and Setters ============================================================================================
    public ISmsSendService getSmsSendService() {
        return smsSendService;
    }

    public void setSmsSendService(ISmsSendService smsSendService) {
        this.smsSendService = smsSendService;
    }

    public SMSSend getSmsSend() {
        return smsSend;
    }

    public void setSmsSend(SMSSend smsSend) {
        this.smsSend = smsSend;
    }
    
    
}
