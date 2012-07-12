/**
 * Author            : Elan
 * Created On        : 2012-5-6 下午03:47:24
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.pmp.validate.ValidateUtil;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ParamsToString {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ParamsToString.class.getName());

    //~ Methods ========================================================================================================
    public static String toString(Map<String,Object> params){
	StringBuilder sb = new StringBuilder();
	if (params==null||params.isEmpty()){
	    return "";
	} else {
	    sb.append(" and ");
	    Set<String> keys = params.keySet();
	    Iterator<String> ite = keys.iterator();
	    while(ite.hasNext()){
		String key = ite.next();
		Object value = params.get(key);
		/* transform value to string */
		if (key.equals("Buil_Num")){
		    /* 楼号精确匹配 */
		    sb.append(key+" = '"+value.toString()+"'");
		} else if (key.equals("h.House_Num")||key.equals("houseOwner.house.houseNum")){
		    /* 房号向后模糊匹配 */
		    sb.append(key+" like '"+value.toString()+"%'");
		} else {
		    /* if the value is like 2012-06 or 2012-06-07*/
		    if (ValidateUtil.isYearMonth(value.toString())||ValidateUtil.isValidDate(value.toString())){
			sb.append("convert(varchar,"+key+",120) like '%"+value.toString()+"%'");
		    } else {
			sb.append(key+" like '%"+value.toString()+"%'");
		    }
		}
		/* append link between parameters */
		sb.append(" and ");
	    }
	    sb.delete(sb.length()-5, sb.length());
	    
	    logger.debug(sb.toString());
	    return sb.toString();
	}
    }
}
