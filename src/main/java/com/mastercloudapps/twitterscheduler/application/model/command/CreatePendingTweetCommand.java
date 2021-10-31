package com.mastercloudapps.twitterscheduler.application.model.command;

import java.time.Instant;

import com.mastercloudapps.twitterscheduler.application.model.shared.Command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class CreatePendingTweetCommand implements Command {

	private static final long serialVersionUID = 3099486578059492608L;

	@NonNull
	private final String message;

	@NonNull
	private final Instant publicationDate;

}
