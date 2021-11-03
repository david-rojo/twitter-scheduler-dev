package com.mastercloudapps.twitterscheduler.controller.pending.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import com.mastercloudapps.twitterscheduler.controller.pending.mapper.CreatePendingTweetResponseMapper;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePendingTweetResponseMapperTest {
	
	private CreatePendingTweetResponseMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new CreatePendingTweetResponseMapper();
	}

}
