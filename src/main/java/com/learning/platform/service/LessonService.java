package com.learning.platform.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.customexception.CourceNotFoundException;
import com.learning.platform.customexception.UserNotFoundException;
import com.learning.platform.dto.LessonDTO;
import com.learning.platform.model.Course;
import com.learning.platform.model.Lesson;
import com.learning.platform.repository.CourseRepository;
import com.learning.platform.repository.LessonRepository;

@Service
public class LessonService {

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private CourseRepository courseRepository;

	public ApiResponse<Lesson> getLessonById(Long id) {

		Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("lesson not found"));

		return ApiResponse.success(lesson, "lessong saved", HttpStatus.OK);
	}

	public ApiResponse<Lesson> createLesson(LessonDTO lesson) {

		// Validate that the course ID is not null
		if (lesson.getCourse_id() == null) {
			return ApiResponse.error("Course ID must not be null", HttpStatus.BAD_REQUEST);
		}

		Course course = courseRepository.findById(lesson.getCourse_id())
				.orElseThrow(() -> new CourceNotFoundException("cource not found"));

		try {
			if (lessonRepository.findByName(lesson.getName()).isPresent()) {
				return ApiResponse.error("lesson is present", HttpStatus.CONFLICT);

			} else {
				Lesson lesson1 = new Lesson();
				lesson1.setName(lesson.getName());

				lesson1.setCourse(course);
				Lesson ls = lessonRepository.save(lesson1);
				return ApiResponse.success(ls, "saved ", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ApiResponse.error("some thing went wrong" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
