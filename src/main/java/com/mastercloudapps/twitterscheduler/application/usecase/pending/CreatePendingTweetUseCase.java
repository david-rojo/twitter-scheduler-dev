package com.mastercloudapps.twitterscheduler.application.usecase.pending;

import com.mastercloudapps.twitterscheduler.application.model.operation.CreatePendingTweetOperation;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;

public interface CreatePendingTweetUseCase {

	PendingTweet createPendingTweet(CreatePendingTweetOperation operation);

}
