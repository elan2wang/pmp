/**
 * Author            : Elan
 * Created On        : 2012-4-24 下午09:22:40
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.sms;

import org.apache.log4j.Logger;
import org.pmp.service.business.ISMSCompanyService;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsSend {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SmsSend.class.getName());
    //~ Instance Fields ================================================================================================
    private ISMSCompanyService smsCompanyService;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void sendMessage(){
	
    }
    //~ Getters and Setters ============================================================================================

    public ISMSCompanyService getSmsCompanyService() {
        return smsCompanyService;
    }

    public void setSmsCompanyService(ISMSCompanyService smsCompanyService) {
        this.smsCompanyService = smsCompanyService;
    }

}
