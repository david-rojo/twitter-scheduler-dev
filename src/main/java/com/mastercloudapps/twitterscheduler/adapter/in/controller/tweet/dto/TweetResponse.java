package com.mastercloudapps.twitterscheduler.adapter.in.controller.tweet.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TweetResponse {

	private Long tweetId;
	private String message;
	private List<TweetImageResponse> images;
	private String requestedPublicationDate;
	private String publishedAt;
	private String createdAt;
	private String updatedAt;

}
