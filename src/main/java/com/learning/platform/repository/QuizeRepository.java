package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Quize;

public interface QuizeRepository extends JpaRepository<Quize, Long> {

}
