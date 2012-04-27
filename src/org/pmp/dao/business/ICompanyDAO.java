/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午12:02:50
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Company;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICompanyDAO {
    public void saveCompany(Company company);
    public void updateCompany(Company company);
    public void deleteCompany(Integer companyID);
    public Company getCompanyByID(Integer companyID);
    public Company getCompanyByName(String companyName);
    public List<?> loadCompanyList(Pager pager);
    public List<?> loadEnabledCompanyList(Pager pager);
    public List<?> loadDisabledCompanyList(Pager pager);
}
