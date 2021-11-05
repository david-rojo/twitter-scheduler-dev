package com.mastercloudapps.twitterscheduler.domain.tweet;

import java.util.Collection;
import java.util.Optional;

import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;

public interface TweetPort {

	public Tweet create(Tweet tweet);
	
	public void delete(Long id);
	
	public Collection<PendingTweet> findAll();
	
	public Optional<PendingTweet> findOne(Long id);

}
