package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtils;

public class PaymentPostgres implements PaymentDAO {
	
	private ConnectionUtils cu = ConnectionUtils.getConnectionUtil();
	private Logger log = Logger.getLogger(PaymentPostgres.class);

	@Override
	public Payment add(Payment pay) {
		// TODO Auto-generated method stub
		 Payment payement = null;
		

			try (Connection conn = cu.getConnection()) {
				conn.setAutoCommit(false);
				
				String sql = "insert into payment values (default, ?, ?, ?, ?) ";
				String[] keys = {"id"};
				PreparedStatement pstmt = conn.prepareStatement(sql, keys);
				pstmt.setFloat(1, pay.getAmount());
				pstmt.setFloat(2, pay.getO().getPrice()-pay.getAmount());
				pstmt.setInt(3, pay.getO().getId());
				pstmt.setInt(4, pay.getWeeksRemaining());
				
				pstmt.executeUpdate();
				
				String sql2 = "UPDATE offer SET payed = true where id=?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, pay.getO().getId());
				pstmt2.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					payement=pay;
					payement.setId(rs.getInt("id"));
					conn.commit();
				} else {
					conn.rollback();
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				}
			
			
			
			return payement;

	}

	@Override
	public Set<Payment> getAll(User loggedInUser) {
		// TODO Auto-generated method stub
        Set<Payment> pay = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from payment p join offer o2 on o2.id =p.offer_id join bicycle b on b.id =o2.bicycle_id where person_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loggedInUser.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setAmount(rs.getFloat("amount_to_pay"));
				p.setRemainingPayment(rs.getFloat("remaining_amount"));
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setReference(rs.getString("reference"));
				o.setBicycle(b);
				p.setO(o);
				pay.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pay;
	}

	@Override
	public Payment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment update(Payment t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment delete(Payment t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Payment> getRemainingPayments(User loggedInUser) {
		// TODO Auto-generated method stub
		 Set<Payment> pay = new HashSet<>();
			
			try (Connection conn = cu.getConnection()) {
				String sql = "select * from payment p join offer o2 on o2.id =p.offer_id join bicycle b on b.id =o2.bicycle_id where remaining_amount<>0 and person_id=?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, loggedInUser.getId());
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Payment p = new Payment();
					p.setId(rs.getInt("id"));
					p.setAmount(rs.getFloat("amount_to_pay"));
					p.setWeeksRemaining(rs.getInt("week_remaining"));
					p.setRemainingPayment(rs.getFloat("remaining_amount"));
					Offer o = new Offer();
					o.setId(rs.getInt("offer_id"));
					Bicycle b = new Bicycle();
					b.setId(rs.getInt("bicycle_id"));
					b.setReference(rs.getString("reference"));
					o.setBicycle(b);
					p.setO(o);
					pay.add(p);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return pay;
	}

	@Override
	public Set<Payment> getAllPay() {
		// TODO Auto-generated method stub
        Set<Payment> pay = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from payment p join offer o2 on o2.id =p.offer_id join bicycle b on b.id =o2.bicycle_id";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);	
			
			while (rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setAmount(rs.getFloat("amount_to_pay"));
				p.setRemainingPayment(rs.getFloat("remaining_amount"));
				Offer o = new Offer();
				o.setId(rs.getInt("offer_id"));
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setReference(rs.getString("reference"));
				o.setBicycle(b);
				p.setO(o);
				pay.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pay;	
		}

	
	}


