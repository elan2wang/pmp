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

import java.util.Iterator;
import java.util.List;

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
    
    public void addTrack(JoinPoint jp) throws Exception{
        StringBuilder msg = new StringBuilder();
	if (SessionHandler.getUser()!=null){
	    /* 从session从获取当前用户名 */
	    String username = SessionHandler.getUser().getRealname();
	    /* 获取连接点方法的参数数组 */
	    Object[] obj = jp.getArgs();
	    /* 添加用户名 */
	    msg.append("["+username+"]: ");
	    /* 添加事件类型、对象类型 */
	    msg.append("Add "+obj[0].getClass().getSimpleName()+", ");
	    /* 添加操作对象的详细信息 */
	    msg.append("Details:"+obj[0].toString());
	}
        logger.info(msg.toString());
    }
    
    public void editTrack(JoinPoint jp) throws Exception{
	StringBuilder msg = new StringBuilder();
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    msg.append("["+username+"]: ");
	    /* 添加事件类型、对象类型 */
	    msg.append("Edit "+obj[0].getClass().getSimpleName()+", ");
	    /* 添加操作对象的详细信息 */
	    msg.append("Details:"+obj[0].toString());
	}
        logger.info(msg.toString());
    }
    
    public void deleteTrack(JoinPoint jp) throws Exception{
	StringBuilder msg = new StringBuilder();
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    msg.append("["+username+"]: ");
	    /* 添加事件类型、对象类型 */
	    msg.append("Delete "+obj[0].getClass().getSimpleName()+", ");
	    /* 添加操作对象的详细信息 */
	    msg.append("Details:"+obj[0].toString());
	}
        logger.info(msg.toString());
    }
    
    public void batchAddTrack(JoinPoint jp) throws Exception{
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    List<?> list = (List<?>)obj[0];
	    Iterator<?> ite = list.iterator();
	    while(ite.hasNext()){
		Object o = ite.next();
		StringBuilder msg = new StringBuilder();
		msg.append("["+username+"]: ");
		/* 添加事件类型、对象类型 */
		msg.append("Add "+o.getClass().getSimpleName()+", ");
		/* 添加操作对象的详细信息 */
		msg.append("Details:"+o.toString());
	        logger.info(msg.toString());
	    }
	}
    }
    
    public void batchEditTrack(JoinPoint jp) throws Exception{
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    List<?> list = (List<?>)obj[0];
	    Iterator<?> ite = list.iterator();
	    while(ite.hasNext()){
		Object o = ite.next();
		StringBuilder msg = new StringBuilder();
		msg.append("["+username+"]: ");
		/* 添加事件类型、对象类型 */
		msg.append("Edit "+o.getClass().getSimpleName()+", ");
		/* 添加操作对象的详细信息 */
		msg.append("Details:"+o.toString());
	        logger.info(msg.toString());
	    }
	}
    }
    
    public void batchDeleteTrack(JoinPoint jp) throws Exception{
	logger.debug("batchDeleteTrack");
	if (SessionHandler.getUser()!=null){
	    String username = SessionHandler.getUser().getRealname();
	    Object[] obj = jp.getArgs();
	    List<?> list = (List<?>)obj[0];
	    Iterator<?> ite = list.iterator();
	    while(ite.hasNext()){
		Object o = ite.next();
		StringBuilder msg = new StringBuilder();
		msg.append("["+username+"]: ");
		/* 添加事件类型、对象类型 */
		msg.append("Delete "+o.getClass().getSimpleName()+", ");
		/* 添加操作对象的详细信息 */
		msg.append("Details:"+o.toString());
	        logger.info(msg.toString());
	    }
	}
    }
    //~ Getters and Setters ============================================================================================

}
