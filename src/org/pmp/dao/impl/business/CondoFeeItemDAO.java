/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午05:37:25
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.util.Pager;
import org.pmp.vo.CondoFeeItem;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ICondoFeeItemDAO;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeItemDAO extends BaseDAO implements ICondoFeeItemDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CondoFeeItemDAO.class.getName());

    //~ Instance Fields ================================================================================================
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void saveCondoFeeItem(CondoFeeItem instance) {
	String debugMsg = "save condoFeeItem";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    public void deleteCondoFeeItem(CondoFeeItem instance){
	String debugMsg = "delete condoFeeItem,cfiId="+instance.getCfiId();
	String hql = "delete from CondoFeeItem where cfiId="+instance.getCfiId();
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public List<?> getCondoFeeYear(){
	String debugMsg = "get all years from CondoFee";
	String hql = "select distinct itemYear from CondoFeeItem order by itemYear desc";
        Pager pager = new Pager(100,1);
	List<?> list = null;
	try {
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    logger.error("failed to get all years from CondoFee");
	    throw e;
	}
	return list;
    }
    
    public CondoFeeItem getCondoFeeItemByID(Integer cfiId){
	String debugMsg = "get condoFeeItem by ID,cfiId="+cfiId;
	String hql = "from CondoFeeItem where cfiId="+cfiId;
	CondoFeeItem condoFeeItem = null;
	try {
	    condoFeeItem = (CondoFeeItem)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return condoFeeItem;
    }
    
    public List<?> loadCondoFeeItemListBy_ComID(Pager pager,Integer comId) {
	String debugMsg = "load condoFeeItem list";
	String hql = "select cfi from CondoFeeItem cfi where cfi.project.proId in" +
	             "(select pro.proId from Project pro where pro.company.comId="+comId+")" +
	             "order by cfi.cfiId desc";
	List<?> cfiList = null;
	try {
	    cfiList = loadListByCondition(hql, pager, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return cfiList;
    }
    
    public List<?> loadCondoFeeItemListBy_ProID(Pager pager,Integer proId) {
	String debugMsg = "load condoFeeItem list";
	String hql = "select cfi from CondoFeeItem cfi where cfi.project.proId="+proId+
	             " order by cfi.cfiId desc";
	List<?> cfiList = null;
	try {
	    cfiList = loadListByCondition(hql, pager, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return cfiList;
    }

}
