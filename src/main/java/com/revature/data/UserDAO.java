package com.revature.data;

import java.util.Set;

import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {
	
	public User add(User u) throws NonUniqueUsernameException;
	
	//public User getById(Integer id);

	public User getByUsername(String username);

	//public void update(User u);

	//public void delete(User u);

	

}
