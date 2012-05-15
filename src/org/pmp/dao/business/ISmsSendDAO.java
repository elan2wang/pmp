/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午04:32:19
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.SMSSend;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ISmsSendDAO {
    public void saveSmsSend(SMSSend instance);
    
    public List<?> loadSmsSend_ByCompany(Integer comId,Pager pager,Map<String,Object> params,String order);
    
    public List<?> loadSmsSend_ByPerson(String name,Pager pager,Map<String,Object> params,String order);
    
    public List<?> loadAllSmsSend(Pager pager,Map<String,Object> params,String order);
}
