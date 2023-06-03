package com.ohusiev.budgettracker.persistence.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ohusiev.budgettracker.persistence.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<UserDetails> findByUsername(String username);
}