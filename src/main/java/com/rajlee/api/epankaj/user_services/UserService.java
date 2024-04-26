package com.rajlee.api.epankaj.user_services;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.models.Users;

import java.util.List;


public interface UserService  {

    public Users findById(long id);

    public Users findbyName(String name);

    public Users findByEmail(String email);

    public List<Users> getAllUsers();

    public Users updateUsersById(long id, Users users);

    public Users addnewUser(UserDTO users);

    public void deleteUserById(long id);
}
