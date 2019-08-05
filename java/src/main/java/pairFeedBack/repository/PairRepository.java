package pairFeedBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pairFeedBack.entity.Pair;

public interface PairRepository extends JpaRepository<Pair, Long>{
    
}