package com.mastercloudapps.twitterscheduler.application.usecase;

import static com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.error;

import com.mastercloudapps.twitterscheduler.application.common.CircuitBreakerHelper;
import com.mastercloudapps.twitterscheduler.application.common.RunnableExecutionResultMapper;
import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult;
import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.ResultErrorCode;
import com.mastercloudapps.twitterscheduler.application.model.shared.BaseCommandUseCase;
import com.mastercloudapps.twitterscheduler.application.model.shared.Command;

import io.vavr.CheckedRunnable;
import io.vavr.control.Try;

import java.util.Optional;

public abstract class GenericCommandUseCase<T extends Command> extends BaseCommandUseCase<T, GenericCommandResult> {

	private final CircuitBreakerHelper useCaseCircuitBreaker;
	
	private final RunnableExecutionResultMapper runnableExecutionResultMapper;

	public GenericCommandUseCase(final CircuitBreakerHelper useCaseCircuitBreaker,
			final RunnableExecutionResultMapper runnableExecutionResultMapper) {

		this.useCaseCircuitBreaker = useCaseCircuitBreaker;
		this.runnableExecutionResultMapper = runnableExecutionResultMapper;
	}

	protected Optional<GenericCommandResult> executeResult(final CheckedRunnable runnable, final ResultErrorCode resultErrorCode) {

		final Try<Void> execution = useCaseCircuitBreaker.run(runnable);

		return runnableExecutionResultMapper.mapResult(execution, resultErrorCode);
	}

	protected Optional<GenericCommandResult> mapErrorResult(final ResultErrorCode resultErrorCode) {
		return Optional.of(error(resultErrorCode));
	}
}
