package com.revature.data;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueBicycleException;

public interface BicycleDAO extends GenericDAO<Bicycle>{
	
	//Create available in generic DAO
	
	//Read
    public Set<Bicycle> getAvailableBicycles();
	
	//Update available in generic DAO

	//Delete available in generic DAO

	
}
