/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午12:33:22
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface MyUserDetails extends UserDetails {
    public Integer getUserId();
    
    public String getRealname();
    
    public String getMobile();
    
    public String getIdentify();
    
    public String getPosition();
    
    public String getUserDesc();
    
    public boolean isIssys();
}
