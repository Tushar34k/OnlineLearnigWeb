package com.learning.platform.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.customexception.EnrollmentException;
import com.learning.platform.customexception.UserNotFoundException;
import com.learning.platform.dto.EnrollmentDTO;
import com.learning.platform.model.Course;
import com.learning.platform.model.Enrollment;
import com.learning.platform.model.User;
import com.learning.platform.repository.CourseRepository;
import com.learning.platform.repository.EnrollmentRepository;
import com.learning.platform.repository.UserRepository;

@Service
public class EnrollmentService {

	private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	public ApiResponse<Enrollment> createEnrollment(EnrollmentDTO enrollmentDTO) {
		try {

			// Fetch user by ID
			User user = userRepository.findById(enrollmentDTO.getUserId()).orElseThrow(
					() -> new UserNotFoundException("User not found with ID: " + enrollmentDTO.getUserId()));

			// Fetch courses by their IDs
			Set<Course> courses = enrollmentDTO.getCourseIds().stream()
					.map(courseId -> courseRepository.findById(courseId)
							.orElseThrow(() -> new RuntimeException("Course not found for ID: " + courseId)))
					.collect(Collectors.toSet());

			if (enrollmentRepository.existsByUserAndCourses(user, courses)) {
				throw new EnrollmentException("user is User is already enrolled in one of these courses.");
			}

			// Validate enrollment name
			if (enrollmentDTO.getName() == null || enrollmentDTO.getName().isEmpty()) {
				return ApiResponse.error("Enrollment name cannot be null or empty", HttpStatus.BAD_REQUEST);
			}

			// Check if enrollment with the same name exists
			Optional<Enrollment> existingEnrollment = enrollmentRepository.findByName(enrollmentDTO.getName());
			if (existingEnrollment.isPresent()) {
				logger.warn("Enrollment with name {} already exists.", enrollmentDTO.getName());
				return ApiResponse.error("Enrollment with this name already exists", HttpStatus.CONFLICT);
			}

			// Create new enrollment and set fields
			Enrollment newEnrollment = new Enrollment();
			newEnrollment.setName(enrollmentDTO.getName());
			newEnrollment.setTotalPrice(enrollmentDTO.getTotalPrice());
			newEnrollment.setDate(new Date());
			newEnrollment.setUser(user);
			newEnrollment.setCourses(courses);

			// Save enrollment to the database
			Enrollment savedEnrollment = enrollmentRepository.save(newEnrollment);

			logger.info("Enrollment created successfully with ID: {}", savedEnrollment.getId());

			return ApiResponse.success(savedEnrollment, "Enrollment saved successfully", HttpStatus.CREATED);
		} catch (UserNotFoundException e) {
			logger.error("Error creating enrollment: {}", e.getMessage());
			return ApiResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (RuntimeException e) {
			logger.error("Error creating enrollment: {}", e.getMessage());
			return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error("Unexpected error occurred: ", e);
			return ApiResponse.error("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
