package com.learning.platform.apiresponse;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

	private boolean success;
	private String message;
	private T data;
	private HttpStatus httpStatus;

	public ApiResponse(boolean success, String message, T data, HttpStatus httpStatus) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
		this.httpStatus = httpStatus;
	}

	

	public static <T> ApiResponse<T> success(T data, String message, HttpStatus httpStatus) {
		return new ApiResponse<>(true, message, data, httpStatus);
	}

	public static <T> ApiResponse<T> error(String message, HttpStatus httpStatus) {
		return new ApiResponse<>(false, message, null, httpStatus);
	}

}
