/**
 * Author            : Elan
 * Created On        : 2012-5-30 下午11:23:25
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class NotCacheFilter implements Filter {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(NotCacheFilter.class.getName());

    public void init(FilterConfig arg0) throws ServletException {}
    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain filterChain) throws IOException, ServletException {
	HttpServletResponse res = (HttpServletResponse)response;
	res.setHeader("Pragma","No-cache"); 
	res.setHeader("Cache-Control","no-cache"); 
	res.setDateHeader("Expires", 0);
	logger.debug("no cache filter");
	filterChain.doFilter(request, response);
    }


}
