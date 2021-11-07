package com.mastercloudapps.twitterscheduler.application.service.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class SchedulerConfiguration {

	@Value("${scheduler.frequency}")
	private String fixedRate;
	
	@Value("${scheduler.initial.delay}")
	private String initialDelay;	
	
}
