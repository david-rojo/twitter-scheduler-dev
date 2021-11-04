package com.mastercloudapps.twitterscheduler.application.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercloudapps.twitterscheduler.application.model.operation.CreatePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.application.model.operation.DeletePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.application.model.operation.FindOnePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetPort;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

@ExtendWith(MockitoExtension.class)
public class PendingTweetServiceTest {
	
	private PendingTweetService service;
	
	@Mock
	private PendingTweetPort pendingTweetPort;
	
	PendingTweet pendingTweet;
	
	PendingTweet anotherPendingTweet;
		
	CreatePendingTweetOperation createOperation;
	
	DeletePendingTweetOperation deleteOperation;
	
	FindOnePendingTweetOperation findOneOperation;
	
	@BeforeEach
	public void beforeEach() {
		
		this.service = new PendingTweetService(pendingTweetPort);
		
		Long id = 1L;
		String message = "test message";
		NullableInstant publicationDate = new NullableInstant(Instant
				.now()
				.plus(20,ChronoUnit.MINUTES));
		NullableInstant createdAt = NullableInstant.now();
		
		this.createOperation = CreatePendingTweetOperation.builder()
				.message(message)
				.publicationDate(publicationDate)
				.build();
		
		this.deleteOperation = DeletePendingTweetOperation.builder()
				.id(id)
				.build();
		
		this.findOneOperation = FindOnePendingTweetOperation.builder()
				.id(id)
				.build();
		
		this.pendingTweet = PendingTweet.builder()
				.id(id)
				.message(message)
				.publicationDate(publicationDate.instant())
				.createdAt(createdAt.instant())
				.build();
		
		Long anotherId = 2L;
		String anotherMessage = "another test message";
		NullableInstant anotherPublicationDate = new NullableInstant(Instant
				.now()
				.plus(30,ChronoUnit.MINUTES));
		NullableInstant anotherCreatedAt = NullableInstant.now();
		
		this.anotherPendingTweet = PendingTweet.builder()
				.id(anotherId)
				.message(anotherMessage)
				.publicationDate(anotherPublicationDate.instant())
				.createdAt(anotherCreatedAt.instant())
				.build();
	}
	
	@Test
	@DisplayName("Test create pending tweet with valid request")
	void givenCreateValidRequest_expectCreatedPendingTweet() {
		
		when(service.createPendingTweet(createOperation)).thenReturn(pendingTweet);
		
		PendingTweet created = service.createPendingTweet(createOperation);
		
		assertThat(created, is(notNullValue()));
		assertThat(created.id().id(), is(notNullValue()));
		assertEquals(created.message().message(), createOperation.getMessage());
		assertEquals(created.publicationDate().instant(), createOperation.getPublicationDate().instant());
	}
	
	@Test
	@DisplayName("Test find all pending tweets with valid request")
	void givenFindAllValidRequest_expectAllPendingTweets() {
		
		List<PendingTweet> pendingTweets = Stream.of(pendingTweet, anotherPendingTweet)
				.collect(Collectors.toList());
		
		when(service.findAll()).thenReturn(pendingTweets);
		
		var pendingTweetsResponse = service.findAll().stream().collect(Collectors.toList());
		
		assertThat(pendingTweetsResponse, is(notNullValue()));
		assertThat(pendingTweetsResponse,hasSize(2));
		assertThat(pendingTweetsResponse.get(0), is(notNullValue()));
		assertEquals(pendingTweetsResponse.get(0).id().id(), pendingTweet.id().id());
		assertEquals(pendingTweetsResponse.get(0).message().message(), pendingTweet.message().message());
//		assertEquals(pendingTweetsResponse.get(0).publicationDate().instant(), pendingTweet.publicationDate().instant());
//		assertEquals(pendingTweetsResponse.get(0).createdAt().instant(), pendingTweet.createdAt().instant());
		assertThat(pendingTweetsResponse.get(1), is(notNullValue()));
		assertEquals(pendingTweetsResponse.get(1).id().id(), anotherPendingTweet.id().id());
		assertEquals(pendingTweetsResponse.get(1).message().message(), anotherPendingTweet.message().message());
//		assertEquals(pendingTweetsResponse.get(1).publicationDate().instant(), pendingTweet.publicationDate().instant());
//		assertEquals(pendingTweetsResponse.get(1).createdAt().instant(), pendingTweet.createdAt().instant());
	}
	
	@Test
	@DisplayName("Test find one pending tweet with valid request")
	void givenFindOneValidRequest_expectPendingTweet() {
		
		when(service.findOne(findOneOperation)).thenReturn(Optional.of(pendingTweet));
		
		var pendingTweetResponse = service.findOne(findOneOperation);
		
		assertThat(pendingTweetResponse, is(notNullValue()));
		assertThat(pendingTweetResponse.isPresent(), is(true));
		assertThat(pendingTweetResponse.get(), instanceOf(PendingTweet.class));
		assertEquals(pendingTweetResponse.get().id().id(), pendingTweet.id().id());
		assertEquals(pendingTweetResponse.get().message().message(), pendingTweet.message().message());
	}
	
	
	
	

}
