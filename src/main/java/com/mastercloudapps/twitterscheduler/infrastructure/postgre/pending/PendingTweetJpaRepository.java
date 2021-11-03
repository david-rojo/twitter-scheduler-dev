package com.mastercloudapps.twitterscheduler.infrastructure.postgre.pending;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingTweetJpaRepository extends JpaRepository<PendingTweetJpaEntity, Long>{

}
