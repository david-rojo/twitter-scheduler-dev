package com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePendingTweetResponseMapperTest {
	
	private CreatePendingTweetResponseMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new CreatePendingTweetResponseMapper();
	}

}
