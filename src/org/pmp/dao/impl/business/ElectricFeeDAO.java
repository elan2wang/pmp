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
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IElectricFeeDAO;
import org.pmp.util.Pager;
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
     * @see org.pmp.dao.business.IElectricFeeDAO#batchUpdateElectricFee(java.util.List)
     */
    @Override
    public void batchUpdateElectricFee(List<ElectricFee> list) {
	// TODO Auto-generated method stub

    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#batchDeleteElectricFee(java.util.List)
     */
    @Override
    public void batchDeleteElectricFee(List<ElectricFee> list) {
	// TODO Auto-generated method stub

    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByEFI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByEFI(Integer efiId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see org.pmp.dao.business.IElectricFeeDAO#loadElectricFeeList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<ElectricFee> loadElectricFeeList_ByProject(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

   
    
}
