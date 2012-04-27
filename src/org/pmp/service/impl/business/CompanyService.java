/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午02:28:34
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.pmp.dao.business.ICompanyDAO;
import org.pmp.service.business.ICompanyService;
import org.pmp.util.Pager;
import org.pmp.vo.Company;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CompanyService implements ICompanyService {
    //~ Instance Fields ================================================================================================
    private ICompanyDAO companyDAO;
    
    //~ Methods ========================================================================================================
    public void addCompany(Company company) {
	companyDAO.saveCompany(company);
    }

    public void editCompany(Company company) {
	companyDAO.updateCompany(company);
    }

    public void deleteCompany(Integer companyID) {
	companyDAO.deleteCompany(companyID);
    }

    public Company getCompanyByID(Integer companyID) {
	return companyDAO.getCompanyByID(companyID);
    }

    public Company getCompanyByName(String companyName) {
	return companyDAO.getCompanyByName(companyName);
    }

    public List<?> loadCompanyList(Pager pager) {
	return companyDAO.loadCompanyList(pager);
    }

    public List<?> loadEnabledCompanyList(Pager pager) {
	return companyDAO.loadEnabledCompanyList(pager);
    }

    public List<?> loadDisabledComapnyList(Pager pager) {
	return companyDAO.loadDisabledCompanyList(pager);
    }

    //~ Getters and Setters ============================================================================================
    public ICompanyDAO getCompanyDAO() {
        return companyDAO;
    }

    public void setCompanyDAO(ICompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

}
