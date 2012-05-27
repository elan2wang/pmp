package org.pmp.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IUserDAO;
import org.pmp.service.admin.ISecurityService;
import org.pmp.vo.TbUser;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(MyUserDetailsService.class.getName ());
	
    //~ Fields =========================================================================================================
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private ISecurityService securityService;
    private IUserDAO userDAO;
	
    //~ Methods ========================================================================================================
	
    public UserDetails loadUserByUsername(String arg0)
            throws UsernameNotFoundException, DataAccessException {
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        //if the username is not exist then throw UsernameNotFoundException
        TbUser user = userDAO.getUserByUsername(arg0);
        if (user == null){
            throw new UsernameNotFoundException("username:"+arg0+",there is no user exist with this name");
        }
        //if the username has no authority then throw UsernameNotFoundException
        auths = securityService.loadUserAuthoritiesByUsername(arg0);
        if (auths.size() == 0){
            throw new UsernameNotFoundException("username:"+arg0+",this user has no authority");
        }
		
        TbUser user2 = new TbUser(user.getUserId(),user.getUsername(),user.getPassword(),user.getRealname(),
                user.getMobile(),user.getIdentify(),user.getPosition(),user.getUserDesc(),true,false,
                true,true,true,auths);
        return user2;
    }
	
    //~ Getters and Setters ============================================================================================
    public ISecurityService getSecurityService() {
        return securityService;
    } 
    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }
    public IUserDAO getUserDAO() {
        return userDAO;
    }
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
