package com.mastercloudapps.twitterscheduler.application.service.twitter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetRequest;
import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetResponse;
import com.mastercloudapps.twitterscheduler.domain.exception.ServiceException;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Component
public class TwitterServiceImpl implements TwitterService {

	private static final String ERR_MSG_PUBLISH_TWEET = "Error publishing in Twitter";
	
	private static Twitter twitter = TwitterFactory.getSingleton();
	
	@Override
	public Optional<PublishTweetResponse> publish(PublishTweetRequest request) {
		
		try {
			StatusUpdate statusUpdate = new StatusUpdate(request.getMessage());
			Status status = twitter.updateStatus(statusUpdate);
			
			PublishTweetResponse response = PublishTweetResponse.builder()
					.id(status.getId())
					.message(statusUpdate.getStatus())
					.publishedAt(status.getCreatedAt().toInstant())
					.build();
			
			return Optional.of(response);
			
		} catch (Exception e) {
			throw new ServiceException(ERR_MSG_PUBLISH_TWEET, e);
		}		
	}

	
}
