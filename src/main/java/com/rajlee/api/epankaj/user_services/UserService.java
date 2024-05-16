package com.rajlee.api.epankaj.user_services;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.models.Users;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService  {

    public Users findById(long id);

    Users getCurrentUser();

    public Users findbyName(String name);

    public Users findByEmail(String email);

    public List<Users> getAllUsers();

    public Users updateUsersById(long id, Users users);

    public Users addnewUser(UserDTO users);

    public void deleteUserById(long id);

    List<Users> findUserssWithSorting(String field);

    Page<Users> findUsersWithPagination(int offset, int pageSize);

    Page<Users> findUsersWithPaginationAndSorting(int offset, int pageSize, String field);
}
