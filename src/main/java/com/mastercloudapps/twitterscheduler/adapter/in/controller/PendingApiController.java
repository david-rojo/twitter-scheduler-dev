package com.mastercloudapps.twitterscheduler.adapter.in.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.dto.PendingImageResponse;
import com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.dto.PendingTweetRequest;
import com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.dto.PendingTweetResponse;
import com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.mapper.CreatePendingTweetRequestMapper;
import com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.mapper.CreatePendingTweetResponseMapper;
import com.mastercloudapps.twitterscheduler.application.port.in.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/pending")
@SecurityRequirement(name = "twitter-scheduler")
public class PendingApiController implements PendingApi {

	private final CreatePendingTweetRequestMapper createPendingTweetRequestMapper;
	
	private final CreatePendingTweetResponseMapper createPendingTweetResponseMapper;
	
	private final CreatePendingTweetUseCase createPendigTweetUseCase;
	
	@Autowired
	public PendingApiController(final CreatePendingTweetRequestMapper createPendingTweetRequestMapper,
			final CreatePendingTweetResponseMapper createPendingTweetResponseMapper,
			final CreatePendingTweetUseCase createPendigTweetUseCase) {
		
		this.createPendingTweetRequestMapper = createPendingTweetRequestMapper;
		this.createPendingTweetResponseMapper = createPendingTweetResponseMapper;
		this.createPendigTweetUseCase = createPendigTweetUseCase;
	
	}
	
	@GetMapping
	public Collection<PendingTweetResponse> getPendingTweets() {
		
		return Collections.emptyList();
	}

	@GetMapping("/{id}")
	public PendingTweetResponse getPendingTweetById(Long id) {

		return getDummyResponse(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PendingTweetResponse createPendingTweet(PendingTweetRequest request) {

		final var createPendingTweetRequest = createPendingTweetRequestMapper.mapRequest(request);
		
		final var createPendingTweetResponse = createPendigTweetUseCase.createPendingTweet(createPendingTweetRequest);
		
		return createPendingTweetResponseMapper.mapResponse(createPendingTweetResponse);
		
		//return getDummyResponse(505L);
	}

	@DeleteMapping("/{id}")
	public PendingTweetResponse deletePendingTweet(Long id) {
		
		return getDummyResponse(id);
	}
	
	private PendingTweetResponse getDummyResponse(Long id) {
		
		Instant now = Instant.now();
		return PendingTweetResponse.builder()
				.pendingTweetId(id)
				.message("Status message")
				.images(Arrays.asList(PendingImageResponse.builder()
						.pendingImageId(5555L)
						.url("https://davidrojo.eu/images/tfm/1.jpg")
						.build()))
				.publicationDate(
						new NullableInstant(now.plus(20,ChronoUnit.SECONDS)).getFormatted())
				.createdAt(new NullableInstant(now).getFormatted())
				.updatedAt(new NullableInstant(now).getFormatted())
				.build();
	}

}
