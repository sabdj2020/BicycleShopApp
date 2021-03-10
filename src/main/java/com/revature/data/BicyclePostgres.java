package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Bicycle;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueBicycleException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtils;

public class BicyclePostgres implements BicycleDAO {
	
	private ConnectionUtils cu = ConnectionUtils.getConnectionUtil();
	private Logger log = Logger.getLogger(BicyclePostgres.class);
	
	/*-----------------this is for the Create------------------*/
	@Override
	public Bicycle add(Bicycle b){
		// TODO Auto-generated method stub
        Bicycle bicycleToAdd = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bicycle values (default, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, b.getReference());
			pstmt.setString(2, b.getColor());
			pstmt.setFloat(3, b.getSize());
			pstmt.setFloat(4, b.getPrice());
			pstmt.setString(5, b.getBrand());
			pstmt.setString(6, b.getDescription());
			pstmt.setInt(7, b.getStatus().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				bicycleToAdd = b;
				bicycleToAdd.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				System.out.println("this bicycle already exixts");
			}
			e.printStackTrace();
		}
		
		return bicycleToAdd;
	}

	
	/*-----------------this is for the Read------------------*/
	
	@Override
	public Set<Bicycle> getAvailableBicycles() {
		// TODO Auto-generated method stub
       Set<Bicycle> bicycles = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select *, status.name as status_name from bicycle join status on bicycle.status_id =status.id where status.name ='Available'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("id"));
				b.setReference(rs.getString("reference"));
				b.setColor(rs.getString("color"));
				b.setSize(rs.getFloat("size_b"));
				b.setPrice(rs.getFloat("price"));
				b.setBrand(rs.getString("brand"));
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				b.setStatus(s);
				
				
				bicycles.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bicycles;
	}



	@Override
	public Bicycle getById(Integer id) {
		// TODO Auto-generated method stub
		
       
		 Bicycle b = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from bicycle join status on bicycle.status_id =status.id where bicycle.id =?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				b = new Bicycle();
				b.setId(rs.getInt("id"));
				b.setReference(rs.getString("reference"));
				b.setColor(rs.getString("color"));
				b.setSize(rs.getFloat("size_b"));
				b.setPrice(rs.getFloat("price"));
				b.setBrand(rs.getString("brand"));
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("name"));
				b.setStatus(s);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
		
	}
	
	@Override
	public Set<Bicycle> getAll(User u) {
		// TODO Auto-generated method stub
        Set<Bicycle> bicycles = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from bicycle b \r\n"
					+ "join offer o on b.id=o.bicycle_id\r\n"
					+ "join status s on b.status_id =s.id \r\n"
					+ "where b.status_id =2 and o.offer_status_id =2 and person_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("id"));
				b.setReference(rs.getString("reference"));
				b.setColor(rs.getString("color"));
				b.setSize(rs.getFloat("size_b"));
				b.setPrice(rs.getFloat("price"));
				b.setBrand(rs.getString("brand"));
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("name"));
				b.setStatus(s);
				
				bicycles.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bicycles;
	}

	/*-----------------this is for the Update------------------*/


	@Override
	public Bicycle update(Bicycle b) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bicycle set reference = ?,price = ?  where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getReference());
			pstmt.setFloat(2, b.getPrice());
			//pstmt.setInt(3, b.getStatus().getId());
			pstmt.setInt(3, b.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

		
	/*-----------------this is for the Delete------------------*/

	@Override
	public Bicycle delete(Bicycle b) {
		// TODO Auto-generated method stub
		System.out.println("hohoho");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete from bicycle where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}



}

	



