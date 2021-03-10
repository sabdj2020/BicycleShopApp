package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.OfferStatus;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.data.DAOFactory;
import com.revature.data.OfferDAO;
import com.revature.data.UserDAO;
import com.revature.exceptions.BicycleNoLongerAvailableException;

public class OfferServiceImpl implements OfferService {
	private OfferDAO offerDao;
	
	
	//constructor
		public OfferServiceImpl() {
			offerDao = DAOFactory.getOfferDAO();
		}

	@Override
	public Integer makeOffer(Offer o) throws BicycleNoLongerAvailableException,Exception {
		// TODO Auto-generated method stub
		
		return offerDao.add(o).getId();
		
		
		
	}

	
	@Override
	public void acceptOffer(Offer o, Bicycle b) {
		// TODO Auto-generated method stub
		offerDao.updateOffer(o,b);
		
	}

	@Override
	public void rejectOffer(Offer o) {
		// TODO Auto-generated method stub
		offerDao.updateOfferStatusRejected(o);
		
		
	}

	@Override
	public Set<Offer> getOffersByBicycleId(Integer id) {
		// TODO Auto-generated method stub
		return offerDao.getOffersByBicycleId(id);
	}

	@Override
	public Offer getOfferById(Integer id) {
		// TODO Auto-generated method stub
		return offerDao.getById(id);
	}

	@Override
	public Set<Offer> getAcceptedOffers(User u) {
		// TODO Auto-generated method stub
		return offerDao.getOfferByStatus(u);
		
	}


	

}
