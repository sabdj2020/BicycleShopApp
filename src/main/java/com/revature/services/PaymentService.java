package com.revature.services;


import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;
import com.revature.exceptions.BicycleNoLongerAvailableException;

public interface PaymentService {
	
	public Integer makePayment(Payment pay) throws Exception;

	public Set<Payment> getMyPaymens(User loggedInUser);

	public Set<Payment> getMyRemainingPaymens(User loggedInUser);

	public Set<Payment> getAllPayments();

}
