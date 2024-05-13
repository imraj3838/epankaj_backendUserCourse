package com.rajlee.api.epankaj.repositories;

import com.rajlee.api.epankaj.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Userrepository extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u WHERE u.name = :username")
    Users findUserByUsername(@Param("username") String username);

    Users findById(long id);

    Users findByEmail(String email);

    List<Users> findAll();

    void deleteById(long id);

    Users getUserByEmail(String username);

    Users findByResetToken(String token);
}
