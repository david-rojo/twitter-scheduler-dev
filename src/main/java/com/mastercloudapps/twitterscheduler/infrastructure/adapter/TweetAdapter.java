package com.mastercloudapps.twitterscheduler.infrastructure.adapter;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mastercloudapps.twitterscheduler.domain.tweet.Tweet;
import com.mastercloudapps.twitterscheduler.domain.tweet.TweetPort;
import com.mastercloudapps.twitterscheduler.infrastructure.postgre.tweet.TweetJpaMapper;
import com.mastercloudapps.twitterscheduler.infrastructure.postgre.tweet.TweetJpaRepository;

@Component
public class TweetAdapter implements TweetPort {

	private static final String DATA_ACCESS_ERROR = "An unexpected error occurred executing a data access operation";

	private TweetJpaRepository tweetJpaRepository;
	
	private TweetJpaMapper mapper;
	
	public TweetAdapter(TweetJpaRepository tweetJpaRepository,
			TweetJpaMapper mapper) {
		
		this.tweetJpaRepository = tweetJpaRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Tweet create(Tweet tweet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Tweet> findAll() {

		return tweetJpaRepository.findAll().stream()
				.map(entity -> mapper.mapEntity(entity))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Tweet> findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
