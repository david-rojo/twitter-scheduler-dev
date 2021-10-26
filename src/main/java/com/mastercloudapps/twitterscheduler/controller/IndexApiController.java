package com.mastercloudapps.twitterscheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/")
@SecurityRequirement(name = "twitter-scheduler")
public class IndexApiController {

	@Value("${openapi.credentials.user}")
	private String user;
	
	@Value("${openapi.credentials.password}")
	private String password;

	private Logger log = LoggerFactory.getLogger(IndexApiController.class);
	
	@GetMapping
	public ResponseEntity<String> getIndex() {

		log.info("user: " + user + ", password: " + password);
		return new ResponseEntity<>("Hello from TwitterScheduler app!", HttpStatus.OK);
	}
}
