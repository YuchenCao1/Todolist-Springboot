package com.example.TodoList.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TodoList.models.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUserId(Long userId);
}
