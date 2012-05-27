package org.pmp.security;

import java.util.Collection;
import java.util.Iterator;

import org.pmp.vo.TbUser;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager implements AccessDecisionManager {
    
    //~ Methods ========================================================================================================
    public void decide(Authentication arg0, Object arg1,
	    Collection<ConfigAttribute> arg2) throws AccessDeniedException,
	    InsufficientAuthenticationException {
	
	/* arg2==null 表示被访问资源是公共资源 */
	if(arg2 == null)return;
	
	/* 遍历被访问资源所需的权限集合 */
	Iterator<ConfigAttribute> ite = arg2.iterator();
	while(ite.hasNext()){
	    ConfigAttribute ca = ite.next();
	    String needRole = ((SecurityConfig)ca).getAttribute();
	    /* 遍历用户的权限集合，若用户拥有访问资源所需要的资源，则允许访问 */
	    for(GrantedAuthority ga : arg0.getAuthorities()){
		if(needRole.trim().equals(ga.getAuthority().trim()))return;
	    }
	}
	
	/* 如果用户不含有被访问资源所需要的权限，则抛出访问拒绝的异常 */
	String username = ((TbUser)arg0.getPrincipal()).getUsername();
	throw new AccessDeniedException("Dear User:"+username+
		",I'm sorry that you don't have the needed authorities");
    }
    
    public boolean supports(ConfigAttribute arg0) {
	return true;
    }
    
    public boolean supports(Class<?> arg0) {
	return true;
    }
}
