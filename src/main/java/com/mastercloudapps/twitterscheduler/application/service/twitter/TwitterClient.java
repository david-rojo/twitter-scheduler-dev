package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.springframework.beans.factory.annotation.Value;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

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
	
	public static Twitter getTwitterInstance() {
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(debug)
	    .setOAuthConsumerKey(consumerKey)
	    .setOAuthConsumerSecret(consumerSecret)
	    .setOAuthAccessToken(accessToken)
	    .setOAuthAccessTokenSecret(accessTokenSecret);

	    TwitterFactory tf = new TwitterFactory(cb.build());
	    return tf.getInstance();
	}
}
