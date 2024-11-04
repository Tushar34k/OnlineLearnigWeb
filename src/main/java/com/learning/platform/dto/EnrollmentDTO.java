package com.learning.platform.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class EnrollmentDTO {

	private String name;
	private Date date;
	private Double totalPrice;
	private Set<Long> courseIds; // IDs of courses to be enrolled in
	private Long userId; // ID of the user
}
