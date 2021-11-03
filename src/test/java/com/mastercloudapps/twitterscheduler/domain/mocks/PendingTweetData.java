package com.mastercloudapps.twitterscheduler.domain.mocks;

import java.time.Instant;

import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;

public enum PendingTweetData {
	HAPPY_NEW_YEAR(1L,"Happy new year!", Instant.parse(Constants.PUBLICATION_DATE_NEW_YEAR_2023)),
	MERRY_CHRISTMAS(1L,"Merry Christmas!", Instant.parse(Constants.PUBLICATION_DATE_CHRISTMAS_2024));

	private final Long id;

	private final String message;

	private Instant publicationDate;

	PendingTweetData(final Long id, final String message, final Instant publicationDate) {

		this.id = id;
		this.message = message;
		this.publicationDate = publicationDate;
	}

	public PendingTweet create() {

		return PendingTweet.builder()
				.id(this.id)
				.message(this.message)
				.publicationDate(this.publicationDate)
				.build();
	}

	public static class Constants {

		// ISO-8601 string
		public static final String PUBLICATION_DATE_NEW_YEAR_2023 = "2023-01-01T00:00:00Z";

		public static final String PUBLICATION_DATE_CHRISTMAS_2024 = "2024-12-25T00:00:00Z";

		private Constants() {}
	}
}
