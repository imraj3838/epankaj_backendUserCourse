package com.rajlee.api.epankaj.user_services;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Userrepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(Userrepository userrepository){
        this.userrepository = userrepository;
    }


    @Override
    public Users findById(long id) {
        return userrepository.findById(id);
    }

        @Override
        public Users getCurrentUser() {
            Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
        }

    @Override
    public Users findbyName(String name) {
        return  userrepository.findUserByUsername(name);
    }

    @Override
    public Users findByEmail(String email) {
        return userrepository.findByEmail(email);
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> us = userrepository.findAll();
        return us;
    }


    @Override
    public Users updateUsersById(long id,Users users) {
        Optional<Users> temp = Optional.ofNullable(userrepository.findById(id));
        if(temp.isEmpty()){
            throw new RuntimeException();
        }
        Users savedUser = temp.get();

        if (savedUser.getName() != null) {
            savedUser.setName(users.getName());
        }
        if (savedUser.getEmail() != null) {
            savedUser.setEmail(users.getEmail());
        }
        if (savedUser.getPassword() != null) {
            savedUser.setPassword(users.getPassword());
        }

        userrepository.save(savedUser);
        return savedUser;
    }

    @Override
    public Users addnewUser(UserDTO users) {
        Users user = new Users();
        user.setName(users.getName());
        user.setEmail(users.getEmail());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        return userrepository.save(user);

    }

    @Override
    public void deleteUserById(long id) {
        userrepository.deleteById(id);
    }
}
