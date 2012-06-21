/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:09:05
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

import org.pmp.vo.RepairAttach;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRepairAttachService {
    public void addRepairAttach(RepairAttach instance);
    
    public void deleteRepairAttach(RepairAttach instance);
    
    public List<RepairAttach> loadRepairAttachList_ByOP(Integer opId);
}
