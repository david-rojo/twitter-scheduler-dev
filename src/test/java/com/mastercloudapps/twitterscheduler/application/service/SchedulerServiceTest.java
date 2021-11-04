package com.mastercloudapps.twitterscheduler.application.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.togglz.core.manager.FeatureManager;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {
	
	private SchedulerService service;
	
	@Mock
	private FeatureManager featureManager;
	
	@BeforeEach
	public void beforeEach() {
		
		this.service = new SchedulerService(featureManager);
	}
	
	@Test
	@DisplayName("Test get scheduler status when active")
	void givenGetScheduleStatusValidRequest_whenActive_expectScheduledStatusActive() {
		
		when(service.getStatus()).thenReturn(true);
		
		boolean status = service.getStatus();
		
		assertThat(status, is(notNullValue()));
		assertThat(status, is(true));
	}	
	
	@Test
	@DisplayName("Test get scheduler status when inactive")
	void givenGetScheduleStatusValidRequest_whenInactive_expectScheduledStatusActive() {
		
		when(service.getStatus()).thenReturn(false);
		
		boolean status = service.getStatus();
		
		assertThat(status, is(notNullValue()));
		assertThat(status, is(false));
	}
	

}
