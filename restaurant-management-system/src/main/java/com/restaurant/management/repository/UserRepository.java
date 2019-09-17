package com.restaurant.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.management.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String name);
	
	Optional<User> findByEmail(String email);
	
}
