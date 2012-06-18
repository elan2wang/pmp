/**
 * Author            : Elan
 * Created On        : 2012-6-18 上午08:39:52
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
public interface TypeAdapterFactory {
    <T> MyTypeAdapter <T> create();
}
