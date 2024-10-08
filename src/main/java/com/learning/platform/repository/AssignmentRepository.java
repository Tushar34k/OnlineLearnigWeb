package com.learning.platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

	Optional<Assignment> findByName(String name);

}
