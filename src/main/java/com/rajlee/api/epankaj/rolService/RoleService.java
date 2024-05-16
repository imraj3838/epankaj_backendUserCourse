package com.rajlee.api.epankaj.rolService;

import com.rajlee.api.epankaj.models.Role;
import com.rajlee.api.epankaj.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private  RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String roleName) {
        Role role = new Role(roleName);
        return roleRepository.save(role);
    }

    public void initRoles() {
        createRole("ROLE_ADMIN");
        createRole("ROLE_USER");
    }


    public Role getRoleByName(String roleName) {
        return (Role) roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
