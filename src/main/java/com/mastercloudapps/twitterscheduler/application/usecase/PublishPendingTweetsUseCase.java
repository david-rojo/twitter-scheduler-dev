package com.mastercloudapps.twitterscheduler.application.usecase;

import java.time.Instant;

public interface PublishPendingTweetsUseCase {

	public void publishPendingTweets(Instant current);

}
