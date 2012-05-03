/**
 * Author            : Elan
 * Created On        : 2012-5-3 下午12:36:08
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.security;

import org.apache.log4j.Logger;
import org.pmp.util.SpringContextUtil;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SecuritySupport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SecuritySupport.class.getName());
    
    //~ Methods ========================================================================================================
    public static void reloadSecurityMetadataSource(){
	MyFilterSecurityInterceptor filterSecurityInterceptor = 
	    (MyFilterSecurityInterceptor)SpringContextUtil.getBean("myFilter");
	MyInvocationSecurityMetadataSource oldSource =
	    (MyInvocationSecurityMetadataSource)SpringContextUtil.getBean("mySecurityMetadataSource");
	
	MyInvocationSecurityMetadataSource newSource = oldSource.getObject();
	filterSecurityInterceptor.setSecurityMetadataSource(newSource);
	logger.debug("InvocationSecurityMetadataSource reload successfully");
    }
    //~ Getters and Setters ============================================================================================

}
