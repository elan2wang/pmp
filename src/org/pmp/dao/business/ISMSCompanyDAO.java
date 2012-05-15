/**
 * Author            : Elan
 * Created On        : 2012-4-17 上午11:57:02
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.SMSCompany;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ISmsCompanyDAO {
    public void saveSMSCompany(SMSCompany instance);
    
    public void updateSMSCompany(SMSCompany instance);
    
    public void delereSMSCompany(Integer smscId);
    
    public SMSCompany getSMSCompanyByID(Integer smscId);
    
    public SMSCompany getSMSCompanyByComID(Integer comId);
    
    public List<?> loadSMSCompanyList(Pager pager);
}
