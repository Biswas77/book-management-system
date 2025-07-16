package com.CRUDOP.ProductManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDOP.ProductManagementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
