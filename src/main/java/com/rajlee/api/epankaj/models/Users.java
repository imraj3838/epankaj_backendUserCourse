package com.rajlee.api.epankaj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users extends BaseModel {
    private String email;
    private String name;
    private String password;
}
