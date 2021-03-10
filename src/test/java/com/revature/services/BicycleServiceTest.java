package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;

class BicycleServiceTest {
	private static BicycleService bicycleServ;

	@BeforeEach
	void setUp() throws Exception {
		bicycleServ = new BicycleServiceImpl();
	}

	@Test
	public void bicycleById() {
		Bicycle bicycleById = bicycleServ.getBicycleById(8);
		assertEquals(8, bicycleById.getId());
	}
	
	@Test
	public void deleteBicycle() {
		Bicycle b = new Bicycle();
		b.setId(21);
		Integer id = bicycleServ.deleteBicycle(b);
		assertEquals(id, b.getId());
	}
	
	@Test
	public void addBicycle() throws Exception{
		Bicycle b = new Bicycle();
		Integer id = bicycleServ.addBicycle(b);
		assertEquals(id, b.getId());
	}
	
	
	
	@Test
	public void updateBicycle() {
		Bicycle b = new Bicycle();
		Integer id = bicycleServ.updateBicycle(b);
		assertEquals(id, b.getId());
	}
	
	
	
	@Test
	public void getAvailableBicycles() {
		Set<Bicycle> b = bicycleServ.getAvailableBicycles();
		for(Bicycle b1: b) {
			assertTrue("Available".equals(b1.getStatus().getName()));
		}	
		
	}
	
	@Test
	public void getMyBicycles() {
		Offer o = new Offer();
		Set<Bicycle> b = bicycleServ.getMyBicycles(o.getUser());
		for(Bicycle b1: b) {
			assertTrue("Accepted".equals(o.getOfferStatus().getName()));
		}	
		
	}
	
//	@Test
//	public void getAllBicycles() {
//		Set<Bicycle> b = bicycleServ.getAllBicycles();
//		for(Bicycle b1: b) {
//			assertTrue(b.contains(b1));
//		}	
//		
//	}

}
