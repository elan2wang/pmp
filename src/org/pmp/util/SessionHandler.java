/**
 * Author            : Elan
 * Created On        : 2012-4-18 下午05:04:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.util;

import org.apache.log4j.Logger;
import org.pmp.service.admin.IUserGroupRoleService;
import org.pmp.service.admin.IUserService;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.IProjectService;
import org.pmp.service.business.ISMSCompanyService;
import org.pmp.vo.Company;
import org.pmp.vo.Project;
import org.pmp.vo.SMSCompany;
import org.pmp.vo.TbGroup;
import org.pmp.vo.TbRole;
import org.pmp.vo.TbUser;
import org.pmp.vo.TbUserGroupRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class SessionHandler {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(SessionHandler.class.getName());
    
    //~ Methods ========================================================================================================
    public static TbUser getUser(){
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (authentication == null)return null;
	String principal = authentication.getPrincipal().toString();
	String [] aa = principal.split(";");
	String bb = null;
	for (int i=0;i<aa.length;i++){
	    if (aa[i].contains("Username")){
		bb = aa[i];
		break;
	    }
	}
	String [] cc = bb.split(":");
	String username = cc[2].trim();
	
	IUserService userService = (IUserService)SpringContextUtil.getBean("userService");
	TbUser user = userService.getUserByUsername(username);
	return user;
    }

    public static TbGroup getUserGroup(){
	TbUser user = getUser();
	IUserGroupRoleService ugrService = (IUserGroupRoleService)SpringContextUtil.getBean("ugrService");
	TbUserGroupRole ugr = ugrService.getUGR_ByUserID(user.getUserId());
	TbGroup group = ugr.getTbGroup();
	return group;
    }
    
    public static TbRole getUserRole(){
	TbUser user = getUser();
	IUserGroupRoleService ugrService = (IUserGroupRoleService)SpringContextUtil.getBean("ugrService");
	TbUserGroupRole ugr = ugrService.getUGR_ByUserID(user.getUserId());
	TbRole role = ugr.getTbRole();
	
	return role;
    }
    
    public static Object getUserRefDomain(){
	TbGroup group = getUserGroup();
	String refDomain = group.getRefDomain().trim();
	Integer level = group.getGroupLevel();
	Object obj = null;
	if(level == 2){
	    ICompanyService companyService = (ICompanyService)SpringContextUtil.getBean("companyService");
	    obj = companyService.getCompanyByName(refDomain);
	} else if(level == 3){
	    IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
	    obj = projectService.getProjectByName(refDomain);
	}
	return obj;
    }
    
    public static SMSCompany getSMSCompany(){
	SMSCompany smsc = null;
        ISMSCompanyService smscService = (ISMSCompanyService)SpringContextUtil.getBean("smsCompanyService");
	if (getUserRefDomain().getClass().getName().equals("org.pmp.vo.Company")){
	    smsc = smscService.getSMSCompanyByComID(((Company)getUserRefDomain()).getComId());
	}
	if (getUserRefDomain().getClass().getName().equals("org.pmp.vo.Project")){
	    Integer comId = ((Project)getUserRefDomain()).getCompany().getComId();
	    smsc = smscService.getSMSCompanyByComID(comId);
	}
	return smsc;
    }
}
