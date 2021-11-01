package com.mastercloudapps.twitterscheduler.application.service;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.command.CreatePendingTweetRequest;
import com.mastercloudapps.twitterscheduler.application.port.in.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;

@Component
public class CreatePendingTweetService implements CreatePendingTweetUseCase {

	@Override
	public PendingTweet createPendingTweet(CreatePendingTweetRequest command) {
		// TODO Auto-generated method stub
		return null;
	}

}
