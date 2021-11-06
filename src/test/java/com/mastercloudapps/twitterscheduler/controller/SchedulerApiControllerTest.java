package com.mastercloudapps.twitterscheduler.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.mastercloudapps.twitterscheduler.application.usecase.GetSchedulerStatusUseCase;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SchedulerApiController Unit tests using mocks")
class SchedulerApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GetSchedulerStatusUseCase getSchedulerStatusTweetUseCase;
	
	@Test
	@DisplayName("Get scheduler status when active, expect true")
	void getSchedulerStatusWhenActive() throws Exception {
		
		when(getSchedulerStatusTweetUseCase.getStatus()).thenReturn(true);
		
		mvc.perform(
	    		get("/api/scheduler/status")
	    		.contentType(MediaType.APPLICATION_JSON)
	    	)
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.active", equalTo(true)));
	}
	
	@Test
	@DisplayName("Get scheduler status when not active, expect false")
	void getSchedulerStatusWhenNotActive() throws Exception {
		
		when(getSchedulerStatusTweetUseCase.getStatus()).thenReturn(false);
		
		mvc.perform(
	    		get("/api/scheduler/status")
	    		.contentType(MediaType.APPLICATION_JSON)
	    	)
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.active", equalTo(false)));
	}
	
}
