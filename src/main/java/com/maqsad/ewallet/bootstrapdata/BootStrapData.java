package com.maqsad.ewallet.bootstrapdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.maqsad.ewallet.entity.Wallet;
import com.maqsad.ewallet.service.WalletService;

@Component
public class BootStrapData implements CommandLineRunner{
	
		@Autowired
	 	private WalletService walletService;
	 	
		@Override
	 	public void run(String... args) throws Exception {

	        System.out.println("Started in Bootstrap");

	        Wallet wallet = new Wallet();
	        wallet.setUserId("100");
	        wallet.setBalance(50);
	        
	        try {
	        	System.out.println(""+walletService.getBalance("100"));
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
	        }
	        
	        
			/*
			 * if(!walletService.deposit("50", 50)) {
			 * System.out.println("Error: cannot add credit"); } else {
			 * System.out.println("Credit added successfully. Your balance is: " +
			 * walletService.getBalance("100")); } if(!walletService.makePayment("50", 30))
			 * { System.out.println("No sufficeient balance"); } else {
			 * System.out.println("Wallet Balance: " +walletService.getBalance("100")); }
			 */
	        
	        /*
			 * Author eric = new Author("Eric", "Evans"); Book ddd = new
			 * Book("Domain Driven Design", "123123"); eric.getBooks().add(ddd);
			 * ddd.getAuthors().add(eric);
			 * 
			 * ddd.setPublisher(publisher); publisher.getBooks().add(ddd);
			 * 
			 * authorRepository.save(eric); bookRepository.save(ddd);
			 * publisherRepository.save(publisher);
			 * 
			 * Author rod = new Author("Rod", "Johnson"); Book noEJB = new
			 * Book("J2EE Development without EJB", "3939459459");
			 * rod.getBooks().add(noEJB); noEJB.getAuthors().add(rod);
			 * 
			 * noEJB.setPublisher(publisher); publisher.getBooks().add(noEJB);
			 * 
			 * authorRepository.save(rod); bookRepository.save(noEJB);
			 * publisherRepository.save(publisher);
			 */
	    }

}
