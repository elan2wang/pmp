/**
 * Author            : Elan
 * Created On        : 2012-4-20 下午01:47:46
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import org.apache.log4j.Logger;
import org.pmp.util.SessionHandler;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class LoginAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(LoginAction.class.getName());
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String loadWelcomePage(){
	SessionHandler.putUserIntoSession();
	return SUCCESS;
    }
    
}
