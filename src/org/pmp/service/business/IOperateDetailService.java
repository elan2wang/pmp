/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:09:45
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.vo.OperateDetail;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IOperateDetailService {
    public void addOperateDetail(OperateDetail instance);
    
    public List<OperateDetail> loadOperateDetailList_ByOP(Integer opId);
}
