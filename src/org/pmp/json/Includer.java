/**
 * Author            : Elan
 * Created On        : 2012-6-16 下午04:43:57
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class Includer {

    //~ Fields ========================================================================================================
    private final List<String> showAttributes;
    public static final Includer DEFAULT = new Includer(new ArrayList<String>());
    
    //~ Constructor ====================================================================================================
    public Includer(List<String> showAttributes){
	this.showAttributes = showAttributes;
    }
    
    //~ Methods ========================================================================================================
    public boolean includeField(String fieldName){
	if (showAttributes==null || showAttributes.size()==0)return true;
	
	for (String attr : showAttributes){
	    if (attr.equals(fieldName))return true;
	    int i = attr.indexOf(fieldName);
	    if (i!=-1)return true;
	}
	return false;
    }

    public List<String> getShowAttributes() {
        return showAttributes;
    }
}
