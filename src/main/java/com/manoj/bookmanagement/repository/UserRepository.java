package com.manoj.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoj.bookmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);  // Add this method to find users by email
}
