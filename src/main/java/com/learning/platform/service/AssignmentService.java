package com.learning.platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Assignment;
import com.learning.platform.repository.AssignmentRepository;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assignmentRepository;

	public ApiResponse<Assignment> createAssignment(Assignment assignment) {
		Optional<Assignment> assOptional = assignmentRepository.findByName(assignment.getName());
		try {
			if (assOptional.isPresent()) {
				return ApiResponse.error("user is alredy present", HttpStatus.CONFLICT);
			} else {
				Assignment assignment2 = new Assignment();
				assignment2.setName(assignment.getName());
				assignment2.setLessons(assignment.getLessons());

				Assignment a = assignmentRepository.save(assignment2);
				return ApiResponse.success(a, "assignment saved", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ApiResponse.error("somehting went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
