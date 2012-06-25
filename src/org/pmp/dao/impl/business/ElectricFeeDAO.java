/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午02:55:37
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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IElectricFeeDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.ElectricFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeDAO extends BaseDAO implements IElectricFeeDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeDAO.class.getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#generateElectricFee(java.lang.Integer)
     */
    @Override
    public void generateElectricFee(final Integer efiId) {
	logger.debug("begin to generate electricFee with efiId="+efiId);
	Work work = new Work(){
            public void execute(Connection connection)throws SQLException{
                String procedure = "{call ef_generate(?) }";
                CallableStatement cstmt = connection.prepareCall(procedure);
                cstmt.setInt(1, efiId);
                cstmt.executeUpdate();
            }
        };
        executeWork(work);
        logger.debug("successfully generate electricFee with efiId="+efiId);
    }


    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#getElectricFee_ByID(java.lang.Integer)
     */
    @Override
    public ElectricFee getElectricFee_ByID(Integer efId) {
	String debugMsg = "get ElectricFee by ID,efId="+efId;
	String hql = "from ElectricFee where efId="+efId;
	ElectricFee ef = null;
	try {
	    ef = (ElectricFee)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return ef;
    }

   
    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#batchUpdateElectricFee(java.util.List)
     */
    @Override
    public void batchUpdateElectricFee(final List<ElectricFee> list) {
	logger.debug("begin to batch update ElectricFee");
	logger.debug("list.size="+list.size());
        Work work = new Work(){
            public void execute(Connection connection) throws SQLException{
        	String sql ="update tb_ElectricFee set ProMeterFee=?,LiftMeterFee=?,Total_Money=? where EF_ID=?";
        	PreparedStatement stmt = connection.prepareStatement(sql);
        	for (int i=0;i<list.size();i++){
        	    stmt.setDouble(1, list.get(i).getProMeterFee());
        	    stmt.setDouble(2, list.get(i).getLiftMeterFee());
        	    stmt.setDouble(3, list.get(i).getTotalMoney());
		    stmt.setInt(4, list.get(i).getEfId());
		    stmt.executeUpdate();
		}
            }
        };
        executeWork(work);
	logger.debug("successfully batch update ElectricFee");
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#batchDeleteElectricFee(java.util.List)
     */
    @Override
    public void batchDeleteElectricFee(final List<ElectricFee> list) {
	logger.debug("begin to batch delete ElectricFee");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_ElectricFee where EF_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getEfId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete ElectricFee");
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	List<ElectricFee> list = null;
	String debugMsg = "load ElectricFee list by electricFeeItem, efiId="+efiId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFee where electricFeeItem.efiId="+efiId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by houseOwner.house.houseNum asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<ElectricFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<ElectricFee> list = null;
	String debugMsg = "load ElectricFee list by Company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFee where houseOwner.house.houseId in (select houseId from House where building.builId in (" +
		   "select builId from Building where project.proId in (select proId from Project where company.comId="+comId+")))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by houseOwner.house.houseNum asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<ElectricFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	List<ElectricFee> list = null;
	String debugMsg = "load ElectricFee list by Project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFee where houseOwner.house.houseId in (select houseId from House where building.builId in (" +
		   "select builId from Building where project.proId="+proId+"))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by houseOwner.house.houseNum asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<ElectricFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByHouse(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByHouse(Integer houseId,
	    Map<String, Object> params, String order, Pager pager) {
	List<ElectricFee> list = null;
	String debugMsg = "load ElectricFee list by house, houseId="+houseId;
	StringBuilder hql = new StringBuilder();
	hql.append("from ElectricFee where houseOwner.house.houseId="+houseId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by houseOwner.house.houseNum asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<ElectricFee>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    
}
