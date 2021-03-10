package com.revature.services;

import java.sql.SQLException;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.User;
import com.revature.exceptions.BicycleNoLongerAvailableException;
import com.revature.exceptions.NonUniqueBicycleException;

public interface BicycleService {
	
    // Create
	public Integer addBicycle(Bicycle b) throws Exception, SQLException;
	
	//Read
  //public Set<Bicycle> getAllBicycles();
	public Set<Bicycle> getAvailableBicycles();
	public Bicycle getBicycleById(Integer id);
	public Set<Bicycle> getMyBicycles(User loggedInUser);
    
	//Update
	public Integer updateBicycle(Bicycle b);
	
	//Delete
	public Integer deleteBicycle(Bicycle b);
	

}
