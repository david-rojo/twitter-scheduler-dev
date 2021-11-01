package com.mastercloudapps.twitterscheduler.adapter.in.controller.pending.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PendingTweetRequest {

	private String message;
	private List<PendingImageRequest> images;
	private String publicationDate;
	
}
