/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午10:51:28
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IFixedParkFeeDAO;
import org.pmp.util.Pager;
import org.pmp.vo.FixedParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class FixedParkFeeDAO extends BaseDAO implements IFixedParkFeeDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(FixedParkFeeDAO.class.getName());

    //~ Methods ========================================================================================================
    public FixedParkFee getFixedParkFeeByID(Integer fpfId){
	String debugMsg = "get FixedParkFee by ID,fpfId="+fpfId;
	String hql = "from FixedParkFee where fpfId="+fpfId;
	FixedParkFee item = null;
	try {
	    item = (FixedParkFee)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return item;
    }
    public void updateFixedParkFee(FixedParkFee instance){
	String debugMsg = "update FixedParkFee,fpfId="+instance.getFpfId();
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    public void deleteFixedParkFee(Integer fpfId)
    {
	String debugMsg = "delete FixedParkFee,fpfId="+fpfId;
	String hql = "delete from FixedParkFee where fpfId="+fpfId;
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public List loadFixedParkFeeListBy_fpfiId(Pager pager,Integer fpfiId){
	String debugMsg = "get FixedParkFee List by fpfiId="+fpfiId;
	String hql = "from FixedParkFee fpf where fpf.fixedParkFeeItem.fpfiId="+fpfiId;
	List list = null;
	try {
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
    //~ Getters and Setters ============================================================================================

}
