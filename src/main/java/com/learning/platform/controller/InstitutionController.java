package com.learning.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Institution;
import com.learning.platform.service.InstitutionService;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {

	@Autowired
	private InstitutionService institutionService;

	@PostMapping("/save")
	public ResponseEntity<ApiResponse<Institution>> createInstitution(@RequestBody Institution institution) {
		ApiResponse<Institution> inApiResponse = institutionService.createInstitution(institution);
		HttpStatus http = inApiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;
		return new ResponseEntity(inApiResponse, http);
	}

}
