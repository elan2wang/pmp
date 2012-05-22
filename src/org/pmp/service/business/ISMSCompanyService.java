/**
 * Author            : Elan
 * Created On        : 2012-4-17 下午12:29:19
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.SMSCompany;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ISMSCompanyService {
    public void addSMSCompany(SMSCompany instance);
    
    public void editSMSCompany(SMSCompany instance);
    
    public void delereSMSCompany(Integer smscId);
    
    public void batchDeleteSMSCompany(List<SMSCompany> list);
    
    public SMSCompany getSMSCompanyByID(Integer smscId);
    
    public SMSCompany getSMSCompanyByComID(Integer comId);
    
    public List loadSMSCompanyList(Pager pager);
}
