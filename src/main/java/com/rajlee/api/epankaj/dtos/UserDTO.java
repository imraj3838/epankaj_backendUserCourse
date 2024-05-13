package com.rajlee.api.epankaj.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String image;

    // Default constructor required for Jackson deserialization
    public UserDTO() {
    }

    // Constructor with name and email
    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor with name, email, and image
//    public UserDTO(String name, String email, String image) {
//        this.name = name;
//        this.email = email;
//        this.image = image;
//    }

    // Jackson annotated constructor with name, email, and password
    @JsonCreator
    public UserDTO(@JsonProperty("name") String name,
                   @JsonProperty("email") String email,
                   @JsonProperty("image") String image) {
        this.name = name;
        this.email = email;
        this.image = image;
    }
}
