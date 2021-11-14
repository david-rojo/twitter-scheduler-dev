package com.mastercloudapps.twitterscheduler.application.service.twitter;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetRequest;
import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetResponse;
import com.mastercloudapps.twitterscheduler.domain.exception.ServiceException;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterServiceImpl implements TwitterService {

	private static Logger logger = LoggerFactory.getLogger(TwitterServiceImpl.class);
	
	@Value("${twitter.oauth.consumerKey}")
	private String consumerKey;
	
	@Value("${twitter.oauth.consumerSecret}")
	private String consumerSecret;
	
	@Value("${twitter.oauth.accessToken}")
	private String accessToken;
	
	@Value("${twitter.oauth.accessTokenSecret}")
	private String accessTokenSecret;

	private static final String ERR_MSG_PUBLISH_TWEET = "Error publishing in Twitter";
	
	private Twitter twitter;

//	private TwitterClient twitter;
	
	public TwitterServiceImpl() {}
	
//	TwitterServiceImpl(TwitterClient twitter){
//
//		this.twitter = new TwitterClient();
//	}
	
	@Override
	public Optional<PublishTweetResponse> publish(PublishTweetRequest request) {
		
		try {
//		    logger.info("AccessToken --> " + accessToken);
//		    logger.info("AccessTokenSecret --> " + accessTokenSecret);
//		    logger.info("ConsumerKey --> " + consumerKey);
//		    logger.info("ConsumerSecret --> " + consumerSecret);
//		    logger.info("debug --> " + debug);
		    
			
			StatusUpdate statusUpdate = new StatusUpdate(request.getMessage());
			Status status = this.getTwitterClient().updateStatus(statusUpdate);
			
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
	
	private Twitter getTwitterClient() {
		
		if (twitter == null) {
			twitter = new TwitterFactory().getInstance();
		}
		
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthAccessToken(oathAccessToken);
		
		return twitter;
	}

	
}
