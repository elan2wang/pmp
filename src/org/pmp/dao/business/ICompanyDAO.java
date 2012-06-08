/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午12:02:50
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.Company;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICompanyDAO {
    public void saveCompany(Company company);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);
    
    public Company getCompanyByID(Integer companyID);
    public Company getCompanyByName(String companyName);
    public List<Company> loadCompanyList_ByChinaMobile(Map<String,Object>params,String order,Pager pager);
}
