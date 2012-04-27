/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:32:07
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.pmp.dao.admin.IUserDAO;
import org.pmp.service.admin.IUserService;
import org.pmp.util.Pager;
import org.pmp.vo.TbUser;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserService implements IUserService {

    //~ Static Fields ==================================================================================================

    //~ Instance Fields ================================================================================================
    private IUserDAO userDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addUser(TbUser instance){
	userDAO.saveUser(instance);
    }
    
    public void editUser(TbUser instance){
	userDAO.updateUser(instance);
    }
    
    public void deleteUser(Integer userID){
	userDAO.deleteUser(userID);
    }
    
    public void passwordReset(String password, Integer userID) {
	userDAO.passwordReset(password, userID);
    }

    public TbUser getUserByUsername(String username) {
	return userDAO.getUserByUsername(username);
    }

    public List getUserList(Pager pager) {
	return userDAO.getUserList(pager);
    }

    public List getUserListByGroup(Pager pager, Integer groupID) {
	return userDAO.getUserListByGroup(pager, groupID);
    }
    
    @Override
	public TbUser getUserById(Integer userId) {
		return userDAO.getUserById(userId);
	}
    //~ Getters and Setters ============================================================================================
    public IUserDAO getUserDAO() {
        return userDAO;
    }
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

	
}
