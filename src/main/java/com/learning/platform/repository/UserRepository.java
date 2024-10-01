package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
