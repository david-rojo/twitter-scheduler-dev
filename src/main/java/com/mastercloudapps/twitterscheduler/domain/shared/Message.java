package com.mastercloudapps.twitterscheduler.domain.shared;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Message implements ValueObject {

	private static final long serialVersionUID = -4273940488364819113L;

	private final String message;

	private Message(final String title) {
		this.message = requireNonNull(title, "Message cannot be null.");
	}

	public static Message valueOf(final String message) {
		return new Message(message);
	}

	public String message() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Message that = (Message) o;
		return Objects.equals(message, that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public String toString() {
		return "Message{message='" + message + '\'' + '}';
	}

}
