/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午01:00:06
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ICompanyDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Building;
import org.pmp.vo.Company;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CompanyDAO extends BaseDAO implements ICompanyDAO {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CompanyDAO.class.getName ());
    
    //~ Methods ========================================================================================================
    public void saveCompany(Company company) {
	String debugMsg = "save company";
	try {
	    saveInstance(company,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    public void updateCompany(Company company) {
	logger.debug("begin to update a company");
	String hql = "update Company set comName=?,comLegal=?,comLicense=?,comPhone=?,comAddress=?,registerTime=?," +
	             "registerMoney=?,comDesc=?,enabled=? where comId=?";
	Session session = getSession();
	Query query = session.createQuery(hql);
	query.setParameter(0, company.getComName());
	query.setParameter(1, company.getComLegal());
	query.setParameter(2, company.getComLicense());
	query.setParameter(3, company.getComPhone());
	query.setParameter(4, company.getComAddress());
	query.setParameter(5, company.getRegisterTime());
	query.setParameter(6, company.getRegisterMoney());
	query.setParameter(7, company.getComDesc());
	query.setParameter(8, company.isEnabled());
	query.setParameter(9, company.getComId());
	try {
	    Transaction tx = session.beginTransaction();
	    query.executeUpdate();
	    tx.commit();
	} catch (RuntimeException e){
	    logger.error("update a company failed",e);
	}
	logger.debug("update a company successful");
	session.close();
    }

    public void deleteCompany(Integer companyID) {
	Session session = getSession();
	logger.debug("begin to delete a company by ID.");
	try {
	    Transaction tx = session.beginTransaction();
	    Query query = session.createQuery("delete Company com where com.comId = "+companyID);
	    query.executeUpdate();
	    tx.commit();
	} catch (RuntimeException e){
	    logger.error("delete a company by ID failed.",e);
	    throw e;
	}
	logger.debug("delete a company by ID successfully.");
	session.close();
    }

    public Company getCompanyByID(Integer companyID) {
	String hql = "from Company com where com.comId="+companyID;
	String debugMsg = "get company by ID,comId="+companyID;
	Company company = null;
	try {
	    company = (Company)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return company;
    }

    public Company getCompanyByName(String companyName) {
	String hql = "from Company com where comName='"+companyName+"'";
	String debugMsg = "get company by companyName,companyName="+companyName;
	Company company = null;
	try {
	    company = (Company)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return company;
    }
	/**
	 * @Title: loadBuildingList_ByChinaMobile
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<Company> loadCompanyList_ByChinaMobile(Map<String,Object>params,String order,Pager pager)
	{
		List<Company> list = null;
		String debugMsg = "load company list by chinamobile";
		StringBuilder hql = new StringBuilder();
		hql.append("from Company");
		hql.append(ParamsToString.toString(params));
		if (order==null){
		    hql.append(" order by comId desc");
		} else {
		    hql.append(" "+order);
		}
		logger.debug(hql);
		try {
		    list = (List<Company>) loadListByCondition(hql.toString(),pager,debugMsg);
		} catch (RuntimeException e){
		    throw e;
		}
		
		return list;
	}
}
