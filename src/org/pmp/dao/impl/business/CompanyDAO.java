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
	String debugMsg = "update company";
	try {
	    updateInstance(company,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    public void deleteCompany(Company company) {
	String hql = "delete from Company where comId="+company.getComId();
	String debugMsg = "delete company,comId="+company.getClass();
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
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
	
    public List<Company> loadCompanyList_ByChinaMobile(Map<String,Object>params,String order,Pager pager)
    {
	List<Company> list = null;
	String debugMsg = "load company list by chinamobile";
	StringBuilder hql = new StringBuilder();
	hql.append("from Company");
	if (params.size()!=0){
	    hql.append(" where ");
            hql.append(ParamsToString.toString(params).substring(5));
	}
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
