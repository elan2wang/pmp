/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:07:15
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

import org.pmp.vo.RepairFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRepairFeeService {
    public void batchAddRepairFee(List<RepairFee> list);
    
    public void deleteRepairFee(RepairFee instance);
    
    public List<RepairFee> loadRepairFeeList_ByOP(Integer opId);
}
