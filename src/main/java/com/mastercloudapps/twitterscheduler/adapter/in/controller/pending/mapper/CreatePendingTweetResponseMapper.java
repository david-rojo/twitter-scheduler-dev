package com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.mapper;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.dto.PendingTweetResponse;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;

@Component
public class CreatePendingTweetResponseMapper {

	public PendingTweetResponse mapResponse(PendingTweet pendingTweet) {
		
		var responseBuilder = PendingTweetResponse
				.builder()
				.pendingTweetId(pendingTweet.id().id())
				.message(pendingTweet.message().message())
				.publicationDate(pendingTweet.publicationDate().getFormatted());
		
		return responseBuilder.build();
	}
}
