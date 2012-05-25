/**
 * Author            : Elan
 * Created On        : 2012-4-20 下午03:59:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.pmp.jms.JmsPublisher;
import org.pmp.service.business.ISmsCompanyService;
import org.pmp.service.business.ISmsSendService;
import org.pmp.service.impl.business.SmsSendService;
import org.pmp.util.SessionHandler;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Owner;
import org.pmp.vo.SMSCompany;
import org.pmp.vo.SMSSend;

import com.chinamobile.openmas.client.Sms;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsAdvice {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(SmsAdvice.class.getName());

    //~instance Fields =================================================================================================
    private ISmsSendService smsSendService;
    
    //~ Methods ========================================================================================================
    /**
     * 当录入物业费时自动触发该方法,发送短信给业主
     */
    public void inputAdvice(JoinPoint jp) throws Exception{
	Object[] args = jp.getArgs();
	StringBuilder timeStr = new StringBuilder();
	Double fetchMoney = 0.0;
	Owner owner = null;
	for (Object obj : (ArrayList<Object>)args[0]){
	    CondoFee cf = (CondoFee)obj;
	    owner = cf.getOwner();
	    fetchMoney += cf.getFetchMoney();
	    timeStr.append(cf.getCfYear()+"-"+cf.getCfMonth()+"、");
	}
	StringBuilder contents = new StringBuilder();
	SMSSend smss = new SMSSend();
	smss.setSmssReceiver(owner.getMobile());
	smss.setSMSCompany(SessionHandler.getSMSCompany());
	smss.setSmssPerson(SessionHandler.getUser().getUsername());
	smss.setSmssState("new");
	smss.setSmssTime(new Date());
	contents.append("尊敬的业主:"+owner.getOwnerName()+",您好!感谢您缴纳"+timeStr.deleteCharAt(timeStr.length()-1));
	contents.append("的物业费,总计:"+fetchMoney.toString()+"元,如果问题请及时联系我们");
	smss.setSmssContent(contents.toString());
	smsSendService.saveSmsSend(smss);
	logger.debug(contents.toString());
	/* send a message to the message queue */
	JmsPublisher.sendMessgae(smss.getSmssId().toString());
    }
    
    
    //~ setters and getters =================================================================================
    public ISmsSendService getSmsSendService() {
        return smsSendService;
    }

    public void setSmsSendService(ISmsSendService smsSendService) {
        this.smsSendService = smsSendService;
    }
}
