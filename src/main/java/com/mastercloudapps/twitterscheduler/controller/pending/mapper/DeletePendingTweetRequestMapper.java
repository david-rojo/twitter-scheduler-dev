package com.mastercloudapps.twitterscheduler.controller.pending.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.command.DeletePendingTweetRequest;
import com.mastercloudapps.twitterscheduler.controller.exception.InvalidInputException;

@Component
public class DeletePendingTweetRequestMapper {

	public DeletePendingTweetRequest mapRequest(final Long id) {

		if (Optional.ofNullable(id).isEmpty()) {
			throw new InvalidInputException("Invalid payload");
		}
		
		return DeletePendingTweetRequest
				.builder()
				.id(id)
				.build();
	}
}
