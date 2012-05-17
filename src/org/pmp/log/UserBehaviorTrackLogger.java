/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午08:45:17
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.pmp.util.SessionHandler;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserBehaviorTrackLogger {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(UserBehaviorTrackLogger.class.getName());
    //~ Instance Fields ================================================================================================
    
    //~ Methods ========================================================================================================
    public void loginTrack(JoinPoint jp) throws Exception{
	StringBuilder msg = new StringBuilder();
	msg.append("["+SessionHandler.getUser().getRealname()+"]: ");
	msg.append("login in successfully");
	logger.info(msg.toString());
    }
    
    public void logoutTrack(JoinPoint jp) throws Exception{
	StringBuilder msg = new StringBuilder();
	msg.append("["+SessionHandler.getUser().getRealname()+"]: ");
	msg.append("login out");
	logger.info(msg.toString());
    }
    
    public void addBehaviorTrack(JoinPoint jp) throws Exception{
        StringBuilder msg = new StringBuilder();
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    msg.append("["+username+"]: add a "+obj[0].getClass().getSimpleName()+" instance. Instance details:");
	    msg.append(obj[0].toString());
	}
        logger.info(msg.toString());
    }
    
    public void editBehaviorTrack(JoinPoint jp) throws Exception{
	StringBuilder msg = new StringBuilder();
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    msg.append("["+username+"]: modify the "+obj[0].getClass().getSimpleName()+" instance. after modified:");
	    msg.append(obj[0].toString());
	}
        logger.info(msg.toString());
    }
    
    public void deleteBehaviorTrack(JoinPoint jp) throws Exception{
	StringBuilder msg = new StringBuilder();
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    String signature = jp.getSignature().toString();
	    Object[] obj = jp.getArgs();
	    msg.append("["+username+"]: "+signature.split(" ")[1]+" ID="+obj[0]);
	}
        logger.info(msg.toString());
    }
    
    //~ Getters and Setters ============================================================================================

}