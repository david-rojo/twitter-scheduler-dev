package com.mastercloudapps.twitterscheduler.application.service;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.operation.CreatePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.application.model.operation.DeletePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.DeletePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.FindAllPendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetId;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetPort;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

@Component
public class PendingTweetService implements CreatePendingTweetUseCase, DeletePendingTweetUseCase, 
		FindAllPendingTweetUseCase {

	private PendingTweetPort pendingTweetPort;
	
	
	public PendingTweetService(PendingTweetPort pendingTweetPort) {
		
		this.pendingTweetPort = pendingTweetPort;
	}
	
	@Override
	public PendingTweet createPendingTweet(CreatePendingTweetOperation request) {
		
		PendingTweet pendingTweet = PendingTweet.builder()
				.id(PendingTweetId.defaultValue())
				.message(request.getMessage())
				.publicationDate(request.getPublicationDate().instant())
				.createdAt(NullableInstant.now().instant())
				.build();
		
		return pendingTweetPort.createPendingTweet(pendingTweet);
	}

	@Override
	public void deletePendingTweet(DeletePendingTweetOperation request) {
		
		pendingTweetPort.deletePendingTweet(request.getId());
		
	}

	@Override
	public Collection<PendingTweet> findAll() {
		
		return pendingTweetPort.findAll();
	}

}
