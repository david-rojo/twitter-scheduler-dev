package com.mastercloudapps.twitterscheduler.application.model.command;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.mastercloudapps.twitterscheduler.application.model.shared.CommandResult;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GenericCommandResult implements CommandResult {

	private static final long serialVersionUID = -8594796032918973541L;

	private ResultErrorCode resultErrorCode;

	public static GenericCommandResult success() {
		return new GenericCommandResult();
	}

	public static GenericCommandResult error(@NotNull ResultErrorCode resultCode) {
		return new GenericCommandResult(resultCode);
	}

	@Override
	public boolean isSuccess() {
		return getResultErrorCode().isEmpty();
	}

	public Optional<ResultErrorCode> getResultErrorCode() {
		return Optional.ofNullable(resultErrorCode);
	}

	public enum ResultErrorCode {
		CREATE_PENDINT_TWEET_STATUS_UNSPECIFIED,
		MESSAGE_MAX_LENGTH_EXCEEDED,
		EXPIRED_PUBLICATION_DATE,
		GENERIC_ERROR,
		REPOSITORY_ERROR
	}

}
