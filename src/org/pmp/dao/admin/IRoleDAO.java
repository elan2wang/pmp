/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午02:50:05
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbRole;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IRoleDAO {
    public void saveRole(TbRole instance);
    public void updateRole(TbRole instance);
    public void deleteRole(Integer roleID);
    
    /**
     * @Title: getRoleByID
     * @Description: 根据角色的编号获取角色实体
     *
     * @param  roleID  角色的编号
     * @return TbRole  角色实体
     */
    public TbRole getRoleByID(Integer roleID);
    
    /**
     * @Title: getRoleList
     * @Description: 获取所有角色的实体列表
     *
     * @return List  角色的实体列表
     */
    public List getRoleList();
    
}
