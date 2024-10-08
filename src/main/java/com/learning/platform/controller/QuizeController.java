package com.learning.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.dto.QuizeDto;
import com.learning.platform.model.Quize;
import com.learning.platform.service.QuizeService;

@RestController
@RequestMapping("/api/quize")
public class QuizeController {

	@Autowired
	private QuizeService quizeService;

	@GetMapping("/find/{id}")
	public ResponseEntity<Quize> getQuize(@PathVariable Long id) {
		ApiResponse<Quize> quApiResponse = quizeService.getQuizeById(id);

		return new ResponseEntity(quApiResponse, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Quize> createQuize(@RequestBody QuizeDto quize) {
		ApiResponse<Quize> quResponse = quizeService.createQuize(quize);
		HttpStatus http = quResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT;

		return new ResponseEntity(quResponse, http);

	}

}
