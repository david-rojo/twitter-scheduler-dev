package com.mastercloudapps.twitterscheduler.application.service.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
public class PublisherTask {
	
	private static Logger logger = LoggerFactory.getLogger(PublisherTask.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Async
	@Scheduled(fixedRate = 10000)
    public void execute() {
		
		logger.info("The time is now {}", dateFormat.format(new Date()));
	}

}
