package com.mastercloudapps.twitterscheduler.application.model.command;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CreatePendingTweetCommandTest {

	CreatePendingTweetCommand create(MockData data) {
		return CreatePendingTweetCommand.builder()
				.message(data.message)
				.publicationDate(data.publicationDate)
				.build();
	}

	enum MockData {
		VALID("tweet message1", Instant.parse("2022-01-01T00:00:00Z")),
		VALID_OTHER("tweet message2", Instant.parse("2022-01-02T00:00:00Z")),
		INVALID_MESSAGE(null, Instant.parse("2022-01-01T00:00:00Z")),
		INVALID_PUBLICATION_DATE("tweet message3", null);

		private final String message;

		private final Instant publicationDate;

		MockData(String message, Instant publicationDate) {
			this.message = message;
			this.publicationDate = publicationDate;
		}
	}

	@Nested
	@DisplayName("Test plan creation with invalid params")
	class TestPlanCreationWithInvalidParams {

		@Test
		@DisplayName("Test creation with null message, expected NullPointerException")
		void testNullMessage() {
			assertThrows(NullPointerException.class, () -> create(MockData.INVALID_MESSAGE));
		}

		@Test
		@DisplayName("Test creation with null publication date, expected NullPointerException")
		void testNullPublicationDate() {
			assertThrows(NullPointerException.class, () -> create(MockData.INVALID_PUBLICATION_DATE));
		}
	}
	
	  @Nested
	  @DisplayName("Test plan creation")
	  class TestPlanCreation {

	    private CreatePendingTweetCommand command;

	    @BeforeEach
	    void setUp() {
	      command = create(MockData.VALID);
	    }

	    @Test
	    @DisplayName("Test creation, expected not null")
	    void testNotNull() {
	      assertThat(command, is(notNullValue()));
	    }

	    @Test
	    @DisplayName("Test creation, expected message")
	    void testEqualsMessage() {
	      assertThat(command.getMessage(), is(MockData.VALID.message));
	    }

	    @Test
	    @DisplayName("Test creation, expected publication date")
	    void testEqualsPublicationDate() {
	      assertThat(command.getPublicationDate(), is(MockData.VALID.publicationDate));
	    }

	    @Test
	    @DisplayName("Test toString, expected id contained")
	    void testToString() {

	      assertThat(command.toString(), containsString(MockData.VALID.message));
	    }
	  }
}
