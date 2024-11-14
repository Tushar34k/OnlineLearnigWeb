//package com.learning.platform.controller;
//
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.learning.platform.apiresponse.ApiResponse;
//import com.learning.platform.dto.EnrollmentDTO;
//import com.learning.platform.model.Enrollment;
//import com.learning.platform.service.EnrollmentService;
//
//@RestController
//@RequestMapping("/api/enrollment")
//public class EnrollmentController {
//
//	@Autowired
//	private Enro enrollmentService;
//
//	// Enroll in multiple courses with payment option (FULL or INSTALLMENT)
//	@PostMapping("/enroll")
//	public ResponseEntity<?> enrollCourses(@RequestParam Long userId, @RequestParam Set<Long> courseIds,
//			@RequestParam String paymentOption, @RequestBody EnrollmentDTO enrollmentDTO) {
//		enrollmentService.enrollCourses(userId, courseIds, paymentOption, enrollmentDTO);
//		return ResponseEntity
//				.ok("Enrollment successful for multiple courses with " + paymentOption + " payment option");
//	}
//
//	// Complete the second installment payment
//	@PostMapping("/complete-installment/{enrollmentId}")
//	public ResponseEntity<?> completeInstallmentPayment(@PathVariable Long enrollmentId) {
//		enrollmentService.completeInstallmentPayment(enrollmentId);
//		return ResponseEntity.ok("Installment payment completed");
//	}
//}