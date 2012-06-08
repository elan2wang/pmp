/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:31:57
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.jms.JmsPublisher;
import org.pmp.service.admin.IGroupService;
import org.pmp.service.admin.IRoleService;
import org.pmp.service.admin.IUserGroupRoleService;
import org.pmp.service.admin.IUserService;
import org.pmp.service.business.ISmsSendService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Project;
import org.pmp.vo.SMSCompany;
import org.pmp.vo.SMSSend;
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
    private ISmsSendService smsSendService;
    
    private TbUser user;
    private Integer roleId;
    private Integer groupId;
    private Integer userId;
    private String userName;
  
	/* used when deleteUser */
    private String idStr;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */

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
    
    public void checkUser(){
    	TbUser user = userService.getUserByUsername(userName);
    	String data = null;
    	if(user!=null){
    	    data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Failed")+"}";
    	}
    	else{
    	    data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Success")+"}";
    	}
     	logger.debug(data);
    	JsonConvert.output(data);
    }
    
    public String editUser(){
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
    
    public void deleteUser(){
	List<TbUser> userList = new ArrayList<TbUser>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    TbUser user = userService.getUserById(Integer.parseInt(checkedID[i]));
	    userList.add(user);
	}
	userService.batchDelete(userList);
    }

    public String getUserDetail(){
    	TbUserGroupRole ugr = ugrService.getUGR_ByUserID(userId);
    	HttpServletRequest request = ServletActionContext.getRequest();
    	request.setAttribute("ugr", ugr);
    	return SUCCESS;
    }
    
    public void passwordReset(){
	/* 生成随机6位数字密码 */
	Integer randomPassword = (int) (System.currentTimeMillis()%10000000);
	logger.debug("password="+randomPassword);
	
	TbUser user = userService.getUserById(userId);
	user.setPassword(randomPassword.toString());
	/* 更新该用户的密码 */
	userService.editUser(user);
	
	/* 获得该用户所在公司的OpenMAS账号 */
	SMSCompany smsc = SessionHandler.getSMSCompany(userId);
	/* 创建SMSSend对象 */
	SMSSend smsSend = new SMSSend();
	smsSend.setSMSCompany(smsc);
	smsSend.setSmssPerson(SessionHandler.getUser().getUsername());
	smsSend.setSmssReceiver(user.getMobile());
	smsSend.setSmssState("new");
	smsSend.setSmssTime(new Date());
	smsSend.setSmssContent("您重置后的密码为："+randomPassword+",登录后请及时更改您的密码！");
	
	/* 保存该待发送短信记录 */
	smsSendService.addSmsSend(smsSend);
	
	/* 给消息队列发消息 */
	JmsPublisher.sendMessgae(smsSend.getSmssId().toString());
    }
    
    public void loadUserList(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by tbUser.username asc";
	}
	
	List<?> ugrList = null;
	if (SessionHandler.getUserGroup().getGroupLevel()==1){
	    ugrList = ugrService.loadUGRList(params, order, pager);
	}
	if (SessionHandler.getUserGroup().getGroupLevel()==2){
	    String comName = ((Company)SessionHandler.getUserRefDomain()).getComName();
	    ugrList = ugrService.loadUGRList_ByCom(comName, params, order, pager);
	}
	if (SessionHandler.getUserGroup().getGroupLevel()==3){
	    String proName = ((Project)SessionHandler.getUserRefDomain()).getProName();
	    ugrList = ugrService.loadUGRList_ByPro(proName, params, order, pager);
	}
	
    	/* convert ugrList instance to JsonData */
	StringBuilder sb = new StringBuilder();
	sb.append("{\n");
    	sb.append("  "+JsonConvert.toJson("page")+":\""+JsonConvert.toJson(pager.getCurrentPage())+"\",\n");
    	sb.append("  "+JsonConvert.toJson("total")+":"+JsonConvert.toJson(pager.getRowsCount())+",\n");
    	sb.append("  "+JsonConvert.toJson("rows")+":[\n");
	Iterator<?> ite = ugrList.iterator();
	while(ite.hasNext()){
	    TbUserGroupRole ugr = (TbUserGroupRole)ite.next();
	    sb.append("    {"+JsonConvert.toJson("id")+":\""+JsonConvert.toJson(ugr.getTbUser().getUserId())+"\",");
	    sb.append(JsonConvert.toJson("cell")+":{");
	    sb.append(JsonConvert.toJson("tbUser.realname")+":"+JsonConvert.toJson(ugr.getTbUser().getRealname())+",");
	    sb.append(JsonConvert.toJson("tbUser.username")+":"+JsonConvert.toJson(ugr.getTbUser().getUsername())+",");
	    sb.append(JsonConvert.toJson("tbUser.mobile")+":"+JsonConvert.toJson(ugr.getTbUser().getMobile())+",");
	    sb.append(JsonConvert.toJson("tbUser.identify")+":"+JsonConvert.toJson(ugr.getTbUser().getIdentify())+",");
	    sb.append(JsonConvert.toJson("tbUser.position")+":"+JsonConvert.toJson(ugr.getTbUser().getPosition())+",");
	    sb.append(JsonConvert.toJson("tbUser.enabled")+":"+JsonConvert.toJson(ugr.getTbUser().isEnabled())+",");
	    sb.append(JsonConvert.toJson("tbUser.issys")+":"+JsonConvert.toJson(ugr.getTbUser().isIssys())+",");
	    sb.append(JsonConvert.toJson("tbGroup.groupName")+":"+JsonConvert.toJson(ugr.getTbGroup().getGroupName())+",");
	    sb.append(JsonConvert.toJson("tbRole.roleName")+":"+JsonConvert.toJson(ugr.getTbRole().getRoleName())+"}},\n");
	}
	if(ugrList.size()!=0)sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}");
	logger.debug(sb.toString());
    	JsonConvert.output(sb.toString());
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRp() {
        return rp;
    }

    public void setRp(Integer rp) {
        this.rp = rp;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
    /**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ISmsSendService getSmsSendService() {
	    return smsSendService;
	}

	public void setSmsSendService(ISmsSendService smsSendService) {
	    this.smsSendService = smsSendService;
	}

}
