package pairFeedBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pairFeedBack. entity.User;

public interface UserRepository extends JpaRepository<User, Long>{}