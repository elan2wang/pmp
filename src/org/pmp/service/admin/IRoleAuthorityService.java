/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午04:00:51
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.vo.TbRoleAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRoleAuthorityService {
    public void batchAdd(List<TbRoleAuthority> list);
    public void batchDeleteByRoleID(Integer roleID);
    public void batchEditByRoleID(List<TbRoleAuthority> list,Integer roleID);
}
