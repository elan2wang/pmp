/**
 * Author            : Elan
 * Created On        : 2012-6-18 上午10:20:33
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

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
enum JsonScope {
    EMPTY_ARRAY,
    
    NONEMPTY_ARRAY,
    
    EMPTY_OBJECT,
    
    DANGLING_NAME,
    
    NONEMPTY_OBJECT,
    
    EMPTY_DOCUMENT,
    
    NONEMPTY_DOCUMENT,
    
    CLOSED,
}
