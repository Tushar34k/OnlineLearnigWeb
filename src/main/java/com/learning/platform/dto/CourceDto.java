package com.learning.platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CourceDto {
	
	private String name;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Double price;
    private Double installment;

}
