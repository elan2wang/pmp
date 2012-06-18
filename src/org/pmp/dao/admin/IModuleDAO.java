/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午07:58:39
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IModuleDAO {
    public void saveModule(TbModule instance);
    public void updateModule(TbModule instance);
    public void deleteModule(TbModule instance);
    
    public TbModule getModuleByID(Integer modId);
    
    public List getModuleList();
    
    public List getModuleListByRoleID(Integer roleId);
    
    public List getNoneGrantedModuleByRoleID(Integer roleId);
}
