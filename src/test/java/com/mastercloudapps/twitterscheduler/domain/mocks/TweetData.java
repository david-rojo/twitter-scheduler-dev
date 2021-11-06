package com.mastercloudapps.twitterscheduler.domain.mocks;

import java.time.Instant;

import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;

public enum TweetData {
	HAPPY_NEW_YEAR(
			1L,
			"Happy new year!",
			Instant.parse(Constants.REQUESTED_PUBLICATION_DATE_NEW_YEAR_2023),
			Instant.parse(Constants.PUBLISHED_AT_NEW_YEAR_2023),
			Instant.parse(Constants.CREATED_AT_NEW_YEAR_2023)),
	MERRY_CHRISTMAS(
			1L,
			"Merry Christmas!",
			Instant.parse(Constants.REQUESTED_PUBLICATION_DATE_CHRISTMAS_2024),
			Instant.parse(Constants.PUBLISHED_AT_CHRISTMAS_2024),
			Instant.parse(Constants.CREATED_AT_CHRISTMAS_2024));

	private final Long id;

	private final String message;

	private Instant requestedPublicationDate;
	
	private Instant publishedAt;
	
	private Instant createdAt;

	TweetData(final Long id, final String message, final Instant requestedPublicationDate,
			final Instant publishedAt, final Instant createdAt) {

		this.id = id;
		this.message = message;
		this.requestedPublicationDate = requestedPublicationDate;
		this.publishedAt = publishedAt;
		this.createdAt = createdAt;
	}

	public Tweet create() {

		return Tweet.builder()
				.id(this.id)
				.message(this.message)
				.requestedPublicationDate(this.requestedPublicationDate)
				.publishedAt(this.publishedAt)
				.createdAt(this.createdAt)
				.build();
	}

	public static class Constants {

		// ISO-8601 string
		public static final String REQUESTED_PUBLICATION_DATE_NEW_YEAR_2023 = "2023-01-01T00:00:00Z";

		public static final String REQUESTED_PUBLICATION_DATE_CHRISTMAS_2024 = "2024-12-25T00:00:00Z";

		public static final String PUBLISHED_AT_NEW_YEAR_2023 = "2023-01-01T00:01:00Z";

		public static final String PUBLISHED_AT_CHRISTMAS_2024 = "2024-12-25T00:02:00Z";
		
		public static final String CREATED_AT_NEW_YEAR_2023 = "2021-10-01T00:00:00Z";
		
		public static final String CREATED_AT_CHRISTMAS_2024 = "2021-11-01T00:00:00Z";

		private Constants() {}
	}
}
