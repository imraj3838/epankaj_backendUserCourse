package com.rajlee.api.epankaj.controllers;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.user_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/epankaj/v1.0/users")
public class HomeController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home() {
        return "Welcome to the home page!";
    }

    @PostMapping("/saveUser")
    public Users addUsers(@RequestBody UserDTO userDTO) {
        return userService.addnewUser(userDTO);
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome to the admin page!";
    }
}
