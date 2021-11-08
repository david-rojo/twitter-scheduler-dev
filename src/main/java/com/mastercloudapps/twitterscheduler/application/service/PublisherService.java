package com.mastercloudapps.twitterscheduler.application.service;

import static java.util.function.Function.identity;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetRequest;
import com.mastercloudapps.twitterscheduler.application.service.twitter.TwitterService;
import com.mastercloudapps.twitterscheduler.application.usecase.PublishPendingTweetsUseCase;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetPort;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;
import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;
import com.mastercloudapps.twitterscheduler.domain.tweet.TweetPort;

@Component
public class PublisherService implements PublishPendingTweetsUseCase {

	private TwitterService twitterService;
	
	private PendingTweetPort pendingTweetPort;
	
	private TweetPort tweetPort;
	
	public PublisherService(final TwitterService twitterService, final PendingTweetPort pendingTweetPort, 
			final TweetPort tweetPort) {
		
		this.twitterService = twitterService;
		this.pendingTweetPort = pendingTweetPort;
		this.tweetPort = tweetPort;
	}

	@Override
	public void publishPendingTweets(Instant publishUntil) {
		
		List<PendingTweet> pendingTweets = pendingTweetPort.findPendingForPublish(publishUntil).stream()
				.map(identity())
				.collect(Collectors.toList());
		
		pendingTweets.forEach(pending -> {
			
			final var publishedTweet = twitterService.publish(PublishTweetRequest.builder()
					.message(pending.message().message())
					.build());
			
			publishedTweet.ifPresent(published -> {
				
				pendingTweetPort.delete(pending.id().id());
				
				tweetPort.create(Tweet.builder()
						.id(published.getId())
						.message(published.getMessage())
						.requestedPublicationDate(pending.publicationDate().instant())
						.publishedAt(published.getPublishedAt())
						.createdAt(NullableInstant.now().instant())
						.build());				
			});
		});		
	}

}
