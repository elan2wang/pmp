/**
 * Author            : Elan
 * Created On        : 2012-3-30 上午10:44:59
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbUserGroupRole;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IUserGroupRoleDAO {
    public void saveUGR(TbUserGroupRole instance);
    public void deleteUGR_ByUserID(Integer userID);
    public void updateUGR(TbUserGroupRole instance);
    
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
