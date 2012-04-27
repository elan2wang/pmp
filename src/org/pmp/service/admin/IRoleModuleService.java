/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:56:09
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.vo.TbRoleModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRoleModuleService {
    public void batchSave(List<TbRoleModule> list);
    public void batchDeleteByRoleID(Integer roleID);
    public void batchUpdateByRoleID(Integer roleID,List<TbRoleModule> list);
}
