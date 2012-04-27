/**
 * Author            : Elan
 * Created On        : 2012-3-30 下午12:07:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import org.pmp.vo.TbUserGroupRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IUserGroupRoleService {
    public void addUGR(TbUserGroupRole instance);
    public void deleteUGR_ByUserID(Integer userID);
    public void editUGR(TbUserGroupRole instance);
    
    /**
     * @Title: getUGR_ByUserID
     * @Description: 根据用户编号获取一个TbUserGroupRole对象
     *
     * @param  userID  Integer类型，表示用户编号
     * @return TbUserGroupRole  用户实体对象
     * @throws RuntimeException
     */
    public TbUserGroupRole getUGR_ByUserID(Integer userID);
}
