package com.mastercloudapps.twitterscheduler.integration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.awaitility.Duration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.togglz.core.manager.FeatureManager;

import com.mastercloudapps.twitterscheduler.application.service.task.TweetPublisherTask;
import com.mastercloudapps.twitterscheduler.application.usecase.PublishPendingTweetsUseCase;

@SpringJUnitConfig(TweetPublisherTask.class)
public class ScheduledAwaitilityIntegrationTest {

//	@SpyBean
//	PublishPendingTweetsUseCase useCase;
//	
//	@Autowired
//	FeatureManager featureManager;
//
//	@Disabled
//	@Test
//	@DisplayName("Scheduled Awaitility IT, check scheduled is called")
//	public void whenWait65Seconds_thenScheduledIsCalledOneTime() {
//		
//		await()
//		.atMost(Duration.ONE_MINUTE.plus(Duration.FIVE_MINUTES))
//		.untilAsserted(() -> verify(useCase, times(1)).publishPending());
//	}
}
