package com.mastercloudapps.twitterscheduler.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mastercloudapps.twitterscheduler.application.usecase.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.DeletePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetRequest;
import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetResponse;
import com.mastercloudapps.twitterscheduler.controller.pending.mapper.CreatePendingTweetRequestMapper;
import com.mastercloudapps.twitterscheduler.controller.pending.mapper.CreatePendingTweetResponseMapper;
import com.mastercloudapps.twitterscheduler.controller.pending.mapper.DeletePendingTweetRequestMapper;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/pending")
@SecurityRequirement(name = "twitter-scheduler")
public class PendingApiController implements PendingApi {

	private final CreatePendingTweetRequestMapper createPendingTweetRequestMapper;
	
	private final CreatePendingTweetResponseMapper createPendingTweetResponseMapper;
	
	private final CreatePendingTweetUseCase createPendingTweetUseCase;
	
	private final DeletePendingTweetUseCase deletePendingTweetUseCase;
	
	private final DeletePendingTweetRequestMapper deletePendingTweetRequestMapper;
	
	@Autowired
	public PendingApiController(final CreatePendingTweetRequestMapper createPendingTweetRequestMapper,
			final CreatePendingTweetResponseMapper createPendingTweetResponseMapper,
			final CreatePendingTweetUseCase createPendingTweetUseCase,
			final DeletePendingTweetUseCase deletePendingTweetUseCase,
			final DeletePendingTweetRequestMapper deletePendingTweetRequestMapper) {
		
		this.createPendingTweetRequestMapper = createPendingTweetRequestMapper;
		this.createPendingTweetResponseMapper = createPendingTweetResponseMapper;
		this.createPendingTweetUseCase = createPendingTweetUseCase;
		this.deletePendingTweetUseCase = deletePendingTweetUseCase;
		this.deletePendingTweetRequestMapper = deletePendingTweetRequestMapper;
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
		
		final var createPendingTweetResponse = createPendingTweetUseCase.createPendingTweet(createPendingTweetRequest);
		
		return createPendingTweetResponseMapper.mapResponse(createPendingTweetResponse);
		
		//return getDummyResponse(505L);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePendingTweet(Long id) {
				
		try {
			deletePendingTweetUseCase.deletePendingTweet(
					deletePendingTweetRequestMapper.mapRequest(id));
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	private PendingTweetResponse getDummyResponse(Long id) {
		
		Instant now = Instant.now();
		return PendingTweetResponse.builder()
				.pendingTweetId(id)
				.message("Status message")
//				.images(Arrays.asList(PendingImageResponse.builder()
//						.pendingImageId(5555L)
//						.url("https://davidrojo.eu/images/tfm/1.jpg")
//						.build()))
				.publicationDate(
						new NullableInstant(now.plus(20,ChronoUnit.SECONDS)).getFormatted())
				.createdAt(new NullableInstant(now).getFormatted())
				.build();
	}

}
