/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:10:30
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

import org.pmp.vo.OperateDetail;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IOperateDetailDAO {
    public void saveOperateDetail(OperateDetail instance);
    
    public List<OperateDetail> loadOperateDetailList_ByOP(Integer opId);
}
