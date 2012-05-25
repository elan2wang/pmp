/**
 * Author            : Elan
 * Created On        : 2012-5-10 下午08:08:49
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.validate;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeValidate {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(CondoFeeValidate.class
	    .getName());
   
    //~ Methods ========================================================================================================
    public static Boolean isRight(List<String> list){
	if (list.get(0).equals(""))return false;
	if (!Pattern.matches("\\d+", list.get(0)))return false;
	
	if (list.get(7).equals(""))return false;
	//if (list.get(5).matches(""));
	return true;
    }
}
