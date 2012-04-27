/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午03:52:18
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbRoleAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRoleAuthorityDAO {
    public void batchSave(List<TbRoleAuthority> list);
    public void batchDeleteByRoleID(Integer roleID);
    public void batchUpdateByRoleID(Integer roleID,List<TbRoleAuthority> list);
}
