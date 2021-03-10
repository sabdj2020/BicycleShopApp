package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;

class PaymentServiceTest {

	private static PaymentService paymentService;

	@BeforeEach
	void setUp() throws Exception {
		paymentService = new PaymentServiceImpl();
	}

	@Test
	public void getMyPaymens() {
		User u = new User();
		Set<Payment> pay = paymentService.getMyPaymens(u);
		for(Payment p: pay) {
			assertEquals(u.getId(), p.getO().getUser().getId());
		}	
	}
	
	@Test
	public void getMyRemainingPaymens() {
		User u = new User();
		Set<Payment> pay = paymentService.getMyRemainingPaymens(u);
		for(Payment p: pay) {
			assertEquals(u.getId(), p.getO().getUser().getId());
			assertTrue(p.getRemainingPayment()!=0);
		}	
	}
	
	@Test
	public void makePayment() throws Exception{
		Offer o = new Offer();
		o.setId(30);
		Payment p = new Payment();
		p.setId(5);
		p.setO(o);
		Integer id = paymentService.makePayment(p);
		assertEquals(id, p.getId());
	}
	
	
	
	

}
