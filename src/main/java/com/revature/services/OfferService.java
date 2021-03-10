package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.exceptions.BicycleNoLongerAvailableException;

public interface OfferService {
	
	public Integer makeOffer(Offer o) throws BicycleNoLongerAvailableException, Exception;
	

	public Set<Offer> getOffersByBicycleId(Integer id);
	
	public Set<Offer> getAcceptedOffers(User u);
	
	public Offer getOfferById(Integer id);

	public void acceptOffer(Offer o, Bicycle b);
	
	public void rejectOffer(Offer o);


	
	

}
