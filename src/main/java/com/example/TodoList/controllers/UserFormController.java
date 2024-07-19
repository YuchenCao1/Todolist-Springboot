package com.example.TodoList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.TodoList.models.User;
import com.example.TodoList.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserFormController {
     @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-login")
    public String userLogin(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/user-register")
    public String userRegister(User user){
        return "register";
    }

    @GetMapping("/forget-password")
    public String forgetPassword(User user){
        return "forgetpassword";
    }

    @GetMapping("/user-logout")
    public String userLogout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/user-login";
    }
}
