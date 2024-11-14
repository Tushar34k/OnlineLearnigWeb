package com.learning.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Institution;
import com.learning.platform.repository.InstitutionRepo;

@Service
public class InstitutionService {

	@Autowired
	private InstitutionRepo institutionRepo;

	public ApiResponse<Institution> createInstitution(Institution institution) {
		Institution institution2 = institutionRepo.save(institution);
                     
		return ApiResponse.success(institution2, "Institution created", HttpStatus.CREATED);

	}

}
