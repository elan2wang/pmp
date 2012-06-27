/**
 * Author            : Elan
 * Created On        : 2012-5-8 下午03:26:51
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.constant.SmsState;
import org.pmp.jms.JmsPublisher;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.admin.IUserService;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IOwnerService;
import org.pmp.service.business.IProjectService;
import org.pmp.service.business.ISmsSendService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;
import org.pmp.vo.SMSSend;
import org.pmp.vo.TbUser;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsAction extends BaseAction{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(SmsAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IOwnerService ownerService;
    private IUserService userService;
    private IProjectService projectService;
    private IBuildingService buildingService;
    private ISmsSendService smsSendService;
    
    private SMSSend smsSend;
    /* used when load_owner_list */
    private Integer proId;
    private Integer builId;
    
    //~ Methods ========================================================================================================
    public void load_owner_list(){
        Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();

        List<?> list = null;
        if (proId!=null && builId!=null){
            list = ownerService.loadOwnerList_ByBuil(builId, params, order, pager);
        } else if (proId!=null && builId==null){
            list = ownerService.loadOwnerList_ByPro(proId, params, order, pager);
        } else {
            list = new ArrayList<Owner>();;
	}
	
        String[] attrs = {"ownerId","ownerName","mobile","houseNum"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(list, "", pager);
	MyJson.print(data);
    }
    
    public void load_user_list(){
	List<?> list = null;
        Pager pager = new Pager(rp,page);
        
	if (proId!=null){
	    list = userService.loadUserList_ByProject(pager, proId);
	} else {
	    list = new ArrayList<TbUser>();	
	}
	
	String[] attrs = {"userId","username","realname","mobile","position","userDesc"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(list, "", pager);
	MyJson.print(data);
    }
    
    public String addSmsSend(){
	/* set the SMSCompany for smsSend with the value retrieved from SessionHandler */
	smsSend.setSMSCompany(SessionHandler.getSMSCompany());
	/* set the smssPerson for smsSend with the value retrieved from SessionHandler */
	smsSend.setSmssPerson(SessionHandler.getUser().getUsername());
	/* set the smssTime with currentTime */
	smsSend.setSmssTime(new Date());
	/* set the smssState to new */
	smsSend.setSmssState(SmsState.NEW);
	smsSendService.addSmsSend(smsSend);
	
	/* after saving the smsSend got its id */
	/* invoke JmsPublisher.sendMessgae() method to send a message to the message queue */
	JmsPublisher.sendMessgae(smsSend.getSmssId().toString());
	
	return SUCCESS;
    }
    
    public void loadHistory(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();

	List<?> list = null;
	Object obj = SessionHandler.getUserRefDomain();
	if (obj instanceof Company){
	    list = smsSendService.loadSmsSend_ByCompany(((Company)obj).getComId(), pager, params, order);
	}
	else if (obj instanceof Project){
	    /* retrieve username from SessionHandler */
	    String username = SessionHandler.getUser().getUsername();
	    list = smsSendService.loadSmsSend_ByPerson(username, pager, params, order);
	} else {
	    list = smsSendService.loadAllSmsSend(pager, params, order);
	}
	
	String[] attrs = {"smssId","SMSCompany.company.comName","smssContent","smssReceiver","smssState","smssTime","smssPerson"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(list, "", pager);
	MyJson.print(data);
    }
    //~ Getters and Setters ============================================================================================

    public IOwnerService getOwnerService() {
        return ownerService;
    }

    public void setOwnerService(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    public IBuildingService getBuildingService() {
        return buildingService;
    }

    public void setBuildingService(IBuildingService buildingService) {
        this.buildingService = buildingService;
    }
    
    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getBuilId() {
        return builId;
    }

    public void setBuilId(Integer builId) {
        this.builId = builId;
    }

    public SMSSend getSmsSend() {
        return smsSend;
    }

    public void setSmsSend(SMSSend smsSend) {
        this.smsSend = smsSend;
    }

    public ISmsSendService getSmsSendService() {
        return smsSendService;
    }

    public void setSmsSendService(ISmsSendService smsSendService) {
        this.smsSendService = smsSendService;
    }

}
