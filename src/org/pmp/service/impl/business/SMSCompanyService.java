/**
 * Author            : Elan
 * Created On        : 2012-4-17 下午12:30:08
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.business.ISMSCompanyDAO;
import org.pmp.service.business.ISMSCompanyService;
import org.pmp.util.Pager;
import org.pmp.vo.SMSCompany;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SMSCompanyService implements ISMSCompanyService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SMSCompanyService.class.getName());
    //~ Instance Fields ================================================================================================
    private ISMSCompanyDAO smsCompanyDAO;
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================

    public void addSMSCompany(SMSCompany instance){
	smsCompanyDAO.saveSMSCompany(instance);
    }
    
    public void editSMSCompany(SMSCompany instance){
	smsCompanyDAO.updateSMSCompany(instance);
    }
    
    public void delereSMSCompany(SMSCompany instance){
	smsCompanyDAO.delereSMSCompany(instance);
    }
    
    public void batchDeleteSMSCompany(List<SMSCompany> list)
    {
    	smsCompanyDAO.batchDeleteSMSCompany(list);
    }
    public SMSCompany getSMSCompanyByID(Integer smscId){
	return smsCompanyDAO.getSMSCompanyByID(smscId);
    }
    
    public SMSCompany getSMSCompanyByComID(Integer comId){
	return smsCompanyDAO.getSMSCompanyByComID(comId);
    }
    
    public List<?> loadSMSCompanyList(Pager pager){
	return smsCompanyDAO.loadSMSCompanyList(pager);
    }
    
    //~ Getters and Setters ============================================================================================
    public ISMSCompanyDAO getSmsCompanyDAO() {
        return smsCompanyDAO;
    }
    public void setSMSCompanyDAO(ISMSCompanyDAO smsCompanyDAO) {
        this.smsCompanyDAO = smsCompanyDAO;
    }

}
