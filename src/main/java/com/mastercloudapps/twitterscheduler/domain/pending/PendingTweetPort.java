package com.mastercloudapps.twitterscheduler.domain.pending;

import java.util.Collection;
import java.util.Optional;

public interface PendingTweetPort {

	public PendingTweet createPendingTweet(PendingTweet pendingTweet);
	
	public void deletePendingTweet(Long id);
	
	public Collection<PendingTweet> findAll();
	
	public Optional<PendingTweet> findOne(Long id);

}
