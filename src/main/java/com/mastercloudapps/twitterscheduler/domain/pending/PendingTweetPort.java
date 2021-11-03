package com.mastercloudapps.twitterscheduler.domain.pending;

public interface PendingTweetPort {

	public PendingTweet createPendingTweet(PendingTweet pendingTweet);
	
	public void deletePendingTweet(Long id);

}
