package com.learning.platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.MockTest;

public interface MockTestRepository extends JpaRepository<MockTest, Long> {
	
	Optional<MockTest> findByName(String name);

}
