package com.mastercloudapps.twitterscheduler.domain.pending;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PendingTweetTest {

	private PendingTweet createPendingTweet(final MockData mockData) {

		return PendingTweet.builder()
				.id(mockData.pendingTweetId)
				.message(mockData.message)
				.publicationDate(mockData.publicationDate)
				.build();
	}

	enum MockData {
		INVALID_NULL_ID(null, "abc", Instant.MAX),
		INVALID_NULL_MESSAGE(1L, null, Instant.MAX),
		INVALID_NULL_PUBLICATION_DATE(1L, "BE-X-ID", null),
		VALID(1L, "valid message 1", Instant.MIN),
		VALID_OTHER_SAME_ID(1L, "valid message 2", Instant.MAX),
		VALID_OTHER_DIFFERENT_ID(2L, "valid message 3", Instant.MAX);

		private final Long pendingTweetId;

		private final String message;

		private final Instant publicationDate;

		MockData(final Long pendingTweetId, final String message, final Instant publicationDate) {
			this.pendingTweetId = pendingTweetId;
			this.message = message;
			this.publicationDate = publicationDate;
		}
	}

	@Nested
	@DisplayName("Test plan creation with invalid params")
	class TestPlanCreationWithInvalidParams {

		@Test
		@DisplayName("Test creation with null parameter, expected NullPointerException")
		void testNullParam() {
			assertThrows(NullPointerException.class, () -> createPendingTweet(MockData.INVALID_NULL_ID));
			assertThrows(NullPointerException.class, () -> createPendingTweet(MockData.INVALID_NULL_MESSAGE));
			assertThrows(NullPointerException.class, () -> createPendingTweet(MockData.INVALID_NULL_PUBLICATION_DATE));
		}
	}
	
	  @Nested
	  @DisplayName("Test plan creation")
	  class TestPlanCreation {

	    private PendingTweet pendingTweet;

	    @BeforeEach
	    void setUp() {
	    	pendingTweet = createPendingTweet(MockData.VALID);
	    }

	    @Test
	    @DisplayName("Test creation, expected not null")
	    void testNotNull() {
	      assertThat(pendingTweet, is(notNullValue()));
	    }

	    @Test
	    @DisplayName("Test creation, expected content")
	    void testEqualsText() {
	      assertThat(pendingTweet.id().id(), is(MockData.VALID.pendingTweetId));
	      assertThat(pendingTweet.message().message(), is(MockData.VALID.message));
	      assertThat(pendingTweet.publicationDate(), is(MockData.VALID.publicationDate));
	    }
	  }
	  
	  @Nested
	  @DisplayName("Test plan for equals and hashcode")
	  class TestPlanEqualsHashCode {

	    private PendingTweet pendingTweet;

	    @BeforeEach
	    void setUp() {
	    	pendingTweet = createPendingTweet(MockData.VALID);
	    }

	    @Test
	    @DisplayName("Test with itself, expected equals")
	    void testEqualsItself() {
	      assertThat(pendingTweet, is(pendingTweet));
	    }

	    @Test
	    @DisplayName("Test with other object with same id, expected equals and same hashcode")
	    void testSameId() {
	      var sameId = createPendingTweet(MockData.VALID_OTHER_SAME_ID);
	      assertThat(pendingTweet, is(sameId));
	      assertThat(pendingTweet.hashCode(), is(sameId.hashCode()));
	    }

	    @Test
	    @DisplayName("Test with other null, expected not equals")
	    void testNotEqualsNull() {
	      assertNotEquals(null, pendingTweet);
	    }

	    @Test
	    @DisplayName("Test with different id, expected not equals and different hashCode")
	    void testDifferentId() {
	      var other = createPendingTweet(MockData.VALID_OTHER_DIFFERENT_ID);
	      assertThat(pendingTweet, is(not(other)));
	      assertThat(pendingTweet.hashCode(), is(not(other.hashCode())));
	    }
	  }
}
