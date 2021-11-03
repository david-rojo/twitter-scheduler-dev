package com.mastercloudapps.twitterscheduler.application.usecase.pending;

import com.mastercloudapps.twitterscheduler.application.model.operation.DeletePendingTweetOperation;

public interface DeletePendingTweetUseCase {

	public void deletePendingTweet(DeletePendingTweetOperation request);
}
