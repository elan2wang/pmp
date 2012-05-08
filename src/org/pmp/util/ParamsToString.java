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
	if (params.isEmpty()){
	    return "";
	} else {
	    sb.append(" and ");
	    Set<String> keys = params.keySet();
	    Iterator<String> ite = keys.iterator();
	    while(ite.hasNext()){
		String key = ite.next();
		sb.append(key+"=");
		Object value = params.get(key);
		/* transform value to string */
		if (value instanceof String)
		    sb.append("'"+value.toString()+"'");
		if (value instanceof Number)
		    sb.append(value);
		/* append link between parameters */
		sb.append(" and ");
	    }
	    sb.delete(sb.length()-5, sb.length());
	    
	    logger.debug(sb.toString());
	    return sb.toString();
	}
    }
}
