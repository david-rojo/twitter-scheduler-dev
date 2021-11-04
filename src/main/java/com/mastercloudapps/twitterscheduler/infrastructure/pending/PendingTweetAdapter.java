package com.mastercloudapps.twitterscheduler.infrastructure.pending;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.domain.exception.RepositoryException;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweet;
import com.mastercloudapps.twitterscheduler.domain.pending.PendingTweetPort;
import com.mastercloudapps.twitterscheduler.infrastructure.postgre.pending.PendingTweetJpaEntity;
import com.mastercloudapps.twitterscheduler.infrastructure.postgre.pending.PendingTweetJpaMapper;
import com.mastercloudapps.twitterscheduler.infrastructure.postgre.pending.PendingTweetJpaRepository;

@Component
public class PendingTweetAdapter implements PendingTweetPort {
		
	private static final String DATA_ACCESS_ERROR = "An unexpected error occurred executing a data access operation";

	private PendingTweetJpaRepository pendingTweetJpaRepository;
	
	private PendingTweetJpaMapper mapper;
	
	public PendingTweetAdapter(PendingTweetJpaRepository pendingTweetJpaRepository,
			PendingTweetJpaMapper mapper) {
		
		this.pendingTweetJpaRepository = pendingTweetJpaRepository;
		this.mapper = mapper;
	}
	
	@Override	
	public PendingTweet createPendingTweet(PendingTweet pendingTweet) {
		
		try {
			PendingTweetJpaEntity pendingTweetJpaEntity = mapper.mapCreatePendingTweet(pendingTweet);			
			pendingTweetJpaRepository.save(pendingTweetJpaEntity);
			
			PendingTweetJpaEntity pendingTweetJpaResponse = pendingTweetJpaRepository
					.findById(pendingTweetJpaEntity.getId())
					.orElseThrow();
			
			return mapper.mapEntity(pendingTweetJpaResponse);
			
		} catch (DataAccessException ex) {

			throw new RepositoryException(DATA_ACCESS_ERROR, ex);
		}
	}

	@Override
	public void deletePendingTweet(Long id) {
		
		pendingTweetJpaRepository.deleteById(id);
		
	}

	@Override
	public Collection<PendingTweet> findAll() {
		
		return pendingTweetJpaRepository.findAll().stream()
				.map(entity -> mapper.mapEntity(entity))
				.collect(Collectors.toList());
	}

}
