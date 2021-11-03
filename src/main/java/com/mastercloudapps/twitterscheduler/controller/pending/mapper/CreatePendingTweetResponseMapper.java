package com.mastercloudapps.twitterscheduler.controller.pending.mapper;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetResponse;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;

@Component
public class CreatePendingTweetResponseMapper {

	public PendingTweetResponse mapResponse(PendingTweet pendingTweet) {
		
		var responseBuilder = PendingTweetResponse
				.builder()
				.pendingTweetId(pendingTweet.id().id())
				.message(pendingTweet.message().message())
				.publicationDate(pendingTweet.publicationDate().getFormatted())
				.createdAt(pendingTweet.createdAt().getFormatted());
		
		return responseBuilder.build();
	}
}
