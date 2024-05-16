package com.rajlee.api.epankaj.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/epankaj/v.0/admin")
public class AdminController {

    @GetMapping("/hello")
    public String helloAdmin(){
        return "Hello admin";
    }
}
