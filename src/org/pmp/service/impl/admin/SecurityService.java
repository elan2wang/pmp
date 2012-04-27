/**
 * Author            : Elan
 * Created On        : 2012-3-27 上午11:48:28
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IAuthorityDAO;
import org.pmp.dao.admin.IResourceDAO;
import org.pmp.service.admin.ISecurityService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SecurityService implements ISecurityService {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SecurityService.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IResourceDAO resourceDAO;
    private IAuthorityDAO authorityDAO;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public List getAllAuthorities() {
	return authorityDAO.getAllAuthorities();
    }

    public List getResourcesByAuthority(String authName) {
	return resourceDAO.getResourcesByAuthority(authName);
    }
    
    public List loadUserAuthoritiesByUsername(String username){
	try {
	    List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
	    List<String> query1 = authorityDAO.loadUserAuthoritiesByUsername(username);
	    for (String authName : query1) {
		GrantedAuthorityImpl authority = new GrantedAuthorityImpl(authName);
		auths.add(authority);
            }
	    return auths;
	} catch (RuntimeException re) {
            logger.error("load user authorities by account failed.", re);
            throw re;
	}
    }
    //~ Getters and Setters ============================================================================================

    public IResourceDAO getResourceDAO() {
        return resourceDAO;
    }

    public void setResourceDAO(IResourceDAO resourceDAO) {
        this.resourceDAO = resourceDAO;
    }

    public IAuthorityDAO getAuthorityDAO() {
        return authorityDAO;
    }

    public void setAuthorityDAO(IAuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

}
