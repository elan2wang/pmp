/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:12:46
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

import org.pmp.vo.RepairFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRepairFeeDAO {
    public void batchSaveRepairFee(List<RepairFee> list);
    
    public void deleteRepairFee(RepairFee instance);
    
    public List<RepairFee> loadRepairFeeList_ByOP(Integer opId);
}
