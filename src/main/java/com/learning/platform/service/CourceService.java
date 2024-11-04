package com.learning.platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.platform.apiresponse.ApiResponse;
import com.learning.platform.dto.CourceDto;
import com.learning.platform.model.Course;
import com.learning.platform.repository.CourseRepository;

@Service
public class CourceService {

	@Autowired
	private CourseRepository courseRepository;

	public ApiResponse<Course> createCource(CourceDto courceDto) {
		Optional<Course> couOptional = courseRepository.findByName(courceDto.getName());

		try {
			if (couOptional.isPresent()) {
				return ApiResponse.error("already present", HttpStatus.CONFLICT);
			} else {
				Course course = new Course();
				course.setName(courceDto.getName());
				course.setStartDate(courceDto.getStartDate());
				course.setEndDate(courceDto.getEndDate());
				course.setPrice(courceDto.getPrice());
				course.setInstallment(courceDto.getInstallment());
				course.setDuration(courceDto.getDuration());
				Course c = courseRepository.save(course);
				return ApiResponse.success(c, "cource save", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception

			return ApiResponse.error("somehting went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
