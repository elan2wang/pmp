/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:09:17
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IResourceDAO;
import org.pmp.service.admin.IResourceService;
import org.pmp.vo.TbResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ResourceService implements IResourceService {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ResourceService.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IResourceDAO resourceDAO;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void batchAdd(List<TbResource> list){
	resourceDAO.batchSave(list);
    }
    
    public void addResource(TbResource instance){
	resourceDAO.saveResource(instance);
    }
    
    public void editResource(TbResource instance){
	resourceDAO.updateResource(instance);
    }
    
    public void deleteResource(Integer resID){
	resourceDAO.deleteResource(resID);
    }
    
    public TbResource getResourceByID(Integer resID){
	return resourceDAO.getResourceByID(resID);
    }
    
    public List getAllResources(){
	return resourceDAO.getAllResources();
    }
    
    public List getResourceList(){
	return resourceDAO.getResourceList();
    }
    
    public List getResourcesByAuthority(String authName){
	return resourceDAO.getResourcesByAuthority(authName);
    }
    
    public List getResourceListByAuthority(Integer authID){
	return resourceDAO.getResourceListByAuthority(authID);
    }

    public List getNoneGrantedResourceListByAuthoriy(Integer authID){
	return resourceDAO.getNoneGrantedResourceListByAuthoriy(authID);
    }
    //~ Getters and Setters ============================================================================================
    public IResourceDAO getResourceDAO() {
        return resourceDAO;
    }

    public void setResourceDAO(IResourceDAO resourceDAO) {
        this.resourceDAO = resourceDAO;
    }
}
