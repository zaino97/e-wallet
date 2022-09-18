package com.maqsad.ewallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maqsad.ewallet.entity.Wallet;
import com.maqsad.ewallet.repository.WalletRepository;
import com.maqsad.ewallet.service.WalletService;

@SpringBootTest
public class WalletServiceTests {

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletRepository walletRepository;
	
    @AfterEach
    void teardown() {
    	
    	walletRepository.deleteWallet(new Wallet("testId", "userTest", 20));
    }
    
    @Test 
	void balanceTestNormal() {
    	walletRepository.addWallet(new Wallet("testId", "userTest", 20));
    	assertEquals(20, walletService.getBalance("userTest"));
    }
	
	@Test 
	void balanceTestWrongUserId() { 
		walletRepository.addWallet(new Wallet("testId", "userTest", 20));
		assertEquals(-1, walletService.getBalance("-1")); 
	}
	
	@Test 
	void makePaymentTestNormal() { 
		walletRepository.addWallet(new Wallet("testId", "userTest", 20));
		assertEquals("Payment made successfully.", walletService.makePayment("userTest", 10)); 
	}
	
	@Test 
	void makePaymentTestNotSufficientBalance() { 
		walletRepository.addWallet(new Wallet("testId", "userTest", 20));
		assertEquals("Not sufficient balance", walletService.makePayment("userTest", 21)); 
	}
	
	@Test 
	void depositAmountTest() { 
		walletRepository.addWallet(new Wallet("testId", "userTest", 20));
		assertTrue(walletService.deposit("userTest", 50)); 
	}
	
	@Test 
	void depositAmountWrongUserIdTest() { 
		walletRepository.addWallet(new Wallet("testId", "userTest", 20));
		assertFalse(walletService.deposit("-1", 50)); 
	}
	
}
