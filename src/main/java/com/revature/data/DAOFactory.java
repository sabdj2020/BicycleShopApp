package com.revature.data;

public class DAOFactory {
	
    public static UserDAO getUserDAO() {
    	return new UserPostgres();
    }
    
    public static BicycleDAO getBicycleDAO() {
        return new BicyclePostgres();
    }
    
    public static OfferDAO getOfferDAO() {
        return new OfferPostgres();
    }
    
    public static PaymentDAO getPaymentDAO() {
        return new PaymentPostgres();
    }
    
    
    
}
