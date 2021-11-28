package com.mastercloudapps.twitterscheduler.infrastructure.jpa.pending;

import java.time.Instant;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="PENDING_TWEET")
public class PendingTweetJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String message;
	
	private Instant publicationDate;
	
	private Instant createdAt;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="pendingTweet", fetch = FetchType.EAGER)
    private Collection<PendingTweetImageJpaEntity> images;
	
}
