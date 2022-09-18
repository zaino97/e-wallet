package com.maqsad.ewallet.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.maqsad.ewallet.entity.Wallet;


@Repository
public class WalletRepository {

    @Autowired
    private DynamoDBMapper mapper;

	/* This method will only be called once, when the user creates an account.
	 *  If the user already exists this will return false */
    public boolean addWallet(Wallet wallet) {
        if(this.getWalletAlreadyExists(wallet.getUserId())) {
        	return false;
        }
    	mapper.save(wallet);
        return true;
    }

    public Wallet findWalletByWalletId(String walletId) throws Exception {
        return mapper.load(Wallet.class, walletId);
    }

    
    public String deleteWallet(Wallet wallet) {
    	String toReturn = "wallet removed !!";
    	try {
    		mapper.delete(wallet);
		} catch (Exception e) {
			e.printStackTrace();
			toReturn=e.getMessage();
		}
    	return toReturn;
    }

    public String updateWallet(Wallet wallet) {
    	String toReturn = "record updated ...";
    	try {
    		mapper.save(wallet, buildExpression(wallet));
		} catch (Exception e) {
			e.printStackTrace();
			toReturn=e.getMessage();
		}
    	return toReturn;
    }
    
    public Wallet getWalletByUserId(String userId) throws Exception {
    	
    	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("userId", new Condition()                                           
           .withComparisonOperator(ComparisonOperator.EQ)                                                
           .withAttributeValueList(new AttributeValue().withS(userId)));
        List<Wallet> scanResult = mapper.scan(Wallet.class, scanExpression);
        
        if(scanResult != null && scanResult.size()>0) {
        	return scanResult.get(0);
        }
        throw new IllegalArgumentException("Error: Wallet doesn't exist!");
    }
    
    public boolean getWalletAlreadyExists(String userId) {
    	try {
			this.getWalletByUserId(userId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
        
    }

    
    private DynamoDBSaveExpression buildExpression(Wallet wallet) {
    
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        try {
	        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
	        expectedMap.put("walletId", new ExpectedAttributeValue(new AttributeValue().withS(wallet.getWalletId())));
	        dynamoDBSaveExpression.setExpected(expectedMap);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return dynamoDBSaveExpression;
    }
}
