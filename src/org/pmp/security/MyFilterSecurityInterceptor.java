package org.pmp.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {
    static Logger logger = Logger.getLogger(MyFilterSecurityInterceptor.class.getName ());
	
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    public void init(FilterConfig arg0) throws ServletException {}
    public void destroy() {}
    
    public Class<? extends Object> getSecureObjectClass() {
	return FilterInvocation.class;
    }
    
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
	FilterChain arg2) throws IOException, ServletException {
	logger.debug("doFilter");
	FilterInvocation fi = new FilterInvocation( arg0, arg1, arg2 );
	invoke(fi);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
	InterceptorStatusToken token = super.beforeInvocation(fi);
	if(this.obtainSecurityMetadataSource()==null){
	    logger.debug("securityMetadataSource为空对象");
	}
	else{
	    logger.debug(fi.toString());
	    logger.debug(this.obtainSecurityMetadataSource().getAttributes(fi));
	}
	
	try {
	    fi.getChain().doFilter(fi.getHttpRequest(), fi.getHttpResponse());
	} finally{
	    super.afterInvocation(token, null);
	}
    }
    
    public SecurityMetadataSource obtainSecurityMetadataSource() {
	return this.securityMetadataSource;
    }
	
    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource 
	    securityMetadataSource){
	this.securityMetadataSource = securityMetadataSource;
    }

}
