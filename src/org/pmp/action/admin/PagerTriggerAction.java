/**
 * Author            : Elan
 * Created On        : 2012-5-22 下午02:25:00
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.action.admin;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.service.admin.IGroupService;
import org.pmp.service.admin.IRoleService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PagerTriggerAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PagerTriggerAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IGroupService groupService;
    private IRoleService roleService;
    
    /* used when loadGroupList_ByUser */
    private Integer roleId;
    
    //~ Methods ========================================================================================================
    
    /**
     * @Title: loadRoleList_ByRole
     * @Description: 根据当前用户的roleLevel,获取roleLevel>=当前roleLevel的角色列表
     */
    public void loadRoleList_ByUser(){
	Integer roleLevel = SessionHandler.getUserRole().getRoleLevel();
	List<?> roleList = roleService.loadRoleList_LevelNotBellow(roleLevel, "");
	String[] attrs = {"roleId","roleName"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2Json(roleList, "org.pmp.vo.TbRole", show);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    /**
     * @Title: loadGroupList_ByRole
     * @Description: 根据角色的Level和用户的权限，获取用户组列表
     */
    public void loadGroupList_ByRole(){
	Integer roleLevel = roleService.getRoleByID(roleId).getRoleLevel();
	List<?> groupList = null;
	Object obj = SessionHandler.getUserRefDomain();
	if (obj instanceof Company){
	    groupList = groupService.loadGroupList_ByComAndLevel(((Company)obj).getComName(), roleLevel, "");
	} else {
	    groupList = groupService.getGroupListByLevel(new Pager(1000,1), roleLevel);
	}
	String[] attrs = {"groupId","groupName"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2Json(groupList, "org.pmp.vo.TbGroup", show);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    //~ Getters and Setters ============================================================================================


    public IGroupService getGroupService() {
        return groupService;
    }


    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }


    public IRoleService getRoleService() {
        return roleService;
    }


    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
