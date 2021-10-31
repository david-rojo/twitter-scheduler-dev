package com.mastercloudapps.twitterscheduler.application.common;

import org.slf4j.Logger;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.State;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;

/**
 * Wrap over the {@link CircuitBreaker} class to help executing message consumption process and manage failures and states.
 */
public class CircuitBreakerHelper {

	private final CircuitBreaker circuitBreaker;

	private final Logger logger;

	public CircuitBreakerHelper(final CircuitBreaker circuitBreaker, final Logger logger) {

		this.logger = logger;
		this.circuitBreaker = circuitBreaker;
		this.circuitBreaker.getEventPublisher().onStateTransition(this::circuitBreakEventListener);
	}

	/**
	 * Run a code under the {@link CircuitBreaker} using the {@link Try} library.
	 *
	 * @param runnable code to execute.
	 * @return Try object to know the result of the previous execution.
	 */
	public Try<Void> run(CheckedRunnable runnable) {

		CheckedRunnable decorateHandleEventProcess =
				CircuitBreaker.decorateCheckedRunnable(this.circuitBreaker, runnable);

		return Try.run(decorateHandleEventProcess)
				.onSuccess(ignore -> logger.debug("Twitter scheduler event handle success."))
				.onFailure(ex -> {

					if (!(ex instanceof io.github.resilience4j.circuitbreaker.CallNotPermittedException)) {

						logger.error("Exception on handle Twitter scheduler event.", ex);
					}
				});
	}

	/**
	 * Run a code under the {@link CircuitBreaker} using the {@link Try} library.
	 *
	 * @param function code to execute.
	 * @return Try object to know the result of the previous execution.
	 */
	public <R> Try<R> of(CheckedFunction0 function) {

		CheckedFunction0 decorateHandleEventProcess =
				CircuitBreaker.decorateCheckedSupplier(this.circuitBreaker, function);

		return Try.of(decorateHandleEventProcess)
				.onSuccess(ignore -> logger.debug("Twitter scheduler event handle success."))
				.onFailure(ex -> {

					if (!(ex instanceof io.github.resilience4j.circuitbreaker.CallNotPermittedException)) {

						logger.error("Exception on handle Twitter scheduler event.", ex);
					}
				});
	}

	public void circuitBreakEventListener(final CircuitBreakerEvent circuitBreakerEvent) {

		this.logger.info("New Event circuit Break : {}", circuitBreakerEvent);
	}

	public boolean isCircuitBreakerOpen() {

		final State state = this.circuitBreaker.getState();
		return state == State.FORCED_OPEN || state == State.OPEN;
	}

}
