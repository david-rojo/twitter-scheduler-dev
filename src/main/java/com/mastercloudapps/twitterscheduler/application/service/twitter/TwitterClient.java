package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

	private static Logger logger = LoggerFactory.getLogger(TwitterClient.class);
	
	@Value("${debug}")
	private static boolean debug;
	
	@Value("${oauth.consumerKey}")
	private static String consumerKey;
	
	@Value("${oauth.consumerSecret}")
	private static String consumerSecret;
	
	@Value("${oauth.accessToken}")
	private static String accessToken;
	
	@Value("${oauth.accessTokenSecret}")
	private static String accessTokenSecret;
	
	public static Twitter getTwitterInstance() {
		
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(debug)
	    	.setOAuthConsumerKey(consumerKey)
	    	.setOAuthConsumerSecret(consumerSecret)
	    	.setOAuthAccessToken(accessToken)
	    	.setOAuthAccessTokenSecret(accessTokenSecret);

	    logger.info("AccessToken --> " + accessToken);
	    logger.info("AccessTokenSecret --> " + accessTokenSecret);
	    logger.info("ConsumerKey --> " + consumerKey);
	    logger.info("ConsumerSecret --> " + consumerSecret);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    return tf.getInstance();
	}
}
