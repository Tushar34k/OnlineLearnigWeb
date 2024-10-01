package com.learning.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.Quize;
import com.learning.platform.repository.QuizeRepository;

@Service
public class QuizeService {

	@Autowired
	private QuizeRepository quizeRepository;

	public ApiResponse<Quize> getQuizeById(Long id) {
		Quize quize = quizeRepository.findById(id).orElseThrow();

		return ApiResponse.success(quize, "quize are saved", HttpStatus.OK);
	}

	public ApiResponse<Quize> createQuize(Quize quize) {
		try {
			if (quize != null) {
				return ApiResponse.error("alredy present", HttpStatus.OK);
			} else {
				Quize quize1 = quizeRepository.save(quize);
				return ApiResponse.success(quize1, "quize save", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception

			return ApiResponse.error("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
