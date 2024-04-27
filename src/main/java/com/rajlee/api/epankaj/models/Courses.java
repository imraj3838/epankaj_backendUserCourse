package com.rajlee.api.epankaj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Courses extends BaseModel {
    private String name;
    private String description;

}
