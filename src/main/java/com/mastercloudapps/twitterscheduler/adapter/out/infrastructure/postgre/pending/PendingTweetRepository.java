package com.mastercloudapps.twitterscheduler.adapter.out.infrastructure.postgre.pending;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingTweetRepository extends JpaRepository<PendingTweet, Long>{

}
