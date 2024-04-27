package com.rajlee.api.epankaj.controllers;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.user_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epankaj/v.0/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public Users getUserById(@PathVariable("id") long id){
        return userService.findById(id);
    }

    @PostMapping("/save")
    public Users addUsers(@RequestBody UserDTO userDTO) {
        return userService.addnewUser(userDTO);
    }

    @GetMapping("/name/{name}")
    public Users getUserByname(@PathVariable("name") String name){
        return userService.findbyName(name);
    }

    @GetMapping("/email/{email}")
    public Users getUserByEmail(@PathVariable("email") String email){
        return userService.findByEmail(email);
    }

    @PatchMapping("/update/{id}")
    public Users updateUsers(@PathVariable("id") long id, @RequestBody Users users){
        return userService.updateUsersById(id,users);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
    }

}
