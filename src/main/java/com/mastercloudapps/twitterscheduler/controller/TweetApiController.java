package com.mastercloudapps.twitterscheduler.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercloudapps.twitterscheduler.controller.tweet.dto.TweetResponse;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/tweets")
@SecurityRequirement(name = "twitter-scheduler")
public class TweetApiController implements TweetApi {

	@GetMapping
	public Collection<TweetResponse> getTweets() {

		return Collections.emptyList();
	}

	@GetMapping("/{id}")
	public TweetResponse getTweetById(Long id) {
		
		Instant now = Instant.now();
		return TweetResponse.builder()
				.tweetId(id)
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
				.publishedAt(NullableInstant.now().getFormatted())
				.updatedAt(NullableInstant.now().getFormatted())
				.build();
	}

}
