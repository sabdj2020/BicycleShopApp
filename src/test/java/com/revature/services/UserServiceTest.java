package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

class UserServiceTest {
	private static UserService userServ;
	
	
	
	@BeforeAll
	public static void setup() {
		userServ = new UserServiceImpl();
		
		
		System.out.println("This will happen once before any of the tests");
	}
	
	@Test
	public void getUserById() {
		User userById = userServ.getUserById(1);
			assertEquals(1, userById.getId());
			
		}
	@Test
	public void addUser() throws NonUniqueUsernameException{
		User u = new User();
		Integer id = userServ.addUser(u);
		assertEquals(id, u.getId());
	}
	
	
	
	

	
	

}
