package com.learning.platform.dto;

import com.learning.platform.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDto {
	
	private String name;
	private String address;
	private String email;
	
	private String password;
	
	private Role role;

}
