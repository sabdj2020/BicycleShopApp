package com.revature.services;

import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

public interface UserService {
	    // create
		public Integer addUser(User u) throws NonUniqueUsernameException;
		// read
		public User getUserById(Integer id);
		public User getUserByUsername(String username);
		// update
		public void updateUser(User u);
		// delete
		public void deleteUser(User u);

}
