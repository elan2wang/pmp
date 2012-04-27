/**
 * Author            : Elan
 * Created On        : 2012-3-27 上午11:45:16
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ISecurityService {
    public List getAllAuthorities();
    public List getResourcesByAuthority(String authName);
    public List loadUserAuthoritiesByUsername(String username);
}
