package org.pmp.vo;

// Generated 2012-4-19 19:37:27 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * TbModule generated by hbm2java
 */
public class TbModule implements java.io.Serializable {

    private Integer modId;
    private String modName;
    private Integer modLevel;
    private String modUrl;
    private Integer modOrder;
    private boolean enabled;
    private boolean issys;

    public TbModule() {
    }

    public TbModule(Integer modId) {
	this.modId = modId;
    }

    public TbModule(Integer modId, String modName, Integer modLevel,
	    String modUrl, Integer modOrder, boolean enabled, boolean issys) {
	this.modId = modId;
	this.modName = modName;
	this.modLevel = modLevel;
	this.modUrl = modUrl;
	this.modOrder = modOrder;
	this.enabled = enabled;
	this.issys = issys;
    }

    public Integer getModId() {
	return this.modId;
    }

    public void setModId(Integer modId) {
	this.modId = modId;
    }

    public String getModName() {
	return this.modName;
    }

    public void setModName(String modName) {
	this.modName = modName;
    }

    public Integer getModLevel() {
	return this.modLevel;
    }

    public void setModLevel(Integer modLevel) {
	this.modLevel = modLevel;
    }

    public String getModUrl() {
	return this.modUrl;
    }

    public void setModUrl(String modUrl) {
	this.modUrl = modUrl;
    }

    public Integer getModOrder() {
        return modOrder;
    }

    public void setModOrder(Integer modOrder) {
        this.modOrder = modOrder;
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
