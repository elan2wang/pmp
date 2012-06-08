/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午02:24:21
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Company;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICompanyService {
    public void addCompany(Company company);
    public void editCompany(Company company);
    public void deleteCompany(Company company);
    public Company getCompanyByID(Integer companyID);
    public Company getCompanyByName(String companyName);
    public List<Company> loadCompanyList_ByChinaMobile(Map<String,Object>params,String order,Pager pager);
}
