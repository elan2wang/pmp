/**
 * Author            : Elan
 * Created On        : 2012-3-30 下午12:08:47
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IUserGroupRoleDAO;
import org.pmp.service.admin.IUserGroupRoleService;
import org.pmp.util.Pager;
import org.pmp.vo.TbUserGroupRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserGroupRoleService implements IUserGroupRoleService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(UserGroupRoleService.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IUserGroupRoleDAO ugrDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addUGR(TbUserGroupRole instance){
	ugrDAO.saveUGR(instance);
    }
    
    public void editUGR(TbUserGroupRole instance){
	ugrDAO.updateUGR(instance);
    }

    public TbUserGroupRole getUGR_ByUserID(Integer userID){
	return ugrDAO.getUGR_ByUserID(userID);
    }
    
    /**
     * @see org.pmp.service.admin.IUserGroupRoleService#loadUGRList(java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadUGRList(Map<String, Object> params, String order,
	    Pager pager) {
	return ugrDAO.loadUGRList(params, order, pager);
    }
    /**
     * @see org.pmp.service.admin.IUserGroupRoleService#loadUGRList_ByPro(java.lang.String, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadUGRList_ByPro(String proName,
	    Map<String, Object> params, String order, Pager pager) {
	return ugrDAO.loadUGRList_ByPro(proName, params, order, pager);
    }
    /**
     * @see org.pmp.service.admin.IUserGroupRoleService#loadUGRList_ByCom(java.lang.String, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadUGRList_ByCom(String comName,
	    Map<String, Object> params, String order, Pager pager) {
	return ugrDAO.loadUGRList_ByCom(comName, params, order, pager);
    }

    //~ Getters and Setters ============================================================================================
    public IUserGroupRoleDAO getUgrDAO() {
        return ugrDAO;
    }
    public void setUgrDAO(IUserGroupRoleDAO ugrDAO) {
        this.ugrDAO = ugrDAO;
    }
    
}
