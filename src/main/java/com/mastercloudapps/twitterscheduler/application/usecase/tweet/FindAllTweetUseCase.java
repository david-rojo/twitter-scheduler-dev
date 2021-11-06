package com.mastercloudapps.twitterscheduler.application.usecase.tweet;

import java.util.Collection;

import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;

public interface FindAllTweetUseCase {

	public Collection<Tweet> findAll();
}
