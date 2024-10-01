package com.learning.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Enrollment;
import com.learning.platform.repository.EnrollmentRepository;

public class EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	public ApiResponse<Enrollment> getEnrollment(Long id) {
		Enrollment enrollment = enrollmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("enrollment not found"));

		return ApiResponse.success(enrollment, "enrollment returen", HttpStatus.OK);

	}

	public ApiResponse<Enrollment> createEnrollment(Enrollment enrollment) {

		try {
			if (enrollment != null) {
				return ApiResponse.error("enrollmet is already deone", HttpStatus.CONFLICT);
			} else {
				Enrollment enrollment2 = enrollmentRepository.save(enrollment);
				return ApiResponse.success(enrollment2, "enrollment saved", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception

			return ApiResponse.error("somethig went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
