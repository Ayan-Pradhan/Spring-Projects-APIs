package com.springapp.app.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springapp.app.configs.ExpenseTrackerPasswordConfig;
import com.springapp.app.entities.User;
import com.springapp.app.repo.UserRepository;

@Service
public class TrackerUserDetailsService implements UserDetailsService{

	private UserRepository repository;
	
	public TrackerUserDetailsService(UserRepository repository, ExpenseTrackerPasswordConfig passwordConfig) {
		super();
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findById(username);
				
		return org.springframework.security.core.userdetails.User
				.withUsername(user.get().getEmail())
				.password(user.get().getPassword())	
				.build();
	}

	
}
