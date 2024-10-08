package com.learning.platform.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.dto.EnrollmentDTO;
import com.learning.platform.model.Enrollment;
import com.learning.platform.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody EnrollmentDTO enrollment) {
        // Use the logger instance to log the request
        logger.info("Received request to create enrollment with name: {}", enrollment.getName());

        // Call the service to create enrollment
        ApiResponse<Enrollment> enApiResponse = enrollmentService.createEnrollment(enrollment);

        // Set HTTP status based on success or conflict
        HttpStatus http = enApiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<>(enApiResponse, http);
    }
}
