/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:07:44
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ICondoFeeDAO;
import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeDAO extends BaseDAO implements ICondoFeeDAO {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CondoFeeDAO.class.getName());

    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public CondoFee getCondoFeeByID(Integer cfId){
	String debugMsg = "get CondoFee by Id ,cfId="+cfId;
	String hql = "from CondoFee where cfId="+cfId;
	CondoFee instance = null;
	try {
	    instance = (CondoFee)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return instance;
    }
    
    public void updateCondoFee(CondoFee instance){
	String debugMsg = "update condoFee, cfId="+instance.getCfId();
	try{
	    updateInstance(instance, debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void deleteCondoFee(Integer cfId){
	String debugMsg = "delete condoFee, cfId="+cfId;
	String hql = "delete CondoFee where cfId="+cfId;
	try {
	    deleteInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public List loadCondoFeeListBy_cfiId(Integer cfiId,Pager pager) {
	String debugMsg = "load condoFee list by CondoFeeItem ID,cfiId="+cfiId;
	String hql = "select cf from CondoFee cf where cf.condoFeeItem.cfiId="+cfiId;
	List condoFeeList = null;
	try {
	    condoFeeList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return condoFeeList;
    }

    public List loadPayedCondoFeeList(Integer cfiId,Pager pager){
	String debugMsg = "load Payed condoFee list by CondoFeeItem ID,cfiId="+cfiId;
	String hql = "select cf from CondoFee cf where cf.condoFeeItem.cfiId="+cfiId+" and cf.state='已缴费'";
	List condoFeeList = null;
	try {
	    condoFeeList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return condoFeeList;
    }
    
    public List loadNonePayedCondoFeeList(Integer cfiId,Pager pager){
	String debugMsg = "load none Payed condoFee list by CondoFeeItem ID,cfiId="+cfiId;
	String hql = "select cf from CondoFee cf where cf.condoFeeItem.cfiId="+cfiId+" and cf.state='新生成'";
	List condoFeeList = null;
	try {
	    condoFeeList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return condoFeeList;
    }
}
