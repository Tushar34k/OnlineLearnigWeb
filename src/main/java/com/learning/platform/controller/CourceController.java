package com.learning.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.dto.CourceDto;
import com.learning.platform.model.Course;
import com.learning.platform.service.CourceService;

@RestController
@RequestMapping("/api/cource")
public class CourceController {

	@Autowired
	private CourceService courceService;

	@PostMapping("/save")
	public ResponseEntity<ApiResponse<Course>> createCource(@RequestBody CourceDto courceDto) {
		ApiResponse<Course> couResponse = courceService.createCource(courceDto);

		HttpStatus httpStatus = couResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;

		return new ResponseEntity(couResponse, httpStatus);
	}

}
