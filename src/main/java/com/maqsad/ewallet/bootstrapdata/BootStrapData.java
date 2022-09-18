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
	        
	    }

}
