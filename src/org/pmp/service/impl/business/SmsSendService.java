/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午04:59:54
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.dao.business.ISmsSendDAO;
import org.pmp.service.business.ISmsSendService;
import org.pmp.util.Pager;
import org.pmp.vo.SMSSend;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SmsSendService implements ISmsSendService{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(SmsSendService.class.getName());
    //~ Instance Fields ================================================================================================
    private ISmsSendDAO smsSendDAO;

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.ISmsSendService#saveSmsSend(org.pmp.vo.SMSSend)
     */
    @Override
    public void saveSmsSend(SMSSend instance) {
	smsSendDAO.saveSmsSend(instance);
    }

    /**
     * @see org.pmp.service.business.ISmsSendService#loadSmsSend_ByCompany(java.lang.Integer, org.pmp.util.Pager, java.util.Map, java.lang.String)
     */
    @Override
    public List<?> loadSmsSend_ByCompany(Integer comId, Pager pager,
	    Map<String, Object> params, String order) {
	return smsSendDAO.loadSmsSend_ByCompany(comId, pager, params, order);
    }

    /**
     * @see org.pmp.service.business.ISmsSendService#loadSmsSend_ByPerson(java.lang.String, org.pmp.util.Pager, java.util.Map, java.lang.String)
     */
    @Override
    public List<?> loadSmsSend_ByPerson(String name, Pager pager,
	    Map<String, Object> params, String order) {
	return smsSendDAO.loadSmsSend_ByPerson(name, pager, params, order);
    }

    /**
     * @see org.pmp.service.business.ISmsSendService#loadAllSmsSend(org.pmp.util.Pager, java.util.Map, java.lang.String)
     */
    @Override
    public List<?> loadAllSmsSend(Pager pager, Map<String, Object> params,
	    String order) {
	return smsSendDAO.loadAllSmsSend(pager, params, order);
    }

    //~ Getters and Setters ============================================================================================

    public ISmsSendDAO getSmsSendDAO() {
        return smsSendDAO;
    }

    public void setSmsSendDAO(ISmsSendDAO smsSendDAO) {
        this.smsSendDAO = smsSendDAO;
    }

}
