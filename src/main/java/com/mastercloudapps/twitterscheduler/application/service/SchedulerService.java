package com.mastercloudapps.twitterscheduler.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.manager.FeatureManager;

import com.mastercloudapps.twitterscheduler.application.usecase.scheduler.GetSchedulerStatusUseCase;
import com.mastercloudapps.twitterscheduler.configuration.featureflags.Features;

@Component
public class SchedulerService implements GetSchedulerStatusUseCase {

	private FeatureManager featureManager;

	@Autowired
	public SchedulerService(FeatureManager featureManager) {
		this.featureManager = featureManager;
	}
	
	@Override
	public boolean getStatus() {
		
		return featureManager.isActive(Features.SCHEDULER);
	}

}
