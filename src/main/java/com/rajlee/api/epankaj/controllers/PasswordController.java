package com.rajlee.api.epankaj.controllers;

import com.rajlee.api.epankaj.jwtservice.ForgetPasswordService;
import com.rajlee.api.epankaj.models.ForgetPasswordRequest;
import com.rajlee.api.epankaj.models.ResetPasswordRequest;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/epankaj/v.0/users")
public class PasswordController {

    @Autowired
    private Userrepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgetPasswordRequest request) {
        String email = request.getEmail();
        log.info("Forget password invoke for this"+email);
        Users user = userrepository.findByEmail(email);

        if (user == null) {
            // Email not found, return appropriate response
            return ResponseEntity.badRequest().body("Email not found");
        }

        // Generate a reset token and send it to the user's email
        String resetToken = forgetPasswordService.generateResetToken(user);
        forgetPasswordService.sendResetPasswordEmail(user.getEmail(), resetToken);

        return ResponseEntity.ok().body("Reset password link sent to your email");
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        String token = request.getToken();
        String newPassword = request.getNewPassword();

        Users user = userrepository.findByResetToken(token);

        if (user == null) {
            // Invalid or expired token, return appropriate response
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }

        // Update the user's password and clear the reset token
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userrepository.save(user);

        return ResponseEntity.ok().body("Password reset successfully");
    }


}
