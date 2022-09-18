package com.maqsad.ewallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


@Configuration
public class DynamoDBConfig {
	 public static final String SERVICE_ENDPOINT = "dynamodb.ap-northeast-1.amazonaws.com";
	    public static final String REGION = "ap-northeast-1";
	    public static final String ACCESS_KEY = "AKIA23HG3ODCPBLWDNGA";
	    public static final String SECRET_KEY = "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";

	    @Bean
	    public DynamoDBMapper mapper() {
	        return new DynamoDBMapper(amazonDynamoDBConfig());
	    }

	    private AmazonDynamoDB amazonDynamoDBConfig() {
	        return AmazonDynamoDBClientBuilder.standard()
	                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
	                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY))).build();
	    }
	
	

}
