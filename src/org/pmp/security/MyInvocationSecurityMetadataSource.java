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
     * 该方法在构造函数中调用，系统初始化时就被执行，创建Map对象
     * 把系统的所有资源作为key，该资源所能访问的权限集合作为value
     */
    private void loadDefinedResource(){
	/* 从数据库获取所有的权限列表 */
        List<String> allAuthorityNames = securityService.getAllAuthorities();
        /* key of the resourceMap is Resource,value is Authority */
        resourceMap = new HashMap<String,Collection<ConfigAttribute>>();
        /* 遍历权限列表 */
        for (String auth : allAuthorityNames){
            ConfigAttribute ca = new SecurityConfig(auth);
            /* 获取权限auth能够访问的资源列表 */
            List<String> resourceList = 
        	securityService.getResourcesByAuthority(auth);
            /* 遍历auth拥有的资源列表 */
            /* 若resourceMap中含有该资源，则将auth添加到该资源的权限集合中 */
            /* 若不含，则为该资源创建权限集合对象，并将该资源及其权限集合添加到该权限集合中 */
            for(String res : resourceList){
                String url = res;
                if(resourceMap.containsKey(url)){
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> attributes = 
                	new ArrayList<ConfigAttribute>();
                    attributes.add(ca);
                    resourceMap.put(url, attributes);
                }
            }
        }
    }
	    
    /**
     * 获取能够访问该资源的权限集合
     */
    public Collection<ConfigAttribute> getAttributes(Object arg0)
            throws IllegalArgumentException {
	/* 从FilterInvocation对象中获取请求的URL */ 
	String url = ((FilterInvocation) arg0).getRequestUrl();
	/* 省略URL中的参数  */
	int firstQuestionMarkIndex = url.indexOf("?");
	if (-1 != firstQuestionMarkIndex){
	    url = url.substring(0, firstQuestionMarkIndex);
	}
	/* 遍历resourceMap的key，若与请求URL匹配 */
	/* 则返回该key对应的value，若没有匹配的key，则返回null */
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
