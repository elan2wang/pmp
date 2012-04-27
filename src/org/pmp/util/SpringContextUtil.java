/**
 * Author            : Elan
 * Created On        : 2012-4-18 下午10:08:22
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SpringContextUtil implements ApplicationContextAware {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SpringContextUtil.class.getName());
    private static ApplicationContext applicationContext;

    //~ Methods ========================================================================================================
    public static Object getBean(String name) throws BeansException{
	return applicationContext.getBean(name);
    }
    
    public static boolean containsBean(String name) {
	return applicationContext.containsBean(name);
    }
    
    //~ Getters and Setters ============================================================================================
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
	SpringContextUtil.applicationContext = arg0;

    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
