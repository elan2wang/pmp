package org.pmp.vo;

// Generated 2012-3-27 12:23:42 by Hibernate Tools 3.4.0.CR1

/**
 * TbAuthorityResource generated by hbm2java
 */
public class TbAuthorityResource implements java.io.Serializable {

    private Integer arId;
    private TbAuthority tbAuthority;
    private TbResource tbResource;

    public String toString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("[ "+arId+","+tbAuthority+","+tbResource);
    	sb.append(" ]");
    	return sb.toString();
    }
    
    public TbAuthorityResource() {
    }

    public TbAuthorityResource(Integer arId) {
	this.arId = arId;
    }

    public TbAuthorityResource(Integer arId, TbAuthority tbAuthority,
	    TbResource tbResource) {
	this.arId = arId;
	this.tbAuthority = tbAuthority;
	this.tbResource = tbResource;
    }

    public Integer getArId() {
	return this.arId;
    }

    public void setArId(Integer arId) {
	this.arId = arId;
    }

    public TbAuthority getTbAuthority() {
	return this.tbAuthority;
    }

    public void setTbAuthority(TbAuthority tbAuthority) {
	this.tbAuthority = tbAuthority;
    }

    public TbResource getTbResource() {
	return this.tbResource;
    }

    public void setTbResource(TbResource tbResource) {
	this.tbResource = tbResource;
    }

}
