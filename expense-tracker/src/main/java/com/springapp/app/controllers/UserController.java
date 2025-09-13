package com.springapp.app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.app.dto.ResponseDto;
import com.springapp.app.dto.UserDto;
import com.springapp.app.entities.User;
import com.springapp.app.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService service;

	@PostMapping("/sign-up")
	public ResponseEntity<ResponseDto> register(@Valid @RequestBody UserDto user) {
		return ResponseEntity.ok(service.registerUser(user));
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		return ResponseEntity.ok(service.getUser(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(service.getAllUser());
	}

}
