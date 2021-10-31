package com.mastercloudapps.twitterscheduler.application.model.shared;

import java.io.Serializable;

/**
 * Represents a direct modification request from the application controllers. A command should be structured by:
 *
 * @see Command  represents a modification request with the given parameters to invoke.
 * @see CommandResult response object that fulfill the command request.
 * @see CommandUseCase the different logic with query objects and aggregates and service to
 *     execute to perform the results.
 */
public interface Command extends Serializable{

}
