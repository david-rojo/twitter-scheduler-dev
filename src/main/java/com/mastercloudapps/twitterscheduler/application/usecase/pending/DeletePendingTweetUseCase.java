package com.mastercloudapps.twitterscheduler.application.usecase.pending;

import com.mastercloudapps.twitterscheduler.application.model.command.DeletePendingTweetRequest;

public interface DeletePendingTweetUseCase {

	public void deletePendingTweet(DeletePendingTweetRequest request);
}
