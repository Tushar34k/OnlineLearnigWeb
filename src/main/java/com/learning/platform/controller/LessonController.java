package com.learning.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.dto.LessonDTO;
import com.learning.platform.model.Lesson;
import com.learning.platform.service.LessonService;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

	@Autowired
	private LessonService lessonService;

	@PostMapping("/save")
	public ResponseEntity<ApiResponse<Lesson>> createLesson(@RequestBody LessonDTO lesson) {
		ApiResponse<Lesson> less = lessonService.createLesson(lesson);

		HttpStatus http = less.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity(less, http);
	}

}
