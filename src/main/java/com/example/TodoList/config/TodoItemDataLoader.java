package com.example.TodoList.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.TodoList.repositories.TodoItemRepository;

@Component
public class TodoItemDataLoader implements CommandLineRunner{
    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception{
        loadSeedData();
    }

    private void loadSeedData(){
        logger.info("Number of TodoItems: {}", todoItemRepository.count());
    }
}

