package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;

@Component
public class TwitterClient {
	
	@Value("${oauth.consumerKey}")
	private String consumerKey;
	
	@Value("${oauth.consumerSecret}")
	private String consumerSecret;
	
	@Value("${oauth.accessToken}")
	private String accessToken;
	
	@Value("${oauth.accessTokenSecret}")
	private String accessTokenSecret;
	
	private Twitter twitter;
	
	public TwitterClient() {
		twitter = TwitterConnection.getInstance();
	}
	
	public Twitter getAuthClient() {
		
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthAccessToken(oathAccessToken);
		
		return twitter;
	}

}
