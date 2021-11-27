package com.mastercloudapps.twitterscheduler.domain.tweet;

import java.util.Collection;
import java.util.Optional;

public interface TweetImagePort {

	public TweetImage create(TweetImage tweetImage);
	
	public void delete(Long id);
	
	public Collection<TweetImage> findAll();
	
	public Optional<TweetImage> findOne(Long id);
	
	public Collection<TweetImage> findByTweetId(Long id);

}
