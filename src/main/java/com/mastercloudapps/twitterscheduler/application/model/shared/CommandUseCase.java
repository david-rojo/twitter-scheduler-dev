package com.mastercloudapps.twitterscheduler.application.model.shared;

import java.util.Optional;

import javax.validation.constraints.NotNull;

/**
 * Use case interface that has to follow every {@link Command} on the application.
 *
 * @param <T> The given parameters.
 * @param <Q> The business response.
 */
public interface CommandUseCase<T extends Command, Q extends CommandResult> extends UseCase {

	Optional<Q> execute(final @NotNull T parameters);
}
