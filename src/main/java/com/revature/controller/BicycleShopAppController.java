package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.OfferStatus;
import com.revature.beans.Payment;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.exceptions.BicycleNoLongerAvailableException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BicycleService;
import com.revature.services.BicycleServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PaymentService;
import com.revature.services.PaymentServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BicycleShopAppController {
	private static Logger log = Logger.getLogger(BicycleShopAppController.class);
	private static UserService userService = new UserServiceImpl();
	private static BicycleService bicycleService = new BicycleServiceImpl();
	private static OfferService offerService = new OfferServiceImpl();
	private static PaymentService paymentService = new PaymentServiceImpl();

	private static Scanner scanner;
	public static void main(String[] args) throws Exception {
		scanner = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while(userActive) {
			System.out.println("welcome to the BicycleShop app");
			User loggedInUser = null;
			
			while(loggedInUser==null) {
				System.out.println("what is your choice: \n 1: Register \n 2: Login \n other: Quit");
				String choice = scanner.nextLine();
				switch (choice) {
				case "1": 
					log.info("account registration");
					loggedInUser=registerUser();
					break;
					
				case "2":
					log.info("Login to App");
					loggedInUser=loginUser();
					break;
					
				default:
					log.info("Quit the App");
					userActive=false;
					break mainLoop;	
				}	
			}
			
			childLoop: while(true) {
				System.out.println("choose from the set of provided functionalities");
				System.out.println(" 1. View available Bicycles");
				if ("Customer".equals(loggedInUser.getRole().getName()))
					System.out.println(" 2. View my Bicycles \n 3. Make an offer for a bicycle \n 4. Make payment \n 5. view all my payments \n 6. view my remaining payment\n Log out");
				else if ("Employee".equals(loggedInUser.getRole().getName()))
					System.out.println(" 7. Manage Bicycles \n 8. Manage Offers \n Other. Log out");
				else if ("Manager".equals(loggedInUser.getRole().getName()))
					System.out.println(" 7. Manage Bicycles \n 8. Manage Offers \n 9. Manage Employee Account \n Other. Log out");
				
				String choice = scanner.nextLine();
				switch(choice) {
				case "1":
					//view available bicycles
					loggedInUser = viewAvailableBicycles(loggedInUser);
					break;
					
				case "2":
					//view Customer's bicycles
					loggedInUser = viewCustomerBicycles(loggedInUser);
					break;
					
				case "3":
					//make offer on a bicycles
					loggedInUser = makeOfferBicycles(loggedInUser);
					break;
					
				case "4":
					loggedInUser = makePayment(loggedInUser);
					break;
					
				
				case "5":
					loggedInUser = viewAllMyPayments(loggedInUser);
					break;
					
				case "6":
					loggedInUser = viewRemainingPayments(loggedInUser);
					break;
					
				
				case "7":
					//Bicycle management by the employee
					loggedInUser = manageBicycles(loggedInUser);
					break;
					
				case "8":
					//Bicycle management by the employee
					loggedInUser = manageOffers(loggedInUser);
					break;
					
				case "9":
					//Bicycle management by the employee
					loggedInUser = manageEmployeeAccount(loggedInUser);
					break;
				
				default:
					System.out.println("good by");
					log.info("logout");
					break childLoop;
					
			}	
			
		}
	}
		
		scanner.close();
}

	private static User viewRemainingPayments(User loggedInUser) {
		// TODO Auto-generated method stub
		Set<Payment> myPayment = paymentService.getMyRemainingPaymens(loggedInUser);
		if (!myPayment.isEmpty()) {
		for (Payment p : myPayment) {
			System.out.println("  Payment ID: "+p.getId() +"    Amount: "+p.getAmount()+"   Offer Id:"+p.getO().getId()+"   Bicycleref:"+p.getO().getBicycle().getReference()+"  Remaining Payments for this offer : "+p.getRemainingPayment()+ "  number of weeks : "+p.getWeeksRemaining());
		}
		}
		return loggedInUser;
	}

	private static User manageOffers(User loggedInUser) {
		// TODO Auto-generated method stub
		System.out.println(" 1. Accept a pending offer \n 8. Reject a pending offer \n Other. Log out");
		String choice=scanner.nextLine();
		switch (choice) {
		case "1":{
			for (Bicycle b : bicycleService.getAvailableBicycles()) {
				System.out.println(b);
			}
			System.out.println("Enter the ID bike for which you want to see offers");
			Bicycle b = bicycleService.getBicycleById(Integer.valueOf(scanner.nextLine()));
			Bicycle bicycleToBuy = b;
			System.out.println(bicycleToBuy);
			if (b != null) {
				System.out.println("Display the offers on a bicycle with reference" + b.getReference());
				
						
						for (Offer o : offerService.getOffersByBicycleId(b.getId())) {
							System.out.println(o);
						}	
					
				}
			
			System.out.println("Enter the Offer ID you would like to accept");
			Offer o = offerService.getOfferById(Integer.valueOf(scanner.nextLine()));
			if (o != null) {
				
				offerService.acceptOffer(o, b);
				System.out.println("the Offer with the amount" +o.getPrice()+"on the bicycle" + b.getReference()+"has been accepted!");
				
			}
			
		}
		
		case "2":{
			
			for (Bicycle b : bicycleService.getAvailableBicycles()) {
				System.out.println(b);
			}
			System.out.println("Enter the ID bike for which you want to see offers");
			Bicycle b = bicycleService.getBicycleById(Integer.valueOf(scanner.nextLine()));
			Bicycle bicycleToBuy = b;
			System.out.println(bicycleToBuy);
			if (b != null) {
				System.out.println("Display the offers on a bicycle with reference" + b.getReference());
				
						
						for (Offer o : offerService.getOffersByBicycleId(b.getId())) {
							System.out.println(o);
						}	
					
				}
			
			System.out.println("Enter the Offer ID you would like to reject");
			Offer o = offerService.getOfferById(Integer.valueOf(scanner.nextLine()));
			if (o != null) {
				
				offerService.rejectOffer(o);
				System.out.println("the Offer with the amount: " +o.getPrice()+"on the bicycle: " + b.getReference()+" has been rejected!");
				
			}
		}
		}

		return loggedInUser;
	}

	private static User manageEmployeeAccount(User loggedInUser) {
		// TODO Auto-generated method stub
		
		Boolean doAgain = true;
		while (doAgain) {
			User account = new User();
			System.out.println("Enter a username of the Employee: ");
			account.setUsername(scanner.nextLine());
			
			System.out.println("Enter a password of the Employee: ");
			account.setPassword(scanner.nextLine());
			
			Role userRole = new Role();
			userRole.setId(1);
			userRole.setName("Employee");
			// TODO get this from the database
			account.setRole(userRole);
			
			System.out.println("check the information please");
			System.out.println("Username: " + account.getUsername() + 
					" Password: " + account.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				log.debug("Submitting new emplyee to the database...");
				try {
					account.setId(userService.addUser(account));
					log.debug(account);
					System.out.println("Confirmed");
					System.out.println("would you like to add another employee yes / no");
					choice = scanner.nextLine();
					if(choice.equals("no")) {
						doAgain=false;
					}
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken. Try again!");
					log.warn("User tried to register with a non-unique username.");
				}
				break;
			case "2":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
			}
		}
		return loggedInUser;
	
	}

	private static User viewAllMyPayments(User loggedInUser) {
		// TODO Auto-generated method stub
		
		log.info("call get my payment");

		Set<Payment> myPayment = paymentService.getMyPaymens(loggedInUser);
		if (!myPayment.isEmpty()) {
		for (Payment p : myPayment) {
			System.out.println("  Payment ID: "+p.getId() +"    Amount: "+p.getAmount()+"   Offer Id:"+p.getO().getId()+"   Bicycleref:"+p.getO().getBicycle().getReference()+"  Remaining Payments for this offer : "+p.getRemainingPayment());
		}
		}
		return loggedInUser;
		
	
	}

	private static User makePayment(User loggedInUser) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(" the list of you accepted offers");
		log.info("accepted offers");

		for (Offer o : offerService.getAcceptedOffers(loggedInUser)) {
			System.out.println(o);
		}	
		
		System.out.println(" chose from the list the accepted offer for which you wanna make payment");
		Offer o = offerService.getOfferById(Integer.valueOf(scanner.nextLine()));
		if (o != null) {
			
			Payment pay = new Payment();
			System.out.println(" how do you want to pay : \n 1- Full Payment \n 2-Weekly Payment");
			String choice=scanner.nextLine();
			
			switch(choice) {
			case "1":{
				
				log.info("make a full payment");

				pay.setAmount(o.getPrice());
				pay.setO(o);
				
				paymentService.makePayment(pay);
				break;
			}
			
			case "2":{
				log.info("make a weekly payment");
				System.out.println("how many weeks do you want");
				int numberOfWeeks = Integer.valueOf(scanner.nextLine());
				int weeksRemaining = numberOfWeeks-1;
				System.out.println("your weekly payment is: \n"+ o.getPrice()/numberOfWeeks);
				float amount = o.getPrice()/numberOfWeeks;
				pay.setAmount(amount);
				pay.setO(o);
				pay.setWeeksRemaining(weeksRemaining);
				paymentService.makePayment(pay);
				
				System.out.println("you made a payment for the offer " + o.getId()+ " on the bike " + o.getBicycle().getReference());
				System.out.println("your weekly payment is: \n"+ o.getPrice()/numberOfWeeks);
				System.out.println("you paid the amount of"+ o.getPrice()/numberOfWeeks +"left");
				System.out.println("you have " + weeksRemaining + "left");

				}
			}
			
			
			
			
		}
		
		return loggedInUser;
	}

	private static User makeOfferBicycles(User loggedInUser) throws Exception {
		// TODO Auto-generated method stub
		 Set<Bicycle> availableBicycles = bicycleService.getAvailableBicycles();
			
			for (Bicycle b : availableBicycles) {
				System.out.println(b);
			}
			System.out.println("which bicycle from the list you want to make the offer ?");
			Offer offer = new Offer();
		
			 while (true) {
					
					String choice = scanner.nextLine();
					Bicycle b = bicycleService.getBicycleById(Integer.valueOf(choice));
													
					if (b != null && b.getStatus().getName().equals("Available")) {
						System.out.println(b);
						System.out.println("could you confirm that you want to make an offer on " + b.getReference() + "? 1 for yes, other for no");
						choice = scanner.nextLine();
						if ("1".equals(choice)) {
								System.out.println("enter the amount you want to offer for the bicycle");
								offer.setPrice(Float.valueOf(scanner.nextLine()));
								OfferStatus os = new OfferStatus();
								os.setId(1);
								os.setName("Pending");
								offer.setOfferStatus(os);
								offer.setBicycle(b);
								offer.setUser(loggedInUser);
								
							try {	
								offerService.makeOffer(offer);
							} catch (BicycleNoLongerAvailableException e) {
								// TODO this needs to be handled better
								System.out.println("this Bicycle is sold...");
								break;
							}
							System.out.println("You made an offer on the bicycle with the reference" + b.getReference() + ".");
							// get the person with their updated bicycle set
							loggedInUser = userService.getUserById(loggedInUser.getId());
							break;
						} else {
							System.out.println("Okay, do you want to make another offer? 1 for yes, other for no");
							choice = scanner.nextLine();
							if (choice != "1")
								break;
						}
					} else {
						System.out.println("Sorry, that's not an available bicycle. Do you want to try again?"
								+ " 1 for yes, other for no");
						choice = scanner.nextLine();
						if (choice != "1") {
							System.out.println("that's fine, chose from your menu what you wanna do! ");
							break;
						}
					}
				
			} 
			
			
			
			return loggedInUser;
			
	}
	
	private static User registerUser() {
		while (true) {
			User account = new User();
			System.out.println("Enter a username: ");
			account.setUsername(scanner.nextLine());
			
			System.out.println("Enter a password: ");
			account.setPassword(scanner.nextLine());
			
			Role userRole = new Role();
			// TODO get this from the database
			account.setRole(userRole);
			
			System.out.println("check the information please");
			System.out.println("Username: " + account.getUsername() + 
					" Password: " + account.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			String input = scanner.nextLine();
			switch (input) {
			case "1":
				log.debug("Submitting new user to the database...");
				try {
					account.setId(userService.addUser(account));
					log.debug(account);
					System.out.println("Confirmed. Welcome to CatApp.");
					return account;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken. Try again!");
					log.warn("User tried to register with a non-unique username.");
				}
				break;
			case "2":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
			}
		}
	}
	private static User loginUser() {
		
		while (true) {
			System.out.println("Enter username: ");
			String username = scanner.nextLine();
			System.out.println("Enter password: ");
			String password = scanner.nextLine();
			
			User user = userService.getUserByUsername(username);
			if (user == null) {
				log.debug("User entered a username that doesn't exist.");
				System.out.println("Nobody exists with that username.");
			} else if (user.getPassword().equals(password)) {
				log.debug("User logged in successfully.");
				log.debug(user);
				System.out.println("Welcome back!");
				return user;
			} else {
				log.debug("User entered an incorrect password.");
				System.out.println("That password is incorrect.");
			}
			System.out.println("try again? 1 for yes, other for no.");
			String input = scanner.nextLine();
			if (!("1".equals(input)))
				return null;
		}
	}
	
	private static User manageBicycles(User loggedInUser) throws Exception {
		// TODO Auto-generated method stub
		if (!(loggedInUser.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage the bicycles:\n 1. View available Bicycles\n 2. Add a new Bicycles\n 3. Edit a Bicycle\n 4. Remove a Bicycle from the shop \n 5. View all payments \n other. Quit");
			String choice = scanner.nextLine();
			
			
			if ("1".equals(choice)){
				Set<Bicycle> availableBicycles = bicycleService.getAvailableBicycles();
				log.debug("Print the available bicycles...");
				
				for (Bicycle b : availableBicycles) {
					System.out.println(b);
				}
				
			}else if ("2".equals(choice)) {
				Bicycle bicycleToAdd = new Bicycle();
				System.out.println("Enter a reference: ");
				bicycleToAdd.setReference(scanner.nextLine());
				System.out.println("Enter a color: ");
				bicycleToAdd.setColor(scanner.nextLine());
				
				System.out.println("Enter a size: ");
				bicycleToAdd.setSize(Float.valueOf(scanner.nextLine()));
				System.out.println("Enter a Price: ");
				bicycleToAdd.setPrice(Float.valueOf(scanner.nextLine()));
				System.out.println("Enter a brand: ");
				bicycleToAdd.setBrand(scanner.nextLine());
				System.out.println("Enter a description: ");
				bicycleToAdd.setDescription(scanner.nextLine());
				
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				bicycleToAdd.setStatus(status);
				System.out.println(bicycleToAdd);
				
				System.out.println("Look good? 1 to confirm, other to start over");
				choice = scanner.nextLine();
				if ("1".equals(choice)) {	
					log.debug("call the add bicycle service...");
					bicycleToAdd.setId(bicycleService.addBicycle(bicycleToAdd));
					System.out.println("You successfully added " + bicycleToAdd.getReference() + "!");
					log.info("bicycle added successfully");
				}
			}else if ("3".equals(choice)) {
				for (Bicycle b : bicycleService.getAvailableBicycles()) {
					System.out.println(b);
				}
				System.out.println("Enter the ID of the Bicycle that you would like to update");
				log.debug("call the get bicycle by id  service...");
				Bicycle b = bicycleService.getBicycleById(Integer.valueOf(scanner.nextLine()));
				
				log.info("bicycle got by id successfully");

				Bicycle bicycleToUpdate = b;
				if (b != null) {
					log.info("edit the bicycle");
					System.out.println("Editing the bicycle with the reference " + b.getReference());
					System.out.println("Current changes:\nReference: " + bicycleToUpdate.getReference()
							+ " Brand: " + bicycleToUpdate.getBrand());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Reference\n2. Price\n3. Save changes\nother. Cancel");
						choice = scanner.nextLine();
						switch (choice) {
						case "1":
							System.out.println("Enter new Reference: ");
							b.setReference(scanner.nextLine());
							break;
						case "2":
							System.out.println("Enter new Price: ");
							b.setPrice(Float.valueOf(scanner.nextLine()));
							break;
						case "3":
							bicycleService.updateBicycle(bicycleToUpdate);
							System.out.println("The bicycle " + bicycleToUpdate.getReference() + " was updated successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			
			}else if ("4".equals(choice)) {
				for (Bicycle b : bicycleService.getAvailableBicycles()) {
					System.out.println(b);
				}
				System.out.println("Enter the ID of the Bicycle that you would like to delete");
				Bicycle b = bicycleService.getBicycleById(Integer.valueOf(scanner.nextLine()));
				Bicycle bicycleToDelete = b;
				if (b != null) {
					log.info("delete the bicycle");

					System.out.println("Deleting the bicycle with the reference " + b.getReference());
					
					
							bicycleService.deleteBicycle(bicycleToDelete);
							System.out.println("The bicycle " + bicycleToDelete.getReference() + " was deleted successfully.");
						
						
					}
			}else if ("5".equals(choice)) {
				for (Payment b : paymentService.getAllPayments()) {
					System.out.println(b);
				}
						
					
			}
			
		}
		
	
	
		}

	private static User viewCustomerBicycles(User loggedInUser) {
		// TODO Auto-generated method stub
		Set<Bicycle> myBicycles = bicycleService.getMyBicycles(loggedInUser);
		if (!myBicycles.isEmpty()) {
		for (Bicycle b : myBicycles) {
			System.out.println(b);
		}
		}
		return loggedInUser;
	}

	private static User viewAvailableBicycles(User loggedInUser) throws Exception {
		// TODO Auto-generated method stub
        Set<Bicycle> availableBicycles = bicycleService.getAvailableBicycles();
		
		for (Bicycle b : availableBicycles) {
			System.out.println(b);
		}
		System.out.println("Would you like to make an offer on any specific Bicycle? 1 for yes, other for no");
		String choice = scanner.nextLine();
		Offer offer = new Offer();
		if ("1".equals(choice)) {
			while (true) {
				
				System.out.println("chose any Bicycle ID from the list of the available Bicycle");
				choice = scanner.nextLine();
				Bicycle b = bicycleService.getBicycleById(Integer.valueOf(choice));
				System.out.println(b);
												
				if (b != null && b.getStatus().getName().equals("Available")) {
					System.out.println(b);
					System.out.println("could you confirm that you want to make an offer on " + b.getReference() + "? 1 for yes, other for no");
					choice = scanner.nextLine();
					if ("1".equals(choice)) {
							System.out.println("enter the amount you want to offer for the bicycle");
							offer.setPrice(Float.valueOf(scanner.nextLine()));
							OfferStatus os = new OfferStatus();
							os.setId(1);
							os.setName("Pending");
							offer.setOfferStatus(os);
							offer.setBicycle(b);
							offer.setUser(loggedInUser);
							
						try {	
							offerService.makeOffer(offer);
						} catch (BicycleNoLongerAvailableException e) {
							// TODO this needs to be handled better
							System.out.println("this Bicycle is sold...");
							break;
						}
						System.out.println("You did it! You made an offer on the bicycle with the reference" + b.getReference() + ".");
						// get the person with their updated bicycle set
						loggedInUser = userService.getUserById(loggedInUser.getId());
						break;
					} else {
						System.out.println("Okay, did you want to make another offer? 1 for yes, other for no");
						choice = scanner.nextLine();
						if (choice != "1")
							break;
					}
				} else {
					System.out.println("Sorry, that's not an available bicycle. Do you want to try again?"
							+ " 1 for yes, other for no");
					choice = scanner.nextLine();
					if (choice != "1") {
						System.out.println("Okay, that's fine.");
						break;
					}
				}
			}
		} else {
			System.out.println("Okay, that's fine.");
		}
		
		
		
		return loggedInUser;
		
	}

	

}
