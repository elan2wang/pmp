/**
 * Author            : Elan
 * Created On        : 2012-6-25 下午10:16:02
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.action.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.util.Pager;
import org.pmp.validate.ValidateUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class BaseAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(BaseAction.class.getName());
    //~ Instance Fields ================================================================================================
    
    /* FlexiGrid post parameters */
    protected Integer page=1;
    protected Integer rp=15;
    protected String sortname;
    protected String sortorder;
    protected String query;
    protected String qtype;
    /* FlexiGrid post parameters */
   
    //~ Methods ========================================================================================================
    
    /* get query parameters */
    protected Map<String, Object> getParams(){
	Map<String,Object> params = new HashMap<String, Object>();
	if(query.equals(""))return params;
	if(qtype.contains(",")){
	    String[] qtypes = qtype.split(",");
	    String[] querys = query.split(",");
	    for(int i=0;i<qtypes.length;i++){
		if (!querys[i].equals("null")){
		    params.put(qtypes[i], querys[i]);
		}
            }
	} else {
	    params.put(qtype, query);
	}
	return params;
    }
    
    /* get query order */
    protected String getOrder(){
	String order = null;
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	}
	return order;
    }
    
    /* get pager */
    protected Pager getPager(){
	return new Pager(rp,page);
    }
    
    //~ Getters and Setters ============================================================================================

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

}
