package com.CRUDOP.ProductManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.CRUDOP.ProductManagementSystem.entity.User;
import com.CRUDOP.ProductManagementSystem.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.put("users", users);
        return "userlist";
    }

    @GetMapping("/users/add")
    public String addUserForm(ModelMap model) {
        model.put("user", new User());
        return "userform";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, ModelMap model) {
        userService.saveUser(user);
        model.put("msg", "User saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
