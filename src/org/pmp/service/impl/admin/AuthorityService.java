/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午02:09:36
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.pmp.dao.admin.IAuthorityDAO;
import org.pmp.service.admin.IAuthorityService;
import org.pmp.vo.TbAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class AuthorityService implements IAuthorityService {

    //~ Static Fields ==================================================================================================
    
    //~ Instance Fields ================================================================================================
    private IAuthorityDAO authorityDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addResource(TbAuthority instance){
	authorityDAO.saveAuthority(instance);
    }
    public void editResource(TbAuthority instance){
	authorityDAO.updateAuthority(instance);
    }
    public void deleteResource(Integer authID){
	authorityDAO.deleteAuthority(authID);
    }

    public TbAuthority getAuthorityByID(Integer authID){
	return authorityDAO.getAuthorityByID(authID);
    }
    
    public List getAuthoritiesByRoleID(Integer roleID){
	return authorityDAO.getAuthoritiesByRoleID(roleID);
    }
    
    public List getNoneGrantedAuthByRoleID(Integer roleID){
	return authorityDAO.getNoneGrantedAuthByRoleID(roleID);
    }
    
    //~ Getters and Setters ============================================================================================
    public IAuthorityDAO getAuthorityDAO() {
        return authorityDAO;
    }
    public void setAuthorityDAO(IAuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    public List getAllAuthorities() {
	return this.authorityDAO.getAllAuthorities();
    }

    public List getAuthorityList() {
	return this.authorityDAO.getAuthorityList();
    }
    
}
