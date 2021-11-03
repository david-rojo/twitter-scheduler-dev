package com.mastercloudapps.twitterscheduler.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.CreatePendingTweetUseCase;
import com.mastercloudapps.twitterscheduler.application.usecase.pending.DeletePendingTweetUseCase;
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
	private CreatePendingTweetUseCase createPendingTweetUseCase;
	
	@MockBean
	private DeletePendingTweetUseCase deletePendingTweetUseCase;

	PendingTweetRequest pendingTweetRequest;

	PendingTweetResponse pendingTweetResponse;

	PendingTweet pendingTweet;

	@BeforeEach
	void setup() {

		Long id = 1L;
		String message = "test message";
		String publicationDate = "2023-04-01T10:00:00Z";

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
				.build();
	}

	@Test
	@DisplayName("Post pending tweet, expect created")
	//@WithMockUser(username = "user", password = "pass", roles = "ADMIN")
	void createPendingTweetTest() throws Exception {

		when(createPendingTweetUseCase.createPendingTweet(Mockito.any()))
			.thenReturn(pendingTweet);

		mvc.perform(
				post("/api/pending/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(pendingTweetRequest)))
		.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("Delete pending tweet, expect deleted")
	@WithMockUser(username = "user", password = "pass", roles = "ADMIN")
	void deletePendingTweetTest() throws Exception {

		doNothing().when(deletePendingTweetUseCase).deletePendingTweet(Mockito.any());

		mvc.perform(
				delete("/api/pending/" + pendingTweet.id().id())
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
