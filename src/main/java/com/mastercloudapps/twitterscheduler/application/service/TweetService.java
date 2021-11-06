package com.mastercloudapps.twitterscheduler.application.service;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.usecase.tweet.FindAllTweetUseCase;
import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;
import com.mastercloudapps.twitterscheduler.domain.tweet.TweetPort;

@Component
public class TweetService implements FindAllTweetUseCase {

	private TweetPort tweetPort;
	
	public TweetService(TweetPort tweetPort) {
		
		this.tweetPort = tweetPort;
	}
	
	@Override
	public Collection<Tweet> findAll() {
		
		return tweetPort.findAll();
	}

}
