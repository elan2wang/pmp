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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		String sql = "update tb_CondoFee set Ought_Money=?,State=? where CF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setDouble(1, list.get(i).getOughtMoney());
		    stmt.setString(2, list.get(i).getState());
		    stmt.setInt(3, list.get(i).getCfId());
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
		String sql = "update tb_CondoFee set Fetch_Money=?,Record_Person=?,Input_Time=?,State=?,Comment=? where CF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setDouble(1, list.get(i).getOughtMoney());
		    stmt.setString(2, list.get(i).getRecordPerson());
		    java.sql.Date inputTime=new java.sql.Date(list.get(i).getInputTime().getTime());
		    stmt.setDate(3, inputTime);
		    stmt.setString(4, list.get(i).getState());
		    stmt.setString(5, list.get(i).getComment());
		    stmt.setInt(6, list.get(i).getCfId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch input");
    }
    
    public void batchAudit(final List<CondoFee> list){
	logger.debug("begin to batch audit CondoFee");
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
	logger.debug("successfully batch audit CondoFee");
    }

    public void batchDelete(final List<CondoFee> list){
	logger.debug("begin to batch delete CondoFee");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_CondoFee where CF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getCfId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete CondoFee");
    }
    
    public CondoFee getCondoFee_ById(Integer cfId){
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
	String debugMsg = "load CondoFee list by house, houseId="+houseId;
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

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Map<String, Object>> loadCondoFeeList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize()+1;
	Integer endRow = startRow+pager.getPageSize()-1;
	
	/* =================== sql1用户查询符合条件的记录总数 ========================================================== */
	StringBuilder sql1 = new StringBuilder();
	sql1.append("select count(CF_ID) From tb_CondoFee,tb_CondoFeeItem,tb_Project,tb_Company " + 
		    "where tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Com_ID = tb_Company.Com_ID and tb_Company.Com_ID = "+comId);
	sql1.append(ParamsToString.toString(params));
	logger.debug(sql1.toString());

	/* =================== sql用于查询符合条件的记录  ============================================================== */
	StringBuilder sql = new StringBuilder();
	sql.append("WITH DataList AS (SELECT ROW_NUMBER() OVER (");
	if (order==null){
	    sql.append("ORDER BY tb_CondoFee.House_ID ASC) ");
	} else {
	    sql.append(""+order+") ");
	}
        sql.append("as RowNum,CF_ID,Pro_Name,tb_House.House_Num,tb_Owner.Owner_Name,CF_Month,State,Ought_Money,Fetch_Money,Input_Time,Comment " +
		   "From tb_CondoFee,tb_CondoFeeItem,tb_Project,tb_Company,tb_House,tb_Owner " +
		   "where tb_CondoFee.House_ID = tb_House.House_ID and tb_CondoFee.Owner_ID = tb_Owner.Owner_ID and tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Com_ID = tb_Company.Com_ID and tb_Company.Com_ID = "+comId);
	sql.append(ParamsToString.toString(params));
	sql.append(") SELECT * FROM DataList WHERE RowNum BETWEEN "+startRow+" AND "+endRow);
	logger.debug(sql.toString());
	
	/* ======================= 开始执行查询 ===================================================================== */
	Transaction tx = null;
	Session session = getSession();
	try {
	    tx = session.beginTransaction();
	    Connection conn = session.connection();
	    PreparedStatement stmt1 = conn.prepareStatement(sql1.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs1 = stmt1.executeQuery();
	    rs1.first();
	    pager.setRowsCount(rs1.getInt(1));
	    
	    PreparedStatement stmt = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    list = generateList(rs);
            conn.close();
            stmt.close();
            rs.close();
         } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
	 } catch (RuntimeException e){
	     tx.rollback();
	     throw e;
	 } finally {
	     tx.commit();
	     session.close();
	 }
	 
	return list;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#loadCondoFeeList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Map<String, Object>> loadCondoFeeList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize()+1;
	Integer endRow = startRow+pager.getPageSize()-1;
	
	/* =================== sql1用户查询符合条件的记录总数 ========================================================== */
	StringBuilder sql1 = new StringBuilder();
	sql1.append("select count(CF_ID) From tb_CondoFee,tb_CondoFeeItem,tb_Project " + 
		    "where tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Pro_ID = "+proId);
	sql1.append(ParamsToString.toString(params));
	logger.debug(sql1.toString());

	/* =================== sql用于查询符合条件的记录  ============================================================== */
	StringBuilder sql = new StringBuilder();
	sql.append("WITH DataList AS (SELECT ROW_NUMBER() OVER (");
	if (order==null){
	    sql.append("ORDER BY tb_CondoFee.House_ID ASC) ");
	} else {
	    sql.append(""+order+") ");
	}
        sql.append("as RowNum,CF_ID,Pro_Name,tb_House.House_Num,tb_Owner.Owner_Name,CF_Month,State,Ought_Money,Fetch_Money,Input_Time,Comment " +
		   "From tb_CondoFee,tb_CondoFeeItem,tb_Project,tb_House,tb_Owner " +
		   "where tb_CondoFee.House_ID = tb_House.House_ID and tb_CondoFee.Owner_ID = tb_Owner.Owner_ID and tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Pro_ID = "+proId);
	sql.append(ParamsToString.toString(params));
	sql.append(") SELECT * FROM DataList WHERE RowNum BETWEEN "+startRow+" AND "+endRow);
	logger.debug(sql.toString());
	
	/* ======================= 开始执行查询 ===================================================================== */
	Transaction tx = null;
	Session session = getSession();
	try {
	    tx = session.beginTransaction();
	    Connection conn = session.connection();
	    PreparedStatement stmt1 = conn.prepareStatement(sql1.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs1 = stmt1.executeQuery();
	    rs1.first();
	    pager.setRowsCount(rs1.getInt(1));
	    
	    PreparedStatement stmt = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    list = generateList(rs);
            conn.close();
            stmt.close();
            rs.close();
         } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
	 } catch (RuntimeException e){
	     tx.rollback();
	     throw e;
	 } finally {
	     tx.commit();
	     session.close();
	 }
	 
	return list;
    }

    private List<Map<String, Object>> generateList(ResultSet rs) throws SQLException{
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	rs.beforeFirst();
        while(rs.next()){
            Map<String, Object> attrMap = new LinkedHashMap<String, Object>();
	    attrMap.put("CF_ID", rs.getObject(2));
	    attrMap.put("Pro_Name", rs.getObject(3));
	    attrMap.put("tb_House.House_Num", rs.getObject(4));
	    attrMap.put("tb_Owner.Owner_Name", rs.getObject(5));
	    attrMap.put("CF_Month", rs.getObject(6));
	    attrMap.put("State", rs.getObject(7));
	    attrMap.put("Ought_Money", rs.getObject(8));
	    attrMap.put("Fetch_Money", rs.getObject(9));
	    attrMap.put("Input_Time", rs.getObject(10));
	    attrMap.put("Comment", rs.getObject(11));
	    list.add(attrMap);
        }
    	return list;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#getAmount_By_Com_State(java.lang.Integer, java.lang.String)
     */
    @Override
    public Integer getAmount_By_Com_State(Integer comId, String state) {
	Integer amount = null;
	/* ================= 设置查询的SQL语句 ====================================================================== */
	StringBuilder sql = new StringBuilder();
	sql.append("select count(CF_ID) From tb_CondoFee,tb_CondoFeeItem,tb_Project,tb_Company " + 
		   "where tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Com_ID = tb_Company.Com_ID and tb_Company.Com_ID = "+comId+" and State ='"+state+"'");
	logger.debug(sql.toString());
	/* ======================= 开始执行查询 ===================================================================== */
	Transaction tx = null;
	Session session = getSession();
	try {
	    tx = session.beginTransaction();
	    Connection conn = session.connection();
	    PreparedStatement stmt = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    rs.first();
	    amount = rs.getInt(1);
	    conn.close();
            stmt.close();
            rs.close();
	} catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
	} catch (RuntimeException e){
	    tx.rollback();
	    throw e;
	} finally {
	    tx.commit();
	    session.close();
	}
	return amount;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#getAmount_By_Pro_State(java.lang.Integer, java.lang.String)
     */
    @Override
    public Integer getAmount_By_Pro_State(Integer proId, String state) {
	Integer amount = null;
	/* ================= 设置查询的SQL语句 ====================================================================== */
	StringBuilder sql = new StringBuilder();
	sql.append("select count(CF_ID) From tb_CondoFee,tb_CondoFeeItem,tb_Project " + 
		   "where tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Pro_ID = "+proId+" and State ='"+state+"'");
	logger.debug(sql.toString());
	/* ======================= 开始执行查询 ===================================================================== */
	Transaction tx = null;
	Session session = getSession();
	try {
	    tx = session.beginTransaction();
	    Connection conn = session.connection();
	    PreparedStatement stmt = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    rs.first();
	    amount = rs.getInt(1);
	    conn.close();
            stmt.close();
            rs.close();
	} catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
	} catch (RuntimeException e){
	    tx.rollback();
	    throw e;
	} finally {
	    tx.commit();
	    session.close();
	}
	return amount;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#getMoneyInfo_ByCom(java.lang.Integer, java.util.Map)
     */
    @Override
    public List<Double> getMoneyInfo_ByCom(Integer comId,
	    Map<String, Object> params) {
	List<Double> list = new ArrayList<Double>();
	
	/* ================= 设置查询的SQL语句 ====================================================================== */
	StringBuilder sql1 = new StringBuilder();
	sql1.append("select sum(Ought_Money),sum(Fetch_Money) From tb_CondoFee,tb_CondoFeeItem,tb_Project,tb_Company " + 
		"where tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Com_ID = tb_Company.Com_ID and tb_Company.Com_ID = "+comId);
	sql1.append(ParamsToString.toString(params));
	logger.debug(sql1.toString());
	
	/* ======================= 开始执行查询 ===================================================================== */
	Transaction tx = null;
	Session session = getSession();
	try {
	    tx = session.beginTransaction();
	    Connection conn = session.connection();
	    PreparedStatement stmt = conn.prepareStatement(sql1.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    rs.first();
	    list.add(rs.getDouble(1));
	    list.add(rs.getDouble(2));
	    conn.close();
            stmt.close();
            rs.close();
         } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
	 } catch (RuntimeException e){
	     tx.rollback();
	     throw e;
	 } finally {
	     tx.commit();
	     session.close();
	 }
	return list;
    }

    /**
     * @see org.pmp.dao.business.ICondoFeeDAO#getMoneyInfo_ByPro(java.lang.Integer, java.util.Map)
     */
    @Override
    public List<Double> getMoneyInfo_ByPro(Integer proId,
	    Map<String, Object> params) {
        List<Double> list = new ArrayList<Double>();
	
	/* ================= 设置查询的SQL语句 ====================================================================== */
	StringBuilder sql1 = new StringBuilder();
	sql1.append("select sum(Ought_Money),sum(Fetch_Money) From tb_CondoFee,tb_CondoFeeItem,tb_Project " + 
		"where tb_CondoFee.CFI_ID = tb_CondoFeeItem.CFI_ID and tb_CondoFeeItem.Pro_ID = tb_Project.Pro_ID and tb_Project.Pro_ID = "+proId);
	sql1.append(ParamsToString.toString(params));
	logger.debug(sql1.toString());
	
	/* ======================= 开始执行查询 ===================================================================== */
	Transaction tx = null;
	Session session = getSession();
	try {
	    tx = session.beginTransaction();
	    Connection conn = session.connection();
	    PreparedStatement stmt = conn.prepareStatement(sql1.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    rs.first();
	    list.add(rs.getDouble(1));
	    list.add(rs.getDouble(2));
	    conn.close();
            stmt.close();
            rs.close();
         } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
	 } catch (RuntimeException e){
	     tx.rollback();
	     throw e;
	 } finally {
	     tx.commit();
	     session.close();
	 }
	return list;
    }

    
}
