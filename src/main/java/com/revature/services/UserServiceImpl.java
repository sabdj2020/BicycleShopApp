package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.data.DAOFactory;
import com.revature.data.UserDAO;
import com.revature.exceptions.NonUniqueUsernameException;

public class UserServiceImpl implements UserService {
    private UserDAO userDao;
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	//constructor
	public UserServiceImpl() {
		userDao = DAOFactory.getUserDAO();
	}
    
	//Create or insert
	@Override
	public Integer addUser(User u) throws NonUniqueUsernameException {
		return userDao.add(u).getId();
	}

	//Read or select
	@Override
	public User getUserById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getByUsername(username);
	}

	//Update
	@Override
	public void updateUser(User u) {
		if (getUserById(u.getId()) != null)
			userDao.update(u);
		else
			log.debug("no such user in the database.");
	}
    
	//delete
	@Override
	public void deleteUser(User u) {
		if (getUserById(u.getId()) != null)
			userDao.delete(u);
		else
			log.debug("no such user in the database.");
	}


}
