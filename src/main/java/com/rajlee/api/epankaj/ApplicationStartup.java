package com.rajlee.api.epankaj;

import com.rajlee.api.epankaj.user_services.UserService;
import com.rajlee.api.epankaj.user_services.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements CommandLineRunner {
    private final UserServiceImpl userService;

    public ApplicationStartup(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.initUsers();
        return;
    }

}
