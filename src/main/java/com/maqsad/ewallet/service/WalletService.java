package com.maqsad.ewallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maqsad.ewallet.entity.Wallet;
import com.maqsad.ewallet.repository.WalletRepository;

@Service
public class WalletService {
	@Autowired
	private WalletRepository walletRepository;
	
	
	/*
	 * this method will return the balance according to the userId. If the user is
	 * not registered, the repository method will throw an exception and this method will
	 * return a negative value
	 */
	public double getBalance(String userId) {
		double balance=-1;
		try {
			balance = walletRepository.getWalletByUserId(userId).getBalance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return balance;       
	}

	
	/*
	 * this will add amount to the user's wallet. In case of any error the amount
	 * will not be added and the service method will return false.
	 */	
	public boolean deposit(String userId, double amountToAdd) {
		try {
			Wallet wallet = walletRepository.getWalletByUserId(userId);
			wallet.setBalance(wallet.getBalance() + amountToAdd);
			walletRepository.updateWallet(wallet);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/*
	 * this will be used to make in app purchases. In case of not enough balance or
	 * invalid userId or any other error this will return a message and no payment will
	 * be made.
	 */	
	public String makePayment(String userId, double paymentAmount) {
		try {
			Wallet wallet = walletRepository.getWalletByUserId(userId);
			double remainingBalance = wallet.getBalance() - paymentAmount;
			if(remainingBalance <= 0) {
				throw new IllegalArgumentException("Not sufficient balance");
			}
			wallet.setBalance(wallet.getBalance() - paymentAmount);
			walletRepository.updateWallet(wallet);
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Payment made successfully.";
	}
}
