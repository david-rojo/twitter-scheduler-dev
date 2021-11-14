package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class TwitterConnection {

	private static Logger logger = LoggerFactory.getLogger(TwitterConnection.class);
	
	private static Twitter twitter;
	
	public static Twitter getInstance() {
		
	    if (twitter == null) {
	    	twitter = new TwitterFactory().getInstance();
	    }
	    return twitter;
	}
	
	public TwitterConnection() {}
	
}
