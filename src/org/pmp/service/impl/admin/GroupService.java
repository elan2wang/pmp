/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:27:37
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IGroupDAO;
import org.pmp.service.admin.IGroupService;
import org.pmp.util.Pager;
import org.pmp.vo.TbGroup;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class GroupService implements IGroupService {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(GroupService.class.getName());
    //~ Instance Fields ================================================================================================
    private IGroupDAO groupDAO;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addGroup(TbGroup instance){
	groupDAO.saveGroup(instance);
    }
    
    public void editGroup(TbGroup instance){
	groupDAO.updateGroup(instance);
    }
    
    public void deleteGroup(Integer groupID){
	groupDAO.deleteGroup(groupID);
    }
    
    public TbGroup getGroupByID(Integer groupID){
	return groupDAO.getGroupByID(groupID);
    }
    
    public List getGroupList(Pager pager){
	return groupDAO.getGroupList(pager);
    }
    
    public List getGroupListByLevel(Pager pager,Integer level){
	return groupDAO.getGroupListByLevel(pager, level);
    }
    public List<?> loadGroupList_ByComAndLevel(String comName,Integer level,String order){
	return groupDAO.loadGroupList_ByComAndLevel(comName, level, order);
    }
    //~ Getters and Setters ============================================================================================
    public IGroupDAO getGroupDAO() {
        return groupDAO;
    }
    public void setGroupDAO(IGroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

}
