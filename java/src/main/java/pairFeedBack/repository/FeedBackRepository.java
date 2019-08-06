package pairFeedBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pairFeedBack.entity.FeedBack;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long>{
    
    
}