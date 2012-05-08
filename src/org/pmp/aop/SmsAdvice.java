/**
 * Author            : Elan
 * Created On        : 2012-4-20 下午03:59:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.pmp.service.business.ISMSCompanyService;
import org.pmp.util.SessionHandler;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Owner;
import org.pmp.vo.SMSCompany;

import com.chinamobile.openmas.client.Sms;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsAdvice {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SmsAdvice.class.getName());
    //~ Instance Fields ================================================================================================
    private ISMSCompanyService smsCompanyService;

    //~ Methods ========================================================================================================
    
    /**
     * @Title: sendMessage
     * @Description: called after the method "editCondoFee()" in ICondoFeeService was execute;
     * send message to the owner to inform them that they've successfully payed the condoFee
     *
     * @param  jp  JointPoint
     */
    public void sendMessage(JoinPoint jp) throws Exception{
	logger.debug("进入sendMessage");
	/* retrieve Owner instance from JointPoint */
	Object[] args = jp.getArgs();
	CondoFee fee = (CondoFee)args[0];
	Owner owner = fee.getOwner();
	/* set the receiver */
	String mobile = owner.getMobile();
	String[] dest = new String[]{mobile};
        /* set the message to send */
        StringBuffer msg = new StringBuffer();
        msg.append("尊敬的业主:"+owner.getOwnerName()+",您好！");
        msg.append("感谢您缴纳物业费");
        String message = msg.toString();
	/* retrieve SessionHandler to get SMS_Company instance */
        SMSCompany smsc = SessionHandler.getSMSCompany();
        /* set the Sms properties */
        Sms sms = new Sms(smsc.getSmsUpUrl());
        String extendCode = smsc.getExtendCode();
        String applicationId = smsc.getUsername();
        String password = smsc.getPassword();
        String res = sms.SendMessage(dest, message, extendCode, applicationId, password);
        logger.debug(res);
    }
    
    //~ Getters and Setters ============================================================================================

    public ISMSCompanyService getSmsCompanyService() {
        return smsCompanyService;
    }

    public void setSmsCompanyService(ISMSCompanyService smsCompanyService) {
        this.smsCompanyService = smsCompanyService;
    }

}
