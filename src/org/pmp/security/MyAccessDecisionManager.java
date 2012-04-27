package org.pmp.security;

import java.util.Collection;
import java.util.Iterator;

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
		if(arg2 == null){
			return;
		}
		Iterator<ConfigAttribute> ite = arg2.iterator();
		while(ite.hasNext()){
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			for(GrantedAuthority ga : arg0.getAuthorities()){
				if(needRole.trim().equals(ga.getAuthority().trim())){
					return;
				}
			}
			
		}
		throw new AccessDeniedException("");	 		  
	}

	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}
