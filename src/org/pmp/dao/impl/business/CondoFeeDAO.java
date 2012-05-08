/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:07:44
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ICondoFeeDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
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
    
    
    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByIds(java.util.List)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByIds(List<Integer> ids) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list whose ID is in the list";
	String hql = "from CondoFee where cfId in (:ids)";
	try {
	    list = (List<CondoFee>) loadList(hql,ids,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }
    
    
    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByOwner(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByOwner(Integer ownerId,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by owner, ownerId="+ownerId;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where owner.ownerId ="+ownerId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by cfId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<CondoFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByCFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByCFI(Integer cfiId,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by condoFeeItem, cfiId="+cfiId;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where condoFeeItem.cfiId ="+cfiId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by cfId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<CondoFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByYear(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByYear(Integer year,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by year, year="+year;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where cfYear="+year);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by cfId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<CondoFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByMonth(java.lang.Integer, java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByMonth(Integer year, Integer month,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by year and month, year="+year+" month="+month;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where cfYear="+year+" and cfMonth="+month);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by cfId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<CondoFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    
}
