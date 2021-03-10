package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.User;

public interface OfferDAO extends GenericDAO<Offer>{
	
	Set<Offer> getOffersByBicycleId(Integer id);
	
	Set<Offer> getOfferByStatus(User u);

	void updateOfferStatusRejected(Offer o);

	void updateOffer(Offer o, Bicycle b);

}
