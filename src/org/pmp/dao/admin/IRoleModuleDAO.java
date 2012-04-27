/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:43:00
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbRoleModule;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRoleModuleDAO {
    public void batchSave(List<TbRoleModule> list);
    public void batchDeleteByRoleID(Integer roleID);
    public void batchUpdateByRoleID(Integer roleID,List<TbRoleModule> list);
}
