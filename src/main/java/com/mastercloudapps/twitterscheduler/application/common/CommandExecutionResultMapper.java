package com.mastercloudapps.twitterscheduler.application.common;

import java.util.Optional;

import com.mastercloudapps.twitterscheduler.application.model.command.GenericCommandResult.ResultErrorCode;
import com.mastercloudapps.twitterscheduler.application.model.shared.CommandResult;

import io.vavr.control.Try;

@FunctionalInterface
public interface CommandExecutionResultMapper<K, R extends CommandResult> {

  Optional<R> mapResult(final Try<K> execution, final ResultErrorCode resultErrorCode);

}
