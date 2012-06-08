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
        //根据用户名从数据库中获取用户对象
        TbUser user = userDAO.getUserByUsername(arg0);
        //若获取的用户对象为null，则抛出UsernameNotFoundException
        if (user == null){
            throw new UsernameNotFoundException("username:"+arg0+
        	    ",there is no user exist with this name");
        }
        //如果用户的权限集合为null,则抛出UsernameNotFoundException
        auths = securityService.loadUserAuthoritiesByUsername(arg0);
        if (auths.size() == 0){
            throw new UsernameNotFoundException("username:"+arg0+
        	    ",this user has no authority");
        }
	//如果获取的用户不为null，且该用户的权限集合不为null，则返回该用户对象	
        TbUser user2 = new TbUser(user.getUserId(),user.getUsername()
        	,user.getPassword(),user.getRealname(),user.getMobile(),
        	user.getIdentify(),user.getPosition(),user.getUserDesc(),
        	true,false,true,true,true,auths);
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
