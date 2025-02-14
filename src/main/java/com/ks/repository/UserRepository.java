package com.ks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ks.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}