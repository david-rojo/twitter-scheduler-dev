package com.mastercloudapps.twitterscheduler.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.mastercloudapps.twitterscheduler.application.usecase.tweet.FindAllTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.tweet.FindOneTweetUseCase;
import com.mastercloudapps.twitterscheduler.domain.mocks.TweetData;
import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TweetApiController Unit tests using mocks")
class TweetApiControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private FindAllTweetUseCase findAllUseCase;
	
	@MockBean
	private FindOneTweetUseCase findOneUseCase;
	
	private Tweet tweet;
	
	private Tweet anotherTweet;
	
	@BeforeEach
	void setup() {
		
		tweet = TweetData.HAPPY_NEW_YEAR.create();
		anotherTweet = TweetData.MERRY_CHRISTMAS.create();
	}
	
	@Test
	@DisplayName("Find all tweets, expect all tweets")
	void findAllTweetTest() throws Exception {
	
		List<Tweet> tweets = Stream.of(tweet, anotherTweet)
				.collect(Collectors.toList());
		
		when(findAllUseCase.findAll()).thenReturn(tweets);
		
		mvc.perform(
	    		get("/api/tweets/")
	    		.contentType(MediaType.APPLICATION_JSON)
	    	)
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$", hasSize(2)))
	    	.andExpect(jsonPath("$[0].id", equalTo(Math.toIntExact(tweet.id().id()))))
	    	.andExpect(jsonPath("$[0].message", equalTo(tweet.message().message())))
	    	.andExpect(jsonPath("$[1].id", equalTo(Math.toIntExact(anotherTweet.id().id()))))
	    	.andExpect(jsonPath("$[1].message", equalTo(anotherTweet.message().message())));	    	
	}
	
	@Test
	@DisplayName("Find one tweet by id, expect pending tweet")
	void findOneTweetTest() throws Exception {
		
		when(findOneUseCase.findOne(any())).thenReturn(Optional.of(tweet));
		
		mvc.perform(
	    		get("/api/tweets/" + tweet.id().id())
	    		.contentType(MediaType.APPLICATION_JSON)
	    	)
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.id", equalTo(Math.toIntExact(tweet.id().id()))))
	    	.andExpect(jsonPath("$.message", equalTo(tweet.message().message())));
	}
}
