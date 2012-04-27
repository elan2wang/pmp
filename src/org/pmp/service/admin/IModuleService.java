/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:17:44
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.vo.TbModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IModuleService {
    public void saveModule(TbModule instance);
    public void editModule(TbModule instance);
    public void deleteModule(Integer modId);
    
    public TbModule getModuleByID(Integer modId);
    
    public List getModuleList();
    
    public List getModuleListByRoleID(Integer roleId);
    
    public List getNoneGrantedModuleByRoleID(Integer roleId);
}
