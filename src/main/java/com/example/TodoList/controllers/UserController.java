package com.example.TodoList.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.TodoList.models.User;
import com.example.TodoList.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    
     @PostMapping("/logining")
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("org.springframework.validation.BindingResult.todo", result);
            return "login";
        }
        
        if (user.getPassword().equals("")){
            model.addAttribute("loginError", "Password can not be empty!");
                return "login";
        }

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            if(existingUser.get().getPassword().equals(user.getPassword())){
                request.getSession().setAttribute("user", existingUser.get().getId());
                return "redirect:/"; 
            }
            else{
                model.addAttribute("loginError", "Invalid username or password!");
                return "login";
            }
        } else {
            model.addAttribute("loginError", "Username doesn't exist!");
            return "login";
        }
    }

    @PostMapping("/registering")
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("org.springframework.validation.BindingResult.todo", result);
            return "register"; 
        } 

        if (user.getPassword().equals("")){
            model.addAttribute("registerError", "Password can not be empty!");
                return "register";
        }

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            model.addAttribute("registerError", "Username exists!");
            return "register"; 
        }
        else{
            userRepository.save(user);
            return "redirect:/";
        }
    }

    @PostMapping("/verify")
    public String verify(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        if (user.getUsername().equals("")){
            model.addAttribute("forgetpasswordError", "Username can not be empty!");
            return "forgetpassword"; 
        }
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            if(existingUser.get().getEmail().equals(user.getEmail())){ 
                model.addAttribute("username", user.getUsername());
                return "reset-password"; 
            }
            else{
                model.addAttribute("forgetpasswordError", "Username and email don't match!");
                return "forgetpassword";
            }
        } else {
            model.addAttribute("forgetpasswordError", "Username doesn't exist!");
            return "forgetpassword";
        }
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@Valid User user, BindingResult result, Model model) {
        if (user.getPassword().equals("")){
            model.addAttribute("resetPasswordError", "Password can not be empty!");
            return "reset-password"; 
        }

        if (!user.getPassword().equals(user.getRepassword())) {
            model.addAttribute("resetPasswordError", "Passwords do not match!");
            return "reset-password"; 
        } 
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (!existingUser.isPresent()) {
            model.addAttribute("resetPasswordError", "Invalid user!");
            return "reset-password";  
        }

        User updateUser = existingUser.get();
        updateUser.setPassword(user.getPassword());
        userRepository.save(updateUser);
        return "redirect:/user-login";
        }
}

