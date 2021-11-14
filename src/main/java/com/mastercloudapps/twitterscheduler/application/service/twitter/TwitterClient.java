package com.mastercloudapps.twitterscheduler.application.service.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterClient {
	
	@Value("${twitter.oauth.consumerKey}")
	private String consumerKey;
	
	@Value("${twitter.oauth.consumerSecret}")
	private String consumerSecret;
	
	@Value("${twitter.oauth.accessToken}")
	private String accessToken;
	
	@Value("${twitter.oauth.accessTokenSecret}")
	private String accessTokenSecret;
	
	private Twitter twitter;
	
	public TwitterClient() {
		
	}
	
	public Twitter getAuthClient() {
		
		if (twitter == null) {
			twitter = new TwitterFactory().getInstance();
			
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			
			AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
			twitter.setOAuthAccessToken(oathAccessToken);
		}
		
		return twitter;
	}

}
