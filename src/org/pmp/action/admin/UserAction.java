/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:31:57
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
    
    /**
     * @Title: updateUser
     * @Description: 修改用户基本信息，除了用户名和密码
     
     */
    public String updateUser(){
        TbUser user2 = userService.getUserById(user.getUserId());
    	user.setUsername(user2.getUsername());
    	user.setPassword(user2.getPassword());
    	user.setIssys(user2.isIssys());
    	userService.editUser(user);
    	TbUserGroupRole ugr = ugrService.getUGR_ByUserID(user.getUserId());

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
    
    /**
     * @Title: loadRoleGroupList
     * @Description: 加载添加用户界面的角色下拉列表和用户组下拉列表数据
     */
    public void loadRoleGroupList(){
	logger.debug("loadRoleGroupList");
	Pager pager = new Pager(1000,1);
	List roles = roleService.getRoleList();
	List groups = groupService.getGroupList(pager);
	
	Iterator ite1 = groups.iterator();
	Iterator ite2 = roles.iterator();
	StringBuffer sb = new StringBuffer();
	if (ite1.hasNext()||ite2.hasNext()){
	    sb.append("{\n  ");
	    //添加用户组信息
	    sb.append(JsonConvert.toJson("Groups")+":[\n");
	    while(ite1.hasNext()){
		TbGroup group = (TbGroup)ite1.next();
		sb.append("    {");
		sb.append(JsonConvert.toJson("groupId")+":"+JsonConvert.toJson(group.getGroupId())+",");
		sb.append(JsonConvert.toJson("groupName")+":"+JsonConvert.toJson(group.getGroupName())+"},\n");
	    }
	    sb.deleteCharAt(sb.length()-2);
    	    sb.append("  ],\n  "); 
    	    //添加角色信息
    	    sb.append(JsonConvert.toJson("Roles")+":[\n");
	    while(ite2.hasNext()){
		TbRole role = (TbRole)ite2.next();
		sb.append("    {");
		sb.append(JsonConvert.toJson("roleId")+":"+JsonConvert.toJson(role.getRoleId())+",");
		sb.append(JsonConvert.toJson("roleName")+":"+JsonConvert.toJson(role.getRoleName())+"},\n");
	    }
	    sb.deleteCharAt(sb.length()-2);
	    sb.append("  ]\n}"); 
	}
	logger.debug(sb.toString());
	//output the Jason data
	try {    
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(sb.toString());
        } catch (IOException e) {                     
            e.printStackTrace();  
        } 
    }
    
    /**
     * @Title: loadUserList
     * @Description: 加载用户信息列表的数据，由ajax异步调用
     */
    public void loadUserList(){
    	Pager pager = new Pager(pageSize, currentPage);
    	List users =  userService.getUserList(pager);
    	StringBuffer sb = new StringBuffer();
		sb.append("{\n");
		sb.append("  "+JsonConvert.toJson("RowsCount")+":"+JsonConvert.toJson(pager.getRowsCount())+",\n");
		sb.append("  "+JsonConvert.toJson("PageSize")+":"+JsonConvert.toJson(pager.getPageSize())+",\n");
		sb.append("  "+JsonConvert.toJson("CurrentPage")+":"+JsonConvert.toJson(pager.getCurrentPage())+",\n");
		sb.append("  "+JsonConvert.toJson("PagesCount")+":"+JsonConvert.toJson(pager.getPagesCount())+",\n");
		sb.append("  "+JsonConvert.toJson("Rows")+":[\n");
		Iterator ite = users.iterator();
		while(ite.hasNext()){
			sb.append("    {");
			TbUser user = (TbUser)ite.next();
			sb.append(JsonConvert.toJson("userId")+":"+JsonConvert.toJson(user.getUserId().toString())+",");
			sb.append(JsonConvert.toJson("realname")+":"+JsonConvert.toJson(user.getRealname().toString())+",");
			sb.append(JsonConvert.toJson("mobile")+":"+JsonConvert.toJson(user.getMobile().toString())+",");
			sb.append(JsonConvert.toJson("identify")+":"+JsonConvert.toJson(user.getIdentify().toString())+",");
			sb.append(JsonConvert.toJson("position")+":"+JsonConvert.toJson(user.getPosition().toString())+",");
			sb.append(JsonConvert.toJson("userDesc")+":"+JsonConvert.toJson(user.getUserDesc().toString())+",");
			sb.append(JsonConvert.toJson("enabled")+":"+JsonConvert.toJson(user.isEnabled())+",");
			sb.append(JsonConvert.toJson("issys")+":"+JsonConvert.toJson(user.isIssys())+",");
			sb.deleteCharAt(sb.length()-1);
		    sb.append("},\n");
		}
		sb.deleteCharAt(sb.length()-2);
		sb.append("  ]\n}\n");
		logger.debug("Json data = "+sb.toString());
		try {    
	            HttpServletResponse response = ServletActionContext.getResponse();  
	            response.setContentType("application/json;charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().println(sb.toString());     
	        } catch (IOException e) {                     
	            e.printStackTrace();  
	        } 
    }
    
    public String getUserById(){
    	user = userService.getUserById(userId);
    	return SUCCESS;
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
