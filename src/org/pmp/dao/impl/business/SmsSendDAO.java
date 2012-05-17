/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午04:37:32
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ISmsSendDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.SMSSend;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsSendDAO extends BaseDAO implements ISmsSendDAO{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(SmsSendDAO.class.getName());
    
    //~ Methods ========================================================================================================
    
    /**
     * @see org.pmp.dao.business.ISmsSendDAO#saveSmsSend(org.pmp.vo.SMSSend)
     */
    @Override
    public void saveSmsSend(SMSSend instance){
	String debugMsg = "save SmsSend instance";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    
	}
    }

    /**
     * @see org.pmp.dao.business.ISmsSendDAO#loadSmsSend_ByCompany(java.lang.Integer, org.pmp.util.Pager, java.util.Map, java.lang.String)
     */
    @Override
    public List<?> loadSmsSend_ByCompany(Integer comId, Pager pager,
	    Map<String, Object> params, String order) {
	List<SMSSend> list = null;
	String debugMsg = "load SmsSend list by Company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	hql.append("from SMSSend where SMSCompany.company.comId="+comId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by smssId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<SMSSend>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.ISmsSendDAO#loadSmsSend_ByPerson(java.lang.String, org.pmp.util.Pager, java.util.Map, java.lang.String)
     */
    @Override
    public List<?> loadSmsSend_ByPerson(String name, Pager pager,
	    Map<String, Object> params, String order) {
	List<SMSSend> list = null;
	String debugMsg = "load SmsSend list by person, username="+name;
	StringBuilder hql = new StringBuilder();
	hql.append("from SMSSend where smssPerson='"+name+"'");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by smssId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<SMSSend>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.ISmsSendDAO#loadAllSmsSend(org.pmp.util.Pager, java.util.Map, java.lang.String)
     */
    @Override
    public List<?> loadAllSmsSend(Pager pager, Map<String, Object> params,
	    String order) {
	List<SMSSend> list = null;
	String debugMsg = "load all SmsSend list";
	StringBuilder hql = new StringBuilder();
	hql.append("from SMSSend");
	String condition = ParamsToString.toString(params);
	if (!condition.equals("")){
	    hql.append(" where ");
	    hql.append(condition.subSequence(5, condition.length()));
	}
	logger.debug(hql);
	
	if (order==null){
	    hql.append(" order by smssId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<SMSSend>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

}
