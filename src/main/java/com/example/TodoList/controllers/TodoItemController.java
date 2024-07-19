package com.example.TodoList.controllers;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.TodoList.models.TodoItem;
import com.example.TodoList.models.User;
import com.example.TodoList.repositories.TodoItemRepository;
import com.example.TodoList.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
public class TodoItemController {
    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request){
        Object userIdObject = request.getSession().getAttribute("user");
        if (userIdObject == null) {
            return new ModelAndView("redirect:/user-login");
        }

        Long userId;
        try {
            userId = Long.parseLong(userIdObject.toString());
        } catch (NumberFormatException e) {
            logger.error("Failed to parse user ID from session", e);
            return new ModelAndView("redirect:/user-login");
        }
        logger.info("request to GET index");
        
        List<TodoItem> todoItems = todoItemRepository.findByUserId(userId);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItems);
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }
    
     @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }
        Long userId = (Long) request.getSession().getAttribute("user");
        if (userId == null) {
            model.addAttribute("loginError", "You must be logged in to create a todo item.");
            return "login"; 
        }

        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            model.addAttribute("loginError", "Invalid user session.");
            return "login"; 
        }

        todoItem.setUser(user.get()); 
        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable Long id, @Valid TodoItem updatedTodoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todo", updatedTodoItem);
            model.addAttribute("org.springframework.validation.BindingResult.todo", result);
            return "update-todo-item"; 
        } 

        Optional<TodoItem> existingTodoItem = todoItemRepository.findById(id);
        if (!existingTodoItem.isPresent()) {
            model.addAttribute("error", "Todo item not found!");
            return "update-todo-item";
        }

        TodoItem todoItem = existingTodoItem.get();
        updatedTodoItem.setUser(todoItem.getUser());

        updatedTodoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(updatedTodoItem);
        return "redirect:/";
    }
}

