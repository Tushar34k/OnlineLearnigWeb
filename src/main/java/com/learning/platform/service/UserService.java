package com.learning.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.User;
import com.learning.platform.repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ApiResponse<?> getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

		return ApiResponse.success(true, "user is returne", HttpStatus.OK);
	}

	public ApiResponse<User> createUser(User user) {
		try {
			if (user != null) {
				return ApiResponse.error("user is alredy prsent", HttpStatus.CONFLICT);
			} else {
				User user1 = userRepository.save(user);
				return ApiResponse.success(user1, "user is saved", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ApiResponse.error("An error occured" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
