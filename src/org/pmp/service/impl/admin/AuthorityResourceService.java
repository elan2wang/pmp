/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午05:40:40
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.pmp.dao.admin.IAuthorityResourceDAO;
import org.pmp.service.admin.IAuthorityResourceService;
import org.pmp.vo.TbAuthorityResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class AuthorityResourceService implements IAuthorityResourceService{

    //~ Static Fields ==================================================================================================
     
    //~ Instance Fields ================================================================================================
    private IAuthorityResourceDAO authorityResourceDAO;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void batchAdd(List<TbAuthorityResource> list){
	authorityResourceDAO.batchSave(list);
    }
    public void batchDeleteByAuthID(Integer authID){
	authorityResourceDAO.batchDeleteByAuthID(authID);
    }
    public void batchEditByAuthID(List<TbAuthorityResource> list,Integer authID){
	authorityResourceDAO.batchUpdateByAuthID(authID, list);
    }
    //~ Getters and Setters ============================================================================================
    public IAuthorityResourceDAO getAuthorityResourceDAO() {
        return authorityResourceDAO;
    }
    public void setAuthorityResourceDAO(IAuthorityResourceDAO authorityResourceDAO) {
        this.authorityResourceDAO = authorityResourceDAO;
    }
}
