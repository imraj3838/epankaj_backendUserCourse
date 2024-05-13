package com.rajlee.api.epankaj.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String token;
    private String newPassword;

}
