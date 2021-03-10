package com.revature.services;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;
import com.revature.data.DAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.PaymentDAO;

public class PaymentServiceImpl implements PaymentService {
	
        private PaymentDAO paymentDao;
	
	
	//constructor
		public PaymentServiceImpl() {
			paymentDao = DAOFactory.getPaymentDAO();
		}


	@Override
	public Integer makePayment(Payment pay) throws Exception {
		// TODO Auto-generated method stub
		return paymentDao.add(pay).getId();
	}


	@Override
	public Set<Payment> getMyPaymens(User loggedInUser) {
		// TODO Auto-generated method stub
		return paymentDao.getAll(loggedInUser);
	}


	@Override
	public Set<Payment> getMyRemainingPaymens(User loggedInUser) {
		// TODO Auto-generated method stub
		return paymentDao.getRemainingPayments(loggedInUser);
	}


	@Override
	public Set<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentDao.getAllPay();	}

}
