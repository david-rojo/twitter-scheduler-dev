package com.mastercloudapps.twitterscheduler.application.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercloudapps.twitterscheduler.application.model.operation.CreatePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.application.model.operation.DeletePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetPort;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

@ExtendWith(MockitoExtension.class)
public class PendingTweetServiceTest {
	
	private PendingTweetService service;
	
	@Mock
	private PendingTweetPort pendingTweetPort;
	
	CreatePendingTweetOperation createRequest;
	
	DeletePendingTweetOperation deleteRequest;
	
	PendingTweet pendingTweet;
	
	@BeforeEach
	public void beforeEach() {
		
		this.service = new PendingTweetService(pendingTweetPort);
		
		Long id = 1L;
		String message = "test message";
		NullableInstant publicationDate = new NullableInstant(Instant
				.now()
				.plus(20,ChronoUnit.MINUTES));
		NullableInstant createdAt = NullableInstant.now();
		
		this.createRequest = CreatePendingTweetOperation.builder()
				.message(message)
				.publicationDate(publicationDate)
				.build();
		
		this.deleteRequest = DeletePendingTweetOperation.builder()
				.id(id)
				.build();
		
		this.pendingTweet = PendingTweet.builder()
				.id(id)
				.message(message)
				.publicationDate(publicationDate.instant())
				.createdAt(createdAt.instant())
				.build();
	}
	
	@Test
	@DisplayName("Test create pending tweet with valid request")
	void givenCreateValidRequest_expectCreatedPendingTweet() {
		
		when(service.createPendingTweet(createRequest)).thenReturn(pendingTweet);
		
		PendingTweet created = service.createPendingTweet(createRequest);
		
		assertThat(created, is(notNullValue()));
		assertThat(created.id().id(), is(notNullValue()));
		assertEquals(created.message().message(), createRequest.getMessage());
		assertEquals(created.publicationDate().instant(), createRequest.getPublicationDate().instant());
	}

}
