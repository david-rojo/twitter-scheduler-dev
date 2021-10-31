package com.mastercloudapps.twitterscheduler.domain.pending;

import static java.util.Objects.requireNonNull;

import java.time.Instant;

import com.mastercloudapps.twitterscheduler.domain.exception.ExpiredPublicationDateException;
import com.mastercloudapps.twitterscheduler.domain.shared.AggregateRoot;
import com.mastercloudapps.twitterscheduler.domain.shared.Message;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

public class PendingTweet extends AggregateRoot<PendingTweetId> {

	private static final long serialVersionUID = -9046699696976862412L;

	private Message message;

	private NullableInstant publicationDate;

	private PendingTweet(final Builder builder) {
		super(builder.pendingTweetId);
		this.message = builder.message;
		this.publicationDate = builder.publicationDate;
	}

	public Message message() {

		return message;
	}

	public NullableInstant publicationDate() {

		return publicationDate;
	}

	public static IdStep builder() {

		return new Builder();
	}

	public interface IdStep {

		MessageStep id(final Long pendingTweetId);
	}

	public interface MessageStep {

		PublicationDateStep message(final String message);
	}

	public interface PublicationDateStep {

		Build publicationDate(final Instant instant);
	}

	public interface Build {

		PendingTweet build();
	}

	public static class Builder implements IdStep, MessageStep, PublicationDateStep, Build {

		private PendingTweetId pendingTweetId;

		private Message message;

		private NullableInstant publicationDate;

		@Override
		public MessageStep id(Long pendingTweetId) {
			this.pendingTweetId = PendingTweetId.valueOf(requireNonNull(pendingTweetId, "Pending Tweet Id cannot be null."));
			return this;
		}

		@Override
		public PublicationDateStep message(String message) {
			this.message = Message.valueOf(requireNonNull(message, "Message cannot be null."));
			return this;
		}

		@Override
		public Build publicationDate(Instant instant) {
			Instant pubDate = requireNonNull(instant, "Publication date date cannot be null.");
			NullableInstant niPubDate = new NullableInstant(pubDate);
			NullableInstant niNow = NullableInstant.now();
			if (instant.isBefore(niNow.instant())) {
				throw new ExpiredPublicationDateException(niPubDate, niNow);
			}
			this.publicationDate = niPubDate;
			return this;
		}

		@Override
		public PendingTweet build() {
			return new PendingTweet(this);
		}

	}
	
}
