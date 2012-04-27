/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午10:18:24
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IFixedParkFeeItemDAO;
import org.pmp.util.Pager;
import org.pmp.vo.FixedParkFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class FixedParkFeeItemDAO extends BaseDAO implements
	IFixedParkFeeItemDAO {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger
	    .getLogger(FixedParkFeeItemDAO.class.getName());

    //~ Methods ========================================================================================================

    public void saveFixedParkFeeItem(FixedParkFeeItem instance) {
	String debugMsg = "save FixedParkFeeItem";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    public void updateFixedParkFeeItem(FixedParkFeeItem instance) {
	String debugMsg = "update FixedParkFeeItem ,fpfiId="+instance.getFpfiId();
	try {
	    updateInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    public void deleteFixedParkFeeItem(Integer fpfiId) {
	String debugMsg = "delete FixedParkFeeItem,fpfiId="+fpfiId;
	String hql = "delete from FixedParkFeeItem where fpfiId="+fpfiId;
	try {
	    deleteInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    public FixedParkFeeItem getFixedParkFeeItemByID(Integer fpfiId) {
	String debugMsg = "get FixedParkFeeItem by Id,fpfiId="+fpfiId;
	String hql = "from FixedParkFeeItem where fpfiId="+fpfiId;
	FixedParkFeeItem item = null;
	try {
	    item = (FixedParkFeeItem)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return item;
    }

    public List loadFixedParkFeeItemList_ByPro(Pager pager,Integer proId) {
	String debugMsg = "load FixedParkFeeItem list by Project,proId="+proId;
	String hql = "from FixedParkFeeItem fpfi where fpfi.project.proId="+proId;
	List list = null;
	try {
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }
    
    public List loadFixedParkFeeItemList_ByCom(Pager pager,Integer comId){
	String debugMsg = "load FixedParkFeeItem list by Company,comId="+comId;
	String hql = "from FixedParkFeeItem fpfi where fpfi.project.proId in " +
	             "(select pro.proId from Project pro where pro.company.comId="+comId+")" +
	             "order by fpfi.fpfiId desc";
	List list = null;
	try {
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }
    //~ Getters and Setters ============================================================================================

}
