package com.mastercloudapps.twitterscheduler.application.common;

import static com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.ResultErrorCode.REPOSITORY_ERROR;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult;
import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.ResultErrorCode;
import com.mastercloudapps.twitterscheduler.domain.exception.RepositoryException;

import io.vavr.control.Try;

@Component
public class RunnableExecutionResultMapper implements CommandExecutionResultMapper<Void, GenericCommandResult> {

	@Override
	public Optional<GenericCommandResult> mapResult(Try<Void> execution, ResultErrorCode resultErrorCode) {
		
		return execution.fold(e -> this.mapError(e, resultErrorCode), ignored -> Optional.of(GenericCommandResult.success()));
	}

	private Optional<GenericCommandResult> mapError(final Throwable e, final ResultErrorCode resultErrorCode) {

	    if (e instanceof RepositoryException) {
	      return Optional.of(GenericCommandResult.error(REPOSITORY_ERROR));
	    }
	    return Optional.of(GenericCommandResult.error(resultErrorCode));
	  }
}
