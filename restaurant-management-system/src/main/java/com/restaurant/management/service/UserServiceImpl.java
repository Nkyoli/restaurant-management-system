package com.restaurant.management.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.management.dto.UserDto;
import com.restaurant.management.model.Role;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAccess(userDto.getAccess());
		user.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));
		User saveUser = userRepository.save(user);
		
		return saveUser;
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);

	}

	@Override
	public Optional<User> findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("Invalid Username or password!");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), rolesMapping(user.get().getRoles()));
	}
	
	private Collection< ? extends GrantedAuthority> rolesMapping(Collection<Role> roles) {
		return roles.stream()
				    .map(role -> new SimpleGrantedAuthority(role.getRole()))
					.collect(Collectors.toList());
	}
}
