package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
