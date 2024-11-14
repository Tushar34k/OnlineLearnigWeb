package com.learning.platform.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.customexception.UserNotFoundException;
import com.learning.platform.dto.UserDto;
import com.learning.platform.model.Course;
import com.learning.platform.model.Enrollment;
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

	// getUserEnrollment
	public Enrollment getUserEnrollment(Long userId) {
		// Fetch the user by ID or throw an exception if not found
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		// Retrieve the user's enrollments (assuming one-to-one or single enrollment)
		Enrollment enrollment = user.getEnrollments(); // Assuming there's a single enrollment

		// Check if enrollment is null
//		if (enrollment == null) {
//			return ApiResponse.error("No enrollment found for user", HttpStatus.NOT_FOUND);
//		}

		// Return success response with the enrollment details
		return enrollment;
	}

// here i have to create some changes so it give me an empty set so i try to modify 
	// getUserEnrollmentCourses
	public Set<Course> getUserEnrollmentCourses(Long userId) {
		// Fetch the user by ID or throw an exception if not found
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		// Get the course from the enrollment
		Set<Course> course = user.getCourses();

		// Return success response with the course
		return course;
	}

//	getUserAssignment
// getUserPay	

}
