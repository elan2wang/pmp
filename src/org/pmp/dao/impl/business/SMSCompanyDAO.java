/**
 * Author            : Elan
 * Created On        : 2012-4-17 下午12:01:10
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ISMSCompanyDAO;
import org.pmp.util.Pager;
import org.pmp.vo.SMSCompany;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SMSCompanyDAO extends BaseDAO implements ISMSCompanyDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SMSCompanyDAO.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void saveSMSCompany(SMSCompany instance){
	String debugMsg = "save SMSCompany instance";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void updateSMSCompany(SMSCompany instance){
	String debugMsg = "update SMSCompany instance,smscId="+instance.getSmscId();
	try {
	    updateInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void delereSMSCompany(Integer smscId){
	String debugMsg = "delete SMSCompany instance,smscId="+smscId;
	String hql = "delete from SMSCompany where smscId="+smscId;
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public SMSCompany getSMSCompanyByID(Integer smscId){
	String debugMsg = "get SMSCompany instance by ID,smscId="+smscId;
	String hql = "from SMSCompany smsc where smsc.smscId="+smscId;
	SMSCompany smsc = null;
	try {
	    smsc = (SMSCompany)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return smsc;
    }
    
    public SMSCompany getSMSCompanyByComID(Integer comId){
	String debugMsg = "get SMSCompany instance by company ID,comId="+comId;
	String hql = "from SMSCompany smsc where smsc.company.comId="+comId;
	SMSCompany smsc = null;
	try {
	    smsc = (SMSCompany)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return smsc;
    }
    
    public List loadSMSCompanyList(Pager pager){
	String debugMsg = "load SMSCompany list";
	String hql = "from SMSCompany order by smscId desc";
	List smscList = null;
	try {
	    smscList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return smscList;
    }
    //~ Getters and Setters ============================================================================================

}
