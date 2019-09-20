package com.restaurant.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.management.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, String>{

	Optional<Menu> findById(String id);
	
	Optional<Menu> findByName(String id);
}
