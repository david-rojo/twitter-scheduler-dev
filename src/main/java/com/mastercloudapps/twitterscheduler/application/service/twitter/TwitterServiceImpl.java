package com.mastercloudapps.twitterscheduler.application.service.twitter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetRequest;
import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetResponse;
import com.mastercloudapps.twitterscheduler.domain.exception.ServiceException;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

@Component
public class TwitterServiceImpl implements TwitterService {

	private static final String ERR_MSG_PUBLISH_TWEET = "Error publishing in Twitter";
	
//	private static Twitter twitter = TwitterFactory.getSingleton();

	private Twitter twitter;
	
	public TwitterServiceImpl() {
		//this(TwitterFactory.getSingleton());
		this(TwitterClient.getTwitterInstance());
	}
	
	TwitterServiceImpl(Twitter twitter){

		this.twitter = twitter;
	}
	
	@Override
	public Optional<PublishTweetResponse> publish(PublishTweetRequest request) {
		
		try {
			StatusUpdate statusUpdate = new StatusUpdate(request.getMessage());
			Status status = twitter.updateStatus(statusUpdate);
			
			PublishTweetResponse response = PublishTweetResponse.builder()
					.id(status.getId())
					.url(this.getTweetUrl(status))
					.message(statusUpdate.getStatus())
					.publishedAt(status.getCreatedAt().toInstant())
					.build();
			
			return Optional.of(response);
			
		} catch (Exception e) {
			throw new ServiceException(ERR_MSG_PUBLISH_TWEET, e);
		}		
	}
	
	private String getTweetUrl(Status status) {
		
		return "https://twitter.com/" + status.getUser().getScreenName()
				+ "/status/" + status.getId();
	}

	
}
