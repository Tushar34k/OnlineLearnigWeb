package com.learning.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.model.MockTest;
import com.learning.platform.repository.MockTestRepository;

public class MockTestService {

	@Autowired
	private MockTestRepository mockTestRepository;

	public ApiResponse<MockTest> getMockTest(Long id) {
		MockTest mockTest = mockTestRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("mock test are not found"));

		return ApiResponse.success(mockTest, "mock test found", HttpStatus.OK);

	}

	public ApiResponse<MockTest> createMockTest(MockTest mockTest) {
		try {
			if (mockTestRepository.findByName(mockTest.getName()).isPresent()) {
				return ApiResponse.error("mocktest alreday present", HttpStatus.CONFLICT);
			} else {
				MockTest mockTest1 = mockTestRepository.save(mockTest);
				return ApiResponse.success(mockTest1, "mockTest save", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception

			return ApiResponse.error("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
