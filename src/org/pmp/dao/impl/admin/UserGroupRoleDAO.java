/**
 * Author            : Elan
 * Created On        : 2012-3-30 上午10:49:29
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IUserGroupRoleDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.TbUserGroupRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserGroupRoleDAO extends BaseDAO implements IUserGroupRoleDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(UserGroupRoleDAO.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================
   
    //~ Methods ========================================================================================================
    public void saveUGR(TbUserGroupRole instance){
	String debugMsg = "SaveUGR";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public void updateUGR(TbUserGroupRole instance){
	String debugMsg = "updateUGR userID="+instance.getTbUser().getUserId();
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public TbUserGroupRole getUGR_ByUserID(Integer userID){
	String debugMsg = "getUGR_ByUserID userID="+userID.toString();
	String hql = "from TbUserGroupRole ugr where ugr.tbUser.userId = "+userID.toString();
	TbUserGroupRole ugr = null;
	try {
	    ugr = (TbUserGroupRole)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return ugr;
    }
    
    /**
     * @see org.pmp.dao.admin.IUserGroupRoleDAO#loadUGRList(java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    public List<?> loadUGRList(Map<String, Object> params, String order,
	    Pager pager) {
	List<?> list = null;
	String debugMsg = "load User list";
	StringBuilder hql = new StringBuilder();
	hql.append("from TbUserGroupRole");
	if(params.size()!=0){
	    hql.append(" where ");
	    hql.append(ParamsToString.toString(params).substring(4));
	}
	else {
	    hql.append("");
	}
	if(order==null){
	    hql.append(" order by tbGroup.groupLevel asc");
	} else {
	    hql.append(" "+order);
	}
	try {
	    list = loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }

    /**
     * @see org.pmp.dao.admin.IUserGroupRoleDAO#loadUGRList_ByCom(java.lang.String, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    public List<?> loadUGRList_ByCom(String comName,Map<String,Object> params,String order,Pager pager){
	List<?> list = null;
	String debugMsg = "load User list by company, comName="+comName;
	StringBuilder hql = new StringBuilder();
	hql.append("from TbUserGroupRole where tbGroup.groupId in " +
		   "(select groupId from TbGroup where refDomain='"+comName+"' or fatherGroupId=" +
		   "(select groupId from TbGroup where refDomain='"+comName+"'))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by tbGroup.groupLevel asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
   
    /**
     * @see org.pmp.dao.admin.IUserGroupRoleDAO#loadUGRList_ByPro(java.lang.String, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    public List<?> loadUGRList_ByPro(String proName,
	    Map<String, Object> params, String order, Pager pager) {
	List<?> list = null;
	String debugMsg = "load User list by project, proName="+proName;
	StringBuilder hql = new StringBuilder();
	hql.append("from TbUserGroupRole where tbGroup.groupId in " +
		   "(select groupId from TbGroup where refDomain='"+proName+"')");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by tbGroup.groupLevel asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }

}
