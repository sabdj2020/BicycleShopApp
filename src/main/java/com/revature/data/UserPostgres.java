package com.revature.data;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class UserPostgres implements UserDAO {
	
	private ConnectionUtils cu = ConnectionUtils.getConnectionUtil();
	private Logger log = Logger.getLogger(UserPostgres.class);
	
	@Override
	public User add(User u) throws NonUniqueUsernameException {
		User newPerson = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setInt(3, u.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				newPerson = u;
				newPerson.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return newPerson;
	}

	
	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		
        User user = new User();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from person  where id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("passwd"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return user;
		}
			
	
	@Override
	public User getByUsername(String username) {
		
		User user = new User();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person.id as person_id, person.username,person.passwd, person_role.id as role_id, person_role.\"name\" as role_name from person join person_role on person.user_role_id = person_role.id where username = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				user.setId(rs.getInt("person_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("passwd"));
				Role r = new Role();
				r.setId(rs.getInt("role_id"));
				r.setName(rs.getString("role_name"));
				user.setRole(r);
				

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return user;
		}
			
	
	

	@Override
	public User update(User u) {
		return u;
		// TODO Auto-generated method stub

	}

	@Override
	public User delete(User u) {
		return u;
		// TODO Auto-generated method stub

	}


	@Override
	public Set<User> getAll(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
