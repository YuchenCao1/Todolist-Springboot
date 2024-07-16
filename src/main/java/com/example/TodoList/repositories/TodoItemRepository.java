package com.example.TodoList.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.TodoList.models.TodoItem;

public interface  TodoItemRepository extends  CrudRepository<TodoItem, Long>{
    
}
