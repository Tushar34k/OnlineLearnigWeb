package com.learning.platform.model;

import java.util.List;
import java.util.Set;

import com.learning.platform.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	private String address;

	private String password; // Ensure password is hashed before storing

	@Email
	@NotBlank
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private Set<Course> courses;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<MockTest> mockTests;
}
