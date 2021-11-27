package com.mastercloudapps.twitterscheduler.domain.pending;

import java.util.Collection;
import java.util.Optional;

public interface PendingTweetImagePort {

	public PendingTweetImage create(PendingTweetImage pendingTweetImage);
	
	public void delete(Long id);
	
	public Collection<PendingTweetImage> findAll();
	
	public Optional<PendingTweetImage> findOne(Long id);
	
	public Collection<PendingTweetImage> findByPendingTweetId(Long id);

}
