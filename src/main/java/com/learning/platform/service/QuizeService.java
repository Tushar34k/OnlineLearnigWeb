package com.learning.platform.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.customexception.UserNotFoundException;
import com.learning.platform.dto.QuizeDto;
import com.learning.platform.model.Course;
import com.learning.platform.model.Quize;
import com.learning.platform.repository.CourseRepository;
import com.learning.platform.repository.QuizeRepository;

@Service
public class QuizeService {

	@Autowired
	private QuizeRepository quizeRepository;

	@Autowired
	private CourseRepository courseRepository;

	public ApiResponse<Quize> getQuizeById(Long id) {
		Quize quize = quizeRepository.findById(id).orElseThrow();

		return ApiResponse.success(quize, "quize are saved", HttpStatus.OK);
	}

	public ApiResponse<Quize> createQuize(QuizeDto quize) {

		try {

			if (quizeRepository.findByName(quize.getName()).isPresent()) {
				return ApiResponse.error("Quiz already present", HttpStatus.CONFLICT);
			}

//			 Fetch the course
			Course course = courseRepository.findById(quize.getCource_id())
					.orElseThrow(() -> new UserNotFoundException("Course not found"));

			// Create new quiz object
			Quize quize1 = new Quize();
			quize1.setName(quize.getName());
			quize1.setMarks(quize.getMarks());
			quize1.setDate(new Date());
			quize1.setCourse(course);

			// Save the quiz
			Quize savedQuize = quizeRepository.save(quize1);

			return ApiResponse.success(savedQuize, "Quiz saved", HttpStatus.CREATED);
		} catch (Exception e) {
			return ApiResponse.error("Something went wrong: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
