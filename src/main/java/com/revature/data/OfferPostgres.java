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
import com.revature.beans.OfferStatus;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtils;

public class OfferPostgres implements OfferDAO {
	
	private ConnectionUtils cu = ConnectionUtils.getConnectionUtil();
	private Logger log = Logger.getLogger(OfferPostgres.class);

	@Override
	public Offer add(Offer o) {
		// TODO Auto-generated method stub
		
         Offer offerToMake = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setFloat(1, o.getPrice());
			pstmt.setInt(2, o.getUser().getId());
			pstmt.setInt(3, o.getBicycle().getId());
			pstmt.setInt(4, o.getOfferStatus().getId());
			pstmt.setBoolean(5, o.getPayed());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				offerToMake = o;
				offerToMake.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			}
		
		
		
		return offerToMake;

	}

	@Override
	public Set<Offer> getOffersByBicycleId(Integer id) {
		// TODO Auto-generated method stub
		 Set<Offer> offers = new HashSet<>();
		
			try (Connection conn = cu.getConnection()) {
				String sql = "select *,  offer.id as offer_id, bicycle.id as b_id,  offer_status.id as osid, offer_status.name as osname, person.id as uid\r\n"
						+ "from offer  \r\n"
						+ "inner join bicycle on offer.bicycle_id = bicycle.id\r\n"
						+ "inner join person on offer.person_id=person.id\r\n"
						+ "inner join offer_status on offer.offer_status_id=offer_status.id where offer_status.name='Pending' and  bicycle_id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Offer o = new Offer();
					o.setId(rs.getInt("offer_id"));
					o.setPrice(rs.getFloat("amount"));
					Bicycle b = new Bicycle();
					b.setId(rs.getInt("b_id"));
					b.setReference(rs.getString("reference"));
					b.setColor(rs.getString("color"));
					b.setSize(rs.getFloat("size_b"));
					b.setPrice(rs.getFloat("price"));
					b.setBrand(rs.getString("brand"));
					b.setColor(rs.getString("description"));
					o.setBicycle(b);
					User u = new User();
					u.setId(rs.getInt("uid"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("passwd"));
					o.setUser(u);
					OfferStatus os = new OfferStatus();
					os.setId(rs.getInt("osid"));
					os.setName(rs.getString("osname"));
					o.setOfferStatus(os);
					o.setPayed(rs.getBoolean("payed"));
					
					
					offers.add(o);
										
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return offers;
			
				}

	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		Offer o = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from offer join offer_status on offer.offer_status_id = offer_status.id where offer.id =?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();


			
			while (rs.next()) {
				o = new Offer();
				o.setId(rs.getInt("id"));
				o.setPrice(rs.getFloat("amount"));
				
				OfferStatus os = new OfferStatus();
				os.setId(rs.getInt("offer_status_id"));
				os.setName(rs.getString("name"));
				o.setOfferStatus(os);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
		
	}

	
	@Override
	public void updateOffer(Offer o, Bicycle b) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "UPDATE offer SET offer_status_id = 2 where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			
			String sql2 = "UPDATE offer SET offer_status_id = 3 where id <> ? and bicycle_id=? and offer_status_id=1";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, o.getId());
			pstmt2.setInt(2, b.getId());
			
			pstmt2.executeUpdate();
			
			String sql3 = "UPDATE bicycle SET status_id = 2 where id= ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, b.getId());
			
			pstmt3.executeUpdate();
			
			
			if (rowsAffected > 0) {
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public void updateOfferStatusRejected(Offer o) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "UPDATE offer SET offer_status_id = 3 where id = ? and offer_status_id=1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<Offer> getOfferByStatus(User u) {
		// TODO Auto-generated method stub
		Set<Offer> offers = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select *, bicycle.id as b_id  from offer join offer_status  ON offer.offer_status_id = offer_status.id join bicycle on offer.bicycle_id=bicycle.id \r\n"
					+ "					where offer_status.name='Accepted' and payed=false and person_id=?;";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				// need to add the fields 
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setPrice(rs.getFloat("amount"));
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("b_id"));
				b.setReference(rs.getString("reference"));
				b.setColor(rs.getString("color"));
				b.setSize(rs.getFloat("size_b"));
				b.setPrice(rs.getFloat("price"));
				b.setDescription(rs.getString("description"));
				o.setBicycle(b);
				
				offers.add(o);
				
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
		
			}

	@Override
	public Set<Offer> getAll(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer delete(Offer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer update(Offer t) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	}

	
				
			
		
		
	


