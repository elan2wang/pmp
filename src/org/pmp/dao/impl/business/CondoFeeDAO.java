/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:07:44
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
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

    //~ Methods ========================================================================================================
    public void generateCondoFee(final Integer cfiId){
	logger.debug("begin to generate condoFee with cfiId="+cfiId);
	Work work = new Work(){
            public void execute(Connection connection)throws SQLException{
                String procedure = "{call cf_generate(?) }";
                CallableStatement cstmt = connection.prepareCall(procedure);
                cstmt.setInt(1, cfiId);
                cstmt.executeUpdate();
            }
        };
        executeWork(work);
        logger.debug("successfully generate condoFee with cfiId="+cfiId);
    }
    
    public void batchSetOughtMoney(final List<CondoFee> list){
	logger.debug("begin to batch set oughtMoney");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "update tb_CondoFee set Ought_Money=? where CF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setDouble(1, list.get(i).getOughtMoney());
		    stmt.setInt(2, list.get(i).getCfId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
        logger.debug("successfully batch set oughtMoney");
    }
    
    public void batchInput(final List<CondoFee> list){
	logger.debug("begin to batch input");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "update tb_CondoFee set Fetch_Money=?,Record_Person=?,Input_Time=?,State=? where CF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setDouble(1, list.get(i).getOughtMoney());
		    stmt.setString(2, list.get(i).getRecordPerson());
		    java.sql.Date inputTime=new java.sql.Date(list.get(i).getInputTime().getTime());
		    stmt.setDate(3, inputTime);
		    stmt.setString(4, list.get(i).getState());
		    stmt.setInt(5, list.get(i).getCfId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch input");
    }
    
    public void batchAudit(final List<CondoFee> list){
	logger.debug("begin to batch audit");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "update tb_CondoFee set Audit_person=?,Audit_Time=?,State=? where CF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setString(1, list.get(i).getAuditPerson());
		    java.sql.Date auditTime=new java.sql.Date(list.get(i).getAuditTime().getTime());
		    stmt.setDate(2, auditTime);
		    stmt.setString(3, list.get(i).getState());
		    stmt.setInt(4, list.get(i).getCfId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch audit");
    }
    
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
    public List<CondoFee> loadCondoFeeList_ByHouse(Integer houseId,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by owner, ownerId="+houseId;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where house.houseId ="+houseId);
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
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where house in (from House where building in " +
		   "(from Building where project in (from Project where company.comId="+comId+")))");
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
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<CondoFee> loadCondoFeeList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	List<CondoFee> list = null;
	String debugMsg = "load CondoFee list by project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	hql.append("from CondoFee where house in (from House where building in " +
		   "(from Building where project.proId="+proId+"))");
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
