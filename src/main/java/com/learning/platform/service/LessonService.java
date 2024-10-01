package com.learning.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Lesson;
import com.learning.platform.repository.LessonRepository;

public class LessonService {

	@Autowired
	private LessonRepository lessonRepository;

	private ApiResponse<Lesson> getLessonById(Long id) {

		Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("lesson not found"));

		return ApiResponse.success(lesson, "lessong saved", HttpStatus.OK);
	}

	private ApiResponse<Lesson> createLesson(Lesson lesson) {
		try {
			if (lesson != null) {
				return ApiResponse.error("lesson is present", HttpStatus.CONFLICT);

			} else {
				Lesson lesson1 = lessonRepository.save(lesson);
				return ApiResponse.success(lesson1, "saved ", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ApiResponse.error("some thing went wrong" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
