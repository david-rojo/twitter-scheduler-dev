package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

	private static Logger logger = LoggerFactory.getLogger(TwitterClient.class);
	
	@Value("${twitter.debug}")
	private static boolean debug;
	
	@Value("${twitter.oauth.consumerKey}")
	private static String consumerKey;
	
	@Value("${twitter.oauth.consumerSecret}")
	private static String consumerSecret;
	
	@Value("${twitter.oauth.accessToken}")
	private static String accessToken;
	
	@Value("${twitter.oauth.accessTokenSecret}")
	private static String accessTokenSecret;
	
	@Value("${TWITTER_CONSUMER_KEY}")
	private static String consumerKeyOS;
	
	public static Twitter getTwitterInstance() {
		
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(debug)
	    	.setOAuthConsumerKey(consumerKey)
	    	.setOAuthConsumerSecret(consumerSecret)
	    	.setOAuthAccessToken(accessToken)
	    	.setOAuthAccessTokenSecret(accessTokenSecret);

	    logger.info("AccessToken --> " + accessToken);
	    logger.info("ConsumerKey --> " + consumerKey);
	    logger.info("ConsumerKeyOS --> " + consumerKeyOS);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    return tf.getInstance();
	}
}
