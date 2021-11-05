package com.mastercloudapps.twitterscheduler.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.DeletePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.FindAllPendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.FindOnePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetRequest;
import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetResponse;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("PendingApiController Unit tests using mocks")
class PendingApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CreatePendingTweetUseCase createTweetUseCase;
	
	@MockBean
	private DeletePendingTweetUseCase deleteUseCase;
	
	@MockBean
	private FindAllPendingTweetUseCase findAllUseCase;
	
	@MockBean
	private FindOnePendingTweetUseCase findOneUseCase;

	PendingTweetRequest pendingTweetRequest;

	PendingTweetResponse pendingTweetResponse;

	PendingTweet pendingTweet;
	
	PendingTweet anotherPendingTweet;

	@BeforeEach
	void setup() {

		Long id = (long) 1;
		String message = "test message";
		String publicationDate = "2023-04-01T10:00:00Z";
		String createdAt = "2023-04-01T10:00:00Z";

		pendingTweetRequest = PendingTweetRequest
				.builder()
				.message(message)
				.publicationDate(publicationDate)
				.build();

		pendingTweet = PendingTweet
				.builder()
				.id(id)
				.message(message)
				.publicationDate(NullableInstant.fromUtcISO8601(publicationDate).instant())
				.createdAt(NullableInstant.fromUtcISO8601(createdAt).instant())
				.build();
		
		anotherPendingTweet = PendingTweet
				.builder()
				.id(2L)
				.message("another test message")
				.publicationDate(NullableInstant.fromUtcISO8601("2024-04-01T10:00:00Z").instant())
				.createdAt(NullableInstant.fromUtcISO8601("2023-08-01T10:00:00Z").instant())
				.build();		
	}

	@Test
	@DisplayName("Post pending tweet, expect created")
	void createPendingTweetTest() throws Exception {

		when(createTweetUseCase.create(Mockito.any()))
			.thenReturn(pendingTweet);

		mvc.perform(
				post("/api/pending/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(pendingTweetRequest)))
		.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("Delete pending tweet, expect deleted")
	void deletePendingTweetTest() throws Exception {

		doNothing().when(deleteUseCase).delete(Mockito.any());

		mvc.perform(
				delete("/api/pending/" + pendingTweet.id().id())
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Find all pending tweets, expect all pending tweets")
	void findAllPendingTweetTest() throws Exception {
	
		List<PendingTweet> pendingTweets = Stream.of(pendingTweet, anotherPendingTweet)
				.collect(Collectors.toList());
		
		when(findAllUseCase.findAll()).thenReturn(pendingTweets);
		
		mvc.perform(
	    		get("/api/pending/")
	    		.contentType(MediaType.APPLICATION_JSON)
	    	)
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$", hasSize(2)))
	    	.andExpect(jsonPath("$[0].id", equalTo(Math.toIntExact(pendingTweet.id().id()))))
	    	.andExpect(jsonPath("$[0].message", equalTo(pendingTweet.message().message())))
	    	.andExpect(jsonPath("$[1].id", equalTo(Math.toIntExact(anotherPendingTweet.id().id()))))
	    	.andExpect(jsonPath("$[1].message", equalTo(anotherPendingTweet.message().message())));	    	
	}
	
	@Test
	@DisplayName("Find one pending tweet by id, expect pending tweet")
	void findOnePendingTweetTest() throws Exception {
		
		when(findOneUseCase.findOne(any())).thenReturn(Optional.of(pendingTweet));
		
		mvc.perform(
	    		get("/api/pending/" + pendingTweet.id().id())
	    		.contentType(MediaType.APPLICATION_JSON)
	    	)
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.id", equalTo(Math.toIntExact(pendingTweet.id().id()))))
	    	.andExpect(jsonPath("$.message", equalTo(pendingTweet.message().message())));
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
