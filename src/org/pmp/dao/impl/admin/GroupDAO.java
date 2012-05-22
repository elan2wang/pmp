/**
 * Author            : Elan
 * Created On        : 2012-3-29 上午11:34:56
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IGroupDAO;
import org.pmp.util.Pager;
import org.pmp.vo.TbGroup;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class GroupDAO extends BaseDAO implements IGroupDAO {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(GroupDAO.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void saveGroup(TbGroup instance){
	String debugMsg = "save group";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void updateGroup(TbGroup instance){
	String debugMsg = "update group";
	try {
	    updateInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	
    }
    public void deleteGroup(Integer groupID){
	String hql = "delete TbGroup where groupId="+groupID.toString();
	String debugMsg = "delete group groupID="+groupID;
	try {
	    deleteInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public TbGroup getGroupByID(Integer groupID){
	TbGroup group = null;
	String hql = "from TbGroup group where group.groupId = "+groupID.toString();
	String debugMsg = "getGroupByID groupID="+groupID.toString();
	try{
	    group = (TbGroup)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return group;
    }
    
    public List getGroupList(Pager pager){
	List list = null;
	String hql = "from TbGroup group order by group.groupLevel asc";
	String debugMsg = "getGroupList";
	try{
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
    
    public List getGroupListByLevel(Pager pager,Integer level){
	List list = null;
	String hql = "from TbGroup group where group.groupLevel = "+level.toString();
	String debugMsg = "getGroupListByLevel groupLevel="+level.toString();
	try{
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
    
    public List<?> loadGroupList_ByComAndLevel(String comName,Integer level,String order){
	String debugMsg = "load group List by company and groupLevel,comName"+comName+" groupLevel="+level;
	List<?> list = null;
	String hql = null;
	if (level==2){
	    hql = "from TbGroup where groupLevel=2 and refDomain='"+comName+"'";
	}
	if (level==3){
	    hql = "from TbGroup where groupLevel=3 and fatherGroupId=(select groupId from TbGroup where refDomain='"+comName+"')";
	}
	try{
	    list = loadListByCondition(hql,new Pager(10000,1),debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
}
