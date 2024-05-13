package com.rajlee.api.epankaj.user_services;

import com.rajlee.api.epankaj.models.UserDetailsImpl;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private Userrepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.getUserByEmail(username);
        if(user==null){
            System.out.println("Error 404 not fount");
            throw new UsernameNotFoundException("Not found user 404 Error");
        }
        return new UserDetailsImpl(user);
    }
}
