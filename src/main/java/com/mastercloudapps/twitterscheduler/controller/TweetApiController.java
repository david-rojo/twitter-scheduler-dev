package com.mastercloudapps.twitterscheduler.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercloudapps.twitterscheduler.application.usecase.tweet.FindAllTweetUseCase;
import com.mastercloudapps.twitterscheduler.controller.tweet.dto.TweetResponse;
import com.mastercloudapps.twitterscheduler.controller.tweet.mapper.TweetResponseMapper;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/tweets")
@SecurityRequirement(name = "twitter-scheduler")
public class TweetApiController implements TweetApi {

	private final FindAllTweetUseCase findAllTweetUseCase;
	
	private final TweetResponseMapper responseMapper;
	
	public TweetApiController(final FindAllTweetUseCase findAllTweetUseCase,
			final TweetResponseMapper responseMapper) {
		
		this.findAllTweetUseCase = findAllTweetUseCase;
		this.responseMapper = responseMapper;
	}
	
	@GetMapping
	public Collection<TweetResponse> getTweets() {

		return findAllTweetUseCase.findAll().stream()
				.map(pendingTweet -> responseMapper.mapResponse(pendingTweet))
				.collect(Collectors.toList());	
	}

	@GetMapping("/{id}")
	public TweetResponse getTweetById(Long id) {
		
		Instant now = Instant.now();
		return TweetResponse.builder()
				.id(id)
				.message("test message")
//				.images(Arrays.asList(TweetImageResponse
//						.builder()
//						.tweetImageId(1453486161003954183L)
//						.type("image/jpeg")
//						.size(50734L)
//						.height(512)
//						.width(512)
//						.build()))
				.createdAt(NullableInstant.now().getFormatted())
				.requestedPublicationDate(
						new NullableInstant(now.plus(20,ChronoUnit.MINUTES)).getFormatted())
				.publishedAt(new NullableInstant(now.plus(21,ChronoUnit.MINUTES)).getFormatted())
				.build();
	}

}
