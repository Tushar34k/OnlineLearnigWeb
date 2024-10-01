package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.MockTest;

public interface MockTestRepository extends JpaRepository<MockTest, Long> {

}
