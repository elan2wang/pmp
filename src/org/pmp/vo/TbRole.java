package org.pmp.vo;

// Generated 2012-3-27 12:23:42 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * TbRole generated by hbm2java
 */
public class TbRole implements java.io.Serializable {

    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private boolean enabled;
    private boolean issys;

    public TbRole() {
    }

    public TbRole(Integer roleId) {
	this.roleId = roleId;
    }

    public TbRole(Integer roleId, String roleName, String roleDesc,
	    boolean enabled, boolean issys) {
	this.roleId = roleId;
	this.roleName = roleName;
	this.roleDesc = roleDesc;
	this.enabled = enabled;
	this.issys = issys;
    }

    public Integer getRoleId() {
	return this.roleId;
    }

    public void setRoleId(Integer roleId) {
	this.roleId = roleId;
    }

    public String getRoleName() {
	return this.roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

    public String getRoleDesc() {
	return this.roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
    }

    public boolean isEnabled() {
	return this.enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public boolean isIssys() {
	return this.issys;
    }

    public void setIssys(boolean issys) {
	this.issys = issys;
    }

}
