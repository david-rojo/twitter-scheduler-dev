package com.mastercloudapps.twitterscheduler.infrastructure.jpa.pending;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingTweetImageJpaRepository extends JpaRepository<PendingTweetImageJpaEntity, Long>{

}
