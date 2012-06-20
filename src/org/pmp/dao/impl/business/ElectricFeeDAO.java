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

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
    public void generateElectricFee(Integer efiId) {
	// TODO Auto-generated method stub

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
