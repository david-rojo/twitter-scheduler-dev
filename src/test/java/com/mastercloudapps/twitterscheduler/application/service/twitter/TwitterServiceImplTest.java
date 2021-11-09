package com.mastercloudapps.twitterscheduler.application.service.twitter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetRequest;
import com.mastercloudapps.twitterscheduler.application.model.twitter.PublishTweetResponse;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

@ExtendWith(MockitoExtension.class)
class TwitterServiceImplTest {

	private TwitterServiceImpl service;
	
	@Mock
	private Twitter twitter;
	
	private PublishTweetResponse response;
	
	@BeforeEach
	public void beforeEach() {
		
		this.service = new TwitterServiceImpl(twitter);
		
		this.response = PublishTweetResponse.builder()
				.id(1L)
				.message("test message")
				.publishedAt(Instant.now())
				.build();
	}
	
	
//	@Disabled
//	@Test
//	@DisplayName("Test find one tweet with not existing valid request")
//	void givenFindOneNotExistingValidRequest_expectEmpty() throws TwitterException {
//		
//		String status = this.getStatus();
//		
//		//when(twitter.updateStatus(new StatusUpdate(anyString())))
//		when(twitter.updateStatus(new StatusUpdate("test")))
//				.thenReturn(TwitterObjectFactory.createStatus(status));
//		
//		final var request = PublishTweetRequest.builder().message("test").build();
//		var tweetResponse = service.publish(request);
//		
//		assertThat(tweetResponse, is(notNullValue()));
//		assertThat(tweetResponse.isPresent(), is(false));
//	}


	private String getStatus() {
		String status = "{"
				+ "createdAt=Tue Nov 09 00:14:37 CET 2021, "
				+ "id=1457849067300696064, "
				+ "text='text tweet', "
				+ "source='<a href=\"https://help.twitter.com/en/using-twitter/how-to-tweet#source-labels\" "
				+ "rel=\"nofollow\">twitter-scheduler-cloudapps</a>', "
				+ "isTruncated=false, "
				+ "inReplyToStatusId=-1, "
				+ "inReplyToUserId=-1, "
				+ "isFavorited=false, "
				+ "isRetweeted=false, "
				+ "favoriteCount=0, "
				+ "inReplyToScreenName='null', "
				+ "geoLocation=null, "
				+ "place=null, "
				+ "retweetCount=0, "
				+ "isPossiblySensitive=false, "
				+ "lang='en', "
				+ "contributorsIDs=[], "
				+ "retweetedStatus=null, "
				+ "userMentionEntities=[], "
				+ "urlEntities=[], "
				+ "hashtagEntities=[], "
				+ "mediaEntities=[], "
				+ "symbolEntities=[], "
				+ "currentUserRetweetId=-1, "
				+ "user={"
					+ "id=1418224648014028813, "
					+ "name='Blue Ocean', "
					+ "email='null', "
					+ "screenName='BlueOcean_TFM', "
					+ "location='', "
					+ "description='', "
					+ "isContributorsEnabled=false, "
					+ "profileImageUrl='http://pbs.twimg.com/profile_images/1418229439977164804/baRT3OKj_normal.png', "
					+ "profileImageUrlHttps='https://pbs.twimg.com/profile_images/1418229439977164804/baRT3OKj_normal.png', "
					+ "isDefaultProfileImage=false, "
					+ "url='null', "
					+ "isProtected=false, "
					+ "followersCount=0, "
					+ "status=null, "
					+ "profileBackgroundColor='F5F8FA', "
					+ "profileTextColor='333333', "
					+ "profileLinkColor='1DA1F2', "
					+ "profileSidebarFillColor='DDEEF6', "
					+ "profileSidebarBorderColor='C0DEED', "
					+ "profileUseBackgroundImage=true, "
					+ "isDefaultProfile=true, "
					+ "showAllInlineMedia=false, "
					+ "friendsCount=0, "
					+ "createdAt=Thu Jul 22 17:01:54 CEST 2021, "
					+ "favouritesCount=0, "
					+ "utcOffset=-1, "
					+ "timeZone='null', "
					+ "profileBackgroundImageUrl='null', "
					+ "profileBackgroundImageUrlHttps='null', "
					+ "profileBackgroundTiled=false, "
					+ "lang='null', "
					+ "statusesCount=12, "
					+ "isGeoEnabled=false, "
					+ "isVerified=false, "
					+ "translator=false, "
					+ "listedCount=0, "
					+ "isFollowRequestSent=false, "
					+ "withheldInCountries=[]"
				+ "}, "
				+ "withHeldInCountries=null, "
				+ "quotedStatusId=-1, "
				+ "quotedStatus=null"
			+ "}";
		return status;
	}
	
}
