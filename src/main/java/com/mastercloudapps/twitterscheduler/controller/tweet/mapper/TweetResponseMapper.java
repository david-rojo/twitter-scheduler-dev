package com.mastercloudapps.twitterscheduler.controller.tweet.mapper;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetResponse;
import com.mastercloudapps.twitterscheduler.controller.tweet.dto.TweetResponse;
import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;

@Component
public class TweetResponseMapper {

	public TweetResponse mapResponse(Tweet tweet) {
		
		var responseBuilder = TweetResponse
				.builder()
				.id(tweet.id().id())
				.message(tweet.message().message())
				.requestedPublicationDate(tweet.requestedPublicationDate().getFormatted())
				.publishedAt(tweet.publishedAt().getFormatted())
				.createdAt(tweet.createdAt().getFormatted());
		
		return responseBuilder.build();
	}
}
