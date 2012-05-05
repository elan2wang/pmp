/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:31:57
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IGroupService;
import org.pmp.service.admin.IRoleService;
import org.pmp.service.admin.IUserGroupRoleService;
import org.pmp.service.admin.IUserService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.vo.TbGroup;
import org.pmp.vo.TbRole;
import org.pmp.vo.TbUser;
import org.pmp.vo.TbUserGroupRole;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(UserAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IUserService userService;
    private IRoleService roleService;
    private IGroupService groupService;
    private IUserGroupRoleService ugrService;
    
    private TbUser user;
    private Integer roleId;
    private Integer groupId;
    private Integer userId;
    private Integer currentPage=1;
    private Integer pageSize=15;
    
    //~ Constructor ====================================================================================================
    


    //~ Methods ========================================================================================================
    public String addUser(){
	//the username and realname is the same by default
	user.setUsername(user.getRealname());
	//default password is 123456
	user.setPassword("123456");
	//user.setPassword(Encoder.md5("123456"));
	user.setIssys(false);
	
	userService.addUser(user);
	//user obtain the id after saving
	
	TbRole role = roleService.getRoleByID(roleId);
	TbGroup group = groupService.getGroupByID(groupId);
	TbUserGroupRole ugr = new TbUserGroupRole();
	ugr.setTbGroup(group);
	ugr.setTbRole(role);
	ugr.setTbUser(user);
	
	ugrService.addUGR(ugr);
	
	return SUCCESS;
    }
    
    public void deleteUser(){
    	userService.deleteUser(userId);
    	ugrService.deleteUGR_ByUserID(userId);
    }
    

    public String getUserById(){
    	TbUserGroupRole ugr = ugrService.getUGR_ByUserID(userId);
    	
    	List<?> roleList = roleService.getRoleList();
    	Pager pager = new Pager(1000,1);
    	Integer level = ugr.getTbRole().getRoleLevel();
    	List<?> groupList = groupService.getGroupListByLevel(pager, level);
    	
    	HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("ugr", ugr);
    	request.setAttribute("roleList", roleList);
    	request.setAttribute("groupList", groupList);
    	return SUCCESS;
    }
    
    /**
     * @Title: updateUser
     * @Description: 修改用户基本信息，除了用户名和密码
     
     */
    public String updateUser(){
	logger.debug("aaa");
        TbUser user2 = userService.getUserById(user.getUserId());
    	user.setUsername(user2.getUsername());
    	user.setPassword(user2.getPassword());
    	user.setIssys(user2.isIssys());
    	userService.editUser(user);
    	TbUserGroupRole ugr = ugrService.getUGR_ByUserID(user.getUserId());
        logger.debug("bbb");
    	
    	TbRole role = roleService.getRoleByID(roleId);
    	TbGroup group = groupService.getGroupByID(groupId);
    	ugr.setTbGroup(group);
    	ugr.setTbRole(role);
    	ugr.setTbUser(user);
    	
    	ugrService.editUGR(ugr);
    	return SUCCESS;
    }
    
    /**
     * @Title: editUsernamePassword
     * @Description: 修改用户的用户名和密码
     */
    public String editUsernamePassword(){
	TbUser user2 = userService.getUserById(userId);
	user.setEnabled(user2.isEnabled());
	user.setIdentify(user2.getIdentify());
	user.setIssys(user2.isIssys());
	user.setMobile(user2.getMobile());
	user.setPosition(user2.getPosition());
	user.setRealname(user2.getRealname());
	user.setUserDesc(user2.getUserDesc());
	user.setUserId(userId);
	
	userService.editUser(user);
	return SUCCESS;
    }
    
    
    public void loadRoleList(){
	logger.debug("loadRoleList");
	List<?> roleList = roleService.getRoleList();
	String[] attrs = {"roleId","roleName"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2Json(roleList, "org.pmp.vo.TbRole", show);
	logger.debug(data);
	JsonConvert.output(data);
	
    }
    /**
     * @Title: loadRoleGroupList
     * @Description: 加载添加用户界面的角色下拉列表和用户组下拉列表数据
     */
    public void loadGroupList(){
	logger.debug("loadRoleGroupList");
	Pager pager = new Pager(1000,1);
	Integer level = roleService.getRoleByID(roleId).getRoleLevel();
	List<?> groupList = groupService.getGroupListByLevel(pager, level);
	String[] attrs = {"groupId","groupName"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2Json(groupList, "org.pmp.vo.TbGroup", show);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    /**
     * @Title: loadUserList
     * @Description: 加载用户信息列表的数据，由ajax异步调用
     */
    public void loadUserList(){
    	Pager pager = new Pager(pageSize, currentPage);
    	List<?> users =  userService.getUserList(pager);
    	
    	String[] attrs = {"userId","realname","mobile","identify","position","userDesc","enabled","issys"};
    	List<String> show = Arrays.asList(attrs);
    	String data = JsonConvert.list2Json(pager, users, "org.pmp.vo.TbUser",show);
    	logger.debug(data);
    	JsonConvert.output(data);
    }
    
    //~ Getters and Setters ============================================================================================
    public IUserService getUserService() {
        return userService;
    }
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public IGroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }

    public IUserGroupRoleService getUgrService() {
        return ugrService;
    }

    public void setUgrService(IUserGroupRoleService ugrService) {
        this.ugrService = ugrService;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
