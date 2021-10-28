package com.mastercloudapps.twitterscheduler.application.service.task;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduledTasks {

	@Bean
	@ConditionalOnProperty(
			value = "tasks.publisher.enabled", matchIfMissing = true, havingValue = "true"
	)
	public PublisherTask scheduledPublisherTask() {

		return new PublisherTask();
	}
}
