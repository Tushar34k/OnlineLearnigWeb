package com.learning.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Assignment;
import com.learning.platform.service.AssignmentService;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;

	@PostMapping("/save")
	private ResponseEntity<ApiResponse<Assignment>> createAssignment(@RequestBody Assignment assignment) {
		ApiResponse<Assignment> as = assignmentService.createAssignment(assignment);

		HttpStatus httpStatus = as.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity(as, httpStatus);

	}

}
