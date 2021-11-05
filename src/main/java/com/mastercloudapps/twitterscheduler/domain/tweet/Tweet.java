package com.mastercloudapps.twitterscheduler.domain.tweet;

import static java.util.Objects.requireNonNull;

import java.time.Instant;

import com.mastercloudapps.twitterscheduler.domain.shared.AggregateRoot;
import com.mastercloudapps.twitterscheduler.domain.shared.Message;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

public class Tweet extends AggregateRoot<TweetId> {
	
	private static final long serialVersionUID = 2394451305012148462L;

	private Message message;

	private NullableInstant requestedPublicationDate;
	
	private NullableInstant publishedAt;
	
	private NullableInstant createdAt;

	private Tweet(final Builder builder) {
		super(builder.tweetId);
		this.message = builder.message;
		this.requestedPublicationDate = builder.requestedPublicationDate;
		this.publishedAt = builder.publishedAt;
		this.createdAt = builder.createdAt;
	}

	public Message message() {

		return message;
	}

	public NullableInstant requestedPublicationDate() {

		return requestedPublicationDate;
	}
	
	public NullableInstant publishedAt() {
		
		return publishedAt;
	}

	public NullableInstant createdAt() {
		
		return createdAt;
	}

	public static IdStep builder() {

		return new Builder();
	}

	public interface IdStep {

		MessageStep id(final Long pendingTweetId);
	}

	public interface MessageStep {

		RequestedPublicationDateStep message(final String message);
	}

	public interface RequestedPublicationDateStep {

		PublishedAtStep requestedPublicationDate(final Instant instant);
	}
	
	public interface PublishedAtStep {

		CreatedAtStep publishedAt(final Instant instant);
	}
	
	public interface CreatedAtStep {
		
		Build createdAt(final Instant instant);
	}

	public interface Build {

		Tweet build();
	}

	public static class Builder implements IdStep, MessageStep, RequestedPublicationDateStep,
		PublishedAtStep, CreatedAtStep, Build {

		private TweetId tweetId;

		private Message message;

		private NullableInstant requestedPublicationDate;
		
		private NullableInstant publishedAt;
		
		private NullableInstant createdAt;

		@Override
		public MessageStep id(Long tweetId) {
			this.tweetId = TweetId.valueOf(requireNonNull(tweetId, "Tweet Id cannot be null."));
			return this;
		}

		@Override
		public RequestedPublicationDateStep message(String message) {
			this.message = Message.valueOf(requireNonNull(message, "Message cannot be null."));
			return this;
		}

		@Override
		public PublishedAtStep requestedPublicationDate(Instant instant) {
			Instant reqPubDate = requireNonNull(instant, "Requested publication date cannot be null.");
			this.requestedPublicationDate = new NullableInstant(reqPubDate);
			return this;
		}

		@Override
		public CreatedAtStep publishedAt(Instant instant) {
			Instant pubDate = requireNonNull(instant, "published at date cannot be null.");
			this.publishedAt = new NullableInstant(pubDate);;
			return this;
		}
		
		@Override
		public Build createdAt(Instant instant) {
			Instant creationDate = requireNonNull(instant, "CreatedAt date cannot be null.");
			NullableInstant niCreatedAt = new NullableInstant(creationDate);
			this.createdAt = niCreatedAt;
			return this;
		}
		
		@Override
		public Tweet build() {
			return new Tweet(this);
		}
	}
	
}
