package com.maqsad.ewallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maqsad.ewallet.entity.Wallet;
import com.maqsad.ewallet.repository.WalletRepository;

@SpringBootApplication
@RestController
public class WalletController {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@RequestMapping("/")
    public String welcome(){
    	return "Welcome to Maqsad's ewallet. "
    			+ "Kindly run the test cases and see the service file you can "
    			+ "also see the outputs in console.";
    }


}
