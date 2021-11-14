package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterConnection {

	private static Logger logger = LoggerFactory.getLogger(TwitterConnection.class);
	
	@Value("${debug}")
	private boolean debug;
	
	@Value("${twitter.oauth.consumerKey}")
	private String consumerKey;
	
	@Value("${twitter.oauth.consumerSecret}")
	private String consumerSecret;
	
	@Value("${twitter.oauth.accessToken}")
	private String accessToken;
	
	@Value("${twitter.oauth.accessTokenSecret}")
	private String accessTokenSecret;
	
	private static Twitter twitter;
	
	public static Twitter getInstance() {
		
	    if (twitter == null) {
	    	twitter = new TwitterFactory().getInstance();
	    }
	    return twitter;
	}
	
	public TwitterConnection() {}
	
}
