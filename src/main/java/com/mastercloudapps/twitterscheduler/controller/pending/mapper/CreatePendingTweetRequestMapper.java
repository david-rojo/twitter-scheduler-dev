package com.mastercloudapps.twitterscheduler.controller.pending.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.command.CreatePendingTweetRequest;
import com.mastercloudapps.twitterscheduler.controller.exception.InvalidInputException;
import com.mastercloudapps.twitterscheduler.controller.pending.dto.PendingTweetRequest;
import com.mastercloudapps.twitterscheduler.domain.shared.NullableInstant;

@Component
public class CreatePendingTweetRequestMapper {

	public CreatePendingTweetRequest mapRequest(final PendingTweetRequest request) {

		if (Optional.ofNullable(request).isEmpty()) {
			throw new InvalidInputException("Invalid payload");
		}

		final var message = this.mapMessage(request);
		final var publicationDate = this.mapPublicationDate(request);
		
		return CreatePendingTweetRequest
				.builder()
				.message(message)
				.publicationDate(publicationDate)
				.build();
	}

	private String mapMessage(final PendingTweetRequest request) {

		if (request.getMessage().equalsIgnoreCase("")) {
			throw new InvalidInputException("Missing required message");
		}
		return request.getMessage();
	}
	
	private NullableInstant mapPublicationDate(final PendingTweetRequest request) {

		if (request.getPublicationDate().equalsIgnoreCase("")) {
			throw new InvalidInputException("Missing required publicationDate");
		}
		return NullableInstant.fromUtcISO8601(request.getPublicationDate());
	}
}
