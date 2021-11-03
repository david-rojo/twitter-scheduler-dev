package com.mastercloudapps.twitterscheduler.application.service;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.command.CreatePendingTweetRequest;
import com.mastercloudapps.twitterscheduler.application.model.command.DeletePendingTweetRequest;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.DeletePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetId;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetPort;

@Component
public class PendingTweetService implements CreatePendingTweetUseCase, DeletePendingTweetUseCase {

	private PendingTweetPort pendingTweetPort;
	
	
	public PendingTweetService(PendingTweetPort pendingTweetPort) {
		
		this.pendingTweetPort = pendingTweetPort;
	}
	
	@Override
	public PendingTweet createPendingTweet(CreatePendingTweetRequest request) {
		
		PendingTweet pendingTweet = PendingTweet.builder()
				.id(PendingTweetId.defaultValue())
				.message(request.getMessage())
				.publicationDate(request.getPublicationDate().instant())
				.build();
		
		return pendingTweetPort.createPendingTweet(pendingTweet);
	}

	@Override
	public void deletePendingTweet(DeletePendingTweetRequest request) {
		
		pendingTweetPort.deletePendingTweet(request.getId());
		
	}

}
