package com.rajlee.api.epankaj.repositories;


import com.rajlee.api.epankaj.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {


    Optional<Object> findByName(String roleName);
}
