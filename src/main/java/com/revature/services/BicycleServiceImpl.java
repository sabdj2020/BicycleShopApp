package com.revature.services;

import java.sql.SQLException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Bicycle;
import com.revature.beans.User;
import com.revature.data.BicycleDAO;
import com.revature.data.DAOFactory;
import com.revature.data.UserDAO;
import com.revature.exceptions.NonUniqueBicycleException;

public class BicycleServiceImpl implements BicycleService {
	 private BicycleDAO bicycleDao;
	 private static Logger log = Logger.getLogger(BicycleServiceImpl.class);
	 
	 public BicycleServiceImpl() {
		    bicycleDao = DAOFactory.getBicycleDAO();
			
		}
    /*-----------------this is for the Create------------------*/
	@Override
	public Integer addBicycle(Bicycle b) throws Exception, SQLException {
		// TODO Auto-generated method stub
		return bicycleDao.add(b).getId();
		
	}
	
	
	/*-----------------this is for the Read------------------*/

	@Override
	public Set<Bicycle> getAvailableBicycles() {
		// TODO Auto-generated method stub
		return bicycleDao.getAvailableBicycles();	
		}
	
	@Override
	public Bicycle getBicycleById(Integer id) {
		// TODO Auto-generated method stub
		return bicycleDao.getById(id);
	}
	
	@Override
	public Set<Bicycle> getMyBicycles(User loggedInUser) {
		return bicycleDao.getAll(loggedInUser);
	}
	
	
	/*-----------------this is for the Update------------------*/

	@Override
	public Integer updateBicycle(Bicycle b) {
		// TODO Auto-generated method stub
		
			return bicycleDao.update(b).getId();
	}
	
	
	/*-----------------this is for the Delete------------------*/

	@Override
	public Integer deleteBicycle(Bicycle b) {
		// TODO Auto-generated method stub
		return bicycleDao.delete(b).getId();
		
	}

	

}
