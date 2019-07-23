package pairFeedBack.repository;

import org.springframework.data.repository.CrudRepository;

import pairFeedBack. entity.User;

public interface UserRepository extends CrudRepository<User, Long>{}