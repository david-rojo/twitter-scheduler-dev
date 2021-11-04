package com.mastercloudapps.twitterscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercloudapps.twitterscheduler.application.usecase.scheduler.GetSchedulerStatusUseCase;
import com.mastercloudapps.twitterscheduler.controller.scheduler.dto.SchedulerResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/scheduler")
@SecurityRequirement(name = "twitter-scheduler")
public class SchedulerApiController implements SchedulerApi {

	private final GetSchedulerStatusUseCase getSchedulerStatusUseCase;
	
	@Autowired
	public SchedulerApiController(final GetSchedulerStatusUseCase getSchedulerStatusUseCase) {
		
		this.getSchedulerStatusUseCase = getSchedulerStatusUseCase;
	}
	
	@GetMapping("/status")
	public ResponseEntity<SchedulerResponse> getStatus() {

		boolean active = getSchedulerStatusUseCase.getStatus();
		
		return ResponseEntity.ok(SchedulerResponse.builder()
				.active(active)
				.build());
	}

}
