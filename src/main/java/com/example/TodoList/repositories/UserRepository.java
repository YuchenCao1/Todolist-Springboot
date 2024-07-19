package com.example.TodoList.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TodoList.models.User;

public interface  UserRepository extends  JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
