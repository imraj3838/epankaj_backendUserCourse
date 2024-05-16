package com.rajlee.api.epankaj.user_services;

import com.rajlee.api.epankaj.dtos.UserDTO;
import com.rajlee.api.epankaj.models.Role;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import com.rajlee.api.epankaj.rolService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private RoleService roleService;

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
            return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        return userrepository.findAll();
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

        Role defaultRole = roleService.getRoleByName("ROLE_USER");
        user.getRoles().add(defaultRole);

        return userrepository.save(user);

    }

//    public void initUsers() {
//        roleService.initRoles(); // Create roles if not already created
//
//        // Create a sample user with the default role
//        Users sampleUser = new Users();
//        sampleUser.setName("sampleUsername");
//        sampleUser.setEmail("sample@example.com");
//        sampleUser.setPassword(passwordEncoder.encode("samplePassword"));
//
//        Role defaultRole = roleService.getRoleByName("ROLE_USER");
//        sampleUser.getRoles().add(defaultRole);
//
//        userrepository.save(sampleUser);
//    }

    @Override
    public void deleteUserById(long id) {
        userrepository.deleteById(id);
    }

    @Override
    public List<Users> findUserssWithSorting(String field) {
        return  userrepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Users> findUsersWithPagination(int offset, int pageSize) {
        return userrepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Users> findUsersWithPaginationAndSorting(int offset, int pageSize, String field) {
        return userrepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
