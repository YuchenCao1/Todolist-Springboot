package com.example.TodoList.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.TodoList.models.User;
import com.example.TodoList.repositories.UserRepository;

@Component
public class UserDataLoader implements CommandLineRunner{
    private final Logger logger = LoggerFactory.getLogger(UserDataLoader.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        loadSeedData();
    }

    private void loadSeedData(){
        if(userRepository.count() == 0){
            User user = new User("Eric", "hhh123");
        
            userRepository.save(user);
        }
        logger.info("Number of Users: {}", userRepository.count());
    }
}

