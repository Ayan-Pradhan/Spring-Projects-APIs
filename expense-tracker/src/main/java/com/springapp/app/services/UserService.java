package com.springapp.app.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springapp.app.dto.ResponseDto;
import com.springapp.app.dto.UserDto;
import com.springapp.app.entities.User;
import com.springapp.app.enums.ExecutionMessages;
import com.springapp.app.exception.RecordNotFoundException;
import com.springapp.app.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;
	private final ModelMapper mapper;
	
	public ResponseDto registerUser(UserDto userDto) {
		if(repository.findById(userDto.getEmail()).isPresent()) {
			return new ResponseDto(false, "User already exists with this email", ExecutionMessages.DEFAULT_VALUE.value());
		}
		userDto.setPassword(encoder.encode(userDto.getPassword()));
		User user = mapper.map(userDto, User.class);
		return new ResponseDto(true, "Registration Successful",repository.save(user));
	}
	
	public User getUser(String id) {
		return repository.findById(id)
		.orElseThrow(()->new RecordNotFoundException("User record not found"));
	}
	
	public List<User> getAllUser(){		
		return Optional.of(repository.findAll())
				.orElseThrow(()->new RecordNotFoundException("Users not present yet"));
	}

}
