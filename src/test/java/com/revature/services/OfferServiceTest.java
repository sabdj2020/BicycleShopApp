package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.User;

class OfferServiceTest {

	private static OfferService offerService;

	@BeforeEach
	void setUp() throws Exception {
		offerService = new OfferServiceImpl();
	}

	@Test
	public void getOfferById() {
		Offer getOfferById = offerService.getOfferById(33);
		assertEquals(33, getOfferById.getId());
	}
	
	@Test
	public void getOffersByBicycleId() {
		
		Set<Offer> offers = offerService.getOffersByBicycleId(33);
		for(Offer o: offers) {
			assertEquals(33, o.getBicycle().getId());
		}		
	}
	
	@Test
	public void getAcceptedOffers() {
		User u = new User();
		u.setId(3);
		Set<Offer> offers = offerService.getAcceptedOffers(u);
		for(Offer o: offers) {
			assertTrue("Accepted".equals(o.getOfferStatus().getName()));
		}	
	}
	@Test
	public void makeOffer() throws Exception{
		Offer o = new Offer();
		Bicycle b = new Bicycle();
		b.setId(37);
		o.setBicycle(b);
		Integer id = offerService.makeOffer(o);
		assertEquals(id, o.getId());
	}
	
	@Test
	public void acceptOffer() {
		Offer o = new Offer();
		offerService.acceptOffer(o,o.getBicycle());
		assertEquals("Accepted", o.getOfferStatus().getName());
	}
	
	@Test
	public void rejectOffer() {
		Offer o = new Offer();
		offerService.rejectOffer(o);
		assertEquals("rejected", o.getOfferStatus().getName());
	}
	

}
