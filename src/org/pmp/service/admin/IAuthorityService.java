/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午02:08:40
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.vo.TbAuthority;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IAuthorityService {
    public void addResource(TbAuthority instance);
    public void editResource(TbAuthority instance);
    public void deleteResource(Integer authID);
    public TbAuthority getAuthorityByID(Integer authID);
    public List getAllAuthorities();
    public List getAuthorityList();
    
    /**
     * @Title: getAuthoritiesByRoleID
     * @Description: 根据角色ID获取该角色所有的权限实体列表
     *
     * @param  roleID  角色的ID
     * @return List  TbAuthority类型的列表，保存所有该角色的权限实体列表
     */
    public List getAuthoritiesByRoleID(Integer roleID);
    
    /**
     * @Title: getNoneGrantedAuthByRoleID
     * @Description: 根据角色ID获取该角色所没有的权限实体列表
     *
     * @param  roleID  角色的ID
     * @return List  TbAuthority类型的列表，保存所有该角色的权限实体列表
     */
    public List getNoneGrantedAuthByRoleID(Integer roleID);
}
