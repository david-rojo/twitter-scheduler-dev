package com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.dto;

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
public class PendingTweetResponse {

	private Long pendingTweetId;
	private String message;
	private List<PendingImageResponse> images;
	private String publicationDate;
	private String createdAt;
	private String updatedAt;

}
