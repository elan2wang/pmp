/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:01:08
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IModuleDAO;
import org.pmp.util.Pager;
import org.pmp.vo.TbModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ModuleDAO extends BaseDAO implements IModuleDAO {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ModuleDAO.class.getName());
   
    //~ Methods ========================================================================================================
    public void saveModule(TbModule instance){
	String debugMsg = "save module";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void updateModule(TbModule instance){
	String debugMsg = "update module,modId="+instance.getModId();
	try {
	    updateInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void deleteModule(Integer modId){
	String debugMsg = "delete module,modId="+modId;
	String hql = "delete from TbModule where modId="+modId;
	try {
	    deleteInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public TbModule getModuleByID(Integer modId){
	String debugMsg = "get module by ID,modId="+modId;
	String hql = "from TbModule where modId="+modId;
	TbModule module = null;
	try {
	    module = (TbModule)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return module;
    }
    
    public List getModuleList(){
	String debugMsg = "get module list";
	String hql = "from TbModule";
	Pager pager = new Pager(10000,1);
	List list = null;
	try {
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }
    
    public List getModuleListByRoleID(Integer roleId){
	String debugMsg = "get module list by role ID,roleId="+roleId;
	Pager pager = new Pager(10000,1);
        List moduleList = null;

	String hql = "select mod from TbModule mod,TbRoleModule rm where mod.modId = rm.tbModule.modId " +
	             "and rm.tbRole.roleId = "+roleId+" order by mod.modOrder asc";
        try {
	    moduleList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
        return moduleList;
    }
    
    public List getNoneGrantedModuleByRoleID(Integer roleId){
	String debugMsg = "get role none granted module list,roleId="+roleId;
	Pager pager = new Pager(10000,1);
        List moduleList = null;
        
	String hql = "select distinct mod from TbModule mod where mod.modId not in " +
	             "(select rm.tbModule.modId from TbRoleModule rm where rm.tbRole.roleId = "+roleId+") " +
	             "order by mod.modOrder asc";
        try {
	    moduleList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
        return moduleList;
    }
    
    //~ Getters and Setters ============================================================================================

}
