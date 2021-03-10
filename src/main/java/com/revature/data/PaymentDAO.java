package com.revature.data;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;

public interface PaymentDAO extends GenericDAO<Payment> {

	Set<Payment> getRemainingPayments(User loggedInUser);

	Set<Payment> getAllPay();
	
}
