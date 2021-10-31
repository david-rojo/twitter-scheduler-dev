package com.mastercloudapps.twitterscheduler.application.model.shared;

import java.util.Optional;

import javax.validation.constraints.NotNull;

public abstract class BaseCommandUseCase<T extends Command, Q extends CommandResult> implements CommandUseCase<T, Q> {

	@Override
	public Optional<Q> execute(final @NotNull T parameters) {

		return executeCommand(parameters);
	}

	protected abstract Optional<Q> executeCommand(final T parameters);

}
