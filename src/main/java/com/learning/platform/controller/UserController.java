package com.learning.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.dto.UserDto;
import com.learning.platform.model.User;
import com.learning.platform.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserId(@PathVariable Long id) {
		User userResponse = userService.getUserById(id);

		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserDto user) {
		ApiResponse<User> userResponse = userService.createUser(user);

		HttpStatus status = userResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity(userResponse, status);

	}

}
