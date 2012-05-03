/**
 * Author            : Elan Wang
 * Created On        : 2012/2/16
 * 
 * Copyright by Elan Wang.  All rights reserved. 
 * 
 */
package org.pmp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.service.admin.ISecurityService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;



/**
 * @author Elan
 * @version 1.0
 * @see org.springframework.security.access.SecurityMetadataSource
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

        //~ Static fields ==================================================================================================	
        static Logger logger = Logger.getLogger(MyInvocationSecurityMetadataSource.class.getName ());
	private static Map<String,Collection<ConfigAttribute>> resourceMap = null;
       
        //~ Instance fields ================================================================================================
        private ISecurityService securityService;
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	//~ Constructors ===================================================================================================

	/**
	 * default constructor
	 */
	public MyInvocationSecurityMetadataSource(ISecurityService securityService){
		this.securityService = securityService;
		loadDefinedResource();
	}
	
	//~ Methods ========================================================================================================

	/**
	 * invoke when the resource and authority mapping changed
	 */
	public MyInvocationSecurityMetadataSource getObject(){
            return new MyInvocationSecurityMetadataSource(this.securityService);
	}
	
	/**
	 * This method is invoked in default constructor
	 * This method would instantiate resourceMap and set its value
	 */
	private void loadDefinedResource(){
		//get all authority names
		List<String> allAuthorityNames = securityService.getAllAuthorities();
		//instantiate resourceMap
		//the key of the resourceMap is Resource,value is Authority
		resourceMap = new HashMap<String,Collection<ConfigAttribute>>();
		
		//Traverse all the authority
		for (String auth : allAuthorityNames){
			ConfigAttribute ca = new SecurityConfig(auth);
			
			//Get all the resources that can be accessed by auth
			List<String> resourceList = securityService.getResourcesByAuthority(auth);
			//Traverse all the resources of auth
			for(String res : resourceList){
				String url = res;
				if(resourceMap.containsKey(url)){
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				}
				else{
					Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
					attributes.add(ca);
					resourceMap.put(url, attributes);
				}
			}
		}
	}
	
	    
	/**
	 * this method return Authority Collection according to the Resource
	 */
	public Collection<ConfigAttribute> getAttributes(Object arg0)
			throws IllegalArgumentException {
		//the parameter arg0 is a URL which user request 
		String url = ((FilterInvocation) arg0).getRequestUrl();
		
		logger.debug("url="+url);
		//omit the parameters of the requested URL
		int firstQuestionMarkIndex = url.indexOf("?");
		if (-1 != firstQuestionMarkIndex)
		{
			url = url.substring(0, firstQuestionMarkIndex);
		}
		logger.debug("url="+url);
		
		//Traverse the resourceMap and find the matched URL
		//if find then return the Collection<ConfigAttribute>
		//otherwise return null
		Iterator<String> ite = resourceMap.keySet().iterator();
		while(ite.hasNext()){
			String resUrl = ite.next();
			if(urlMatcher.pathMatchesUrl(url, resUrl)){
				return resourceMap.get(resUrl);
			}
		}
		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
