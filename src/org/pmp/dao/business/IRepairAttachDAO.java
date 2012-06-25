/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:17:45
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

import org.pmp.vo.RepairAttach;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRepairAttachDAO {
    public void saveRepairAttach(RepairAttach instance);
    
    public RepairAttach getRepairAttach_ByID(Integer raId);
    
    public void deleteRepairAttach(RepairAttach instance);
    
    public List<RepairAttach> loadRepairAttachList_ByOP(Integer opId);
}
