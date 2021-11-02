package com.mastercloudapps.twitterscheduler.adapter.out.infrastructure.postgre.pending;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingTweet {

	@Id
	@GeneratedValue
	private Long id;
	
	private String message;
	
	private Instant publicationDate;
	
}
