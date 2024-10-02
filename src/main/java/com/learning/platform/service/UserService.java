package com.learning.platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.customexception.UserNotFoundException;
import com.learning.platform.dto.UserDto;
import com.learning.platform.model.User;
import com.learning.platform.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));

		return user;
	}

	public ApiResponse<User> createUser(UserDto userDto) {
		try {
			Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());

			if (existingUser.isPresent()) {
				return ApiResponse.error("User with this email already exists", HttpStatus.CONFLICT);
			} else {
				User user = new User();
				user.setName(userDto.getName());
				user.setEmail(userDto.getEmail());
				user.setPassword(userDto.getPassword());
				user.setRole(userDto.getRole());
				user.setAddress(userDto.getAddress());

				User savedUser = userRepository.save(user);
				return ApiResponse.success(savedUser, "User is saved", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return ApiResponse.error("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
