package com.rajlee.api.epankaj.controllers;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.jwtservice.JwtService;
import com.rajlee.api.epankaj.models.LoginResponse;
import com.rajlee.api.epankaj.models.UserDetailsImpl;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import com.rajlee.api.epankaj.user_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epankaj/v.0/users")
public class UserController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private Userrepository userrepository;

    @GetMapping("/hello")
    public String greet(){
        return "Hello brother";
    }

    @GetMapping("/getall")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            System.out.println(userDetails.getUserEmail()+userDetails.getUsername()+userDetails.getUserImage());
            System.out.println("iske baad");
            UserDTO userDetailsDTO = new UserDTO(userDetails.getUsername(), userDetails.getUserEmail(), userDetails.getUserImage());
            System.out.println("Inside me end point"+userDetailsDTO.getName()+" "+userDetailsDTO.getEmail());
            return ResponseEntity.ok(userDetailsDTO);
        } else {
            // Handle case where user details are not available
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id/{id}")
    public Users getUserById(@PathVariable("id") long id){
        System.out.println("safe api hitted");
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserDTO user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getEmail());
            Users loggedInUser = userrepository.getUserByEmail(user.getEmail());
            LoginResponse loginResponse = new LoginResponse(token, loggedInUser.getId(), loggedInUser.getEmail());
            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
    }

}
