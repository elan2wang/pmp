/**
 * Author            : Elan
 * Created On        : 2012-5-2 下午05:55:24
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ExceptionHandleFilter implements Filter{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ExceptionHandleFilter.class
	    .getName());

    //~ Methods ========================================================================================================
    public void init(FilterConfig arg0) throws ServletException {}
    public void destroy() {}
    
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	try {
	    chain.doFilter(request, response);
            logger.debug("Chain processed normally");
	} catch (Exception e){
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/exception.jsp");
            dispatcher.forward(request, response);
	}
	
    }
    
    //~ Getters and Setters ============================================================================================

}
