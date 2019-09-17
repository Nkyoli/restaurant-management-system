package com.restaurant.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.restaurant.management.dto.UserDto;
import com.restaurant.management.model.User;

public interface UserService extends UserDetailsService {
	List<User> findAll();
	
	User save(UserDto customerDto);
	
	void delete(User customer);
	
	Optional<User> findByUsername(String name);
	
	Optional<User> findByEmail(String email);
	
}
