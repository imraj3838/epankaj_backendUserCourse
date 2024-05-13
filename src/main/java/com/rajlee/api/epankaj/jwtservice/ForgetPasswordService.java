package com.rajlee.api.epankaj.jwtservice;

import java.util.Properties;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ForgetPasswordService extends Authenticator {

    @Autowired
    private Userrepository userrepository;

    public String generateResetToken(Users user) {
        // You can use a library like java-uuid-generator or implement your own logic
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userrepository.save(user);
        return token;
    }


    public void sendResetPasswordEmail(String email, String resetToken) {
        // Configure email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.rajlee.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Your email credentials
        final String username = "priyanshu@rajlee.com";
        final String password = "Rajlee@2024";

        // Create a session with the SMTP server
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender email address
            message.setFrom(new InternetAddress(username));

            // Set the recipient email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set the email subject
            message.setSubject("Reset Password");

            // Set the email content
            String resetPasswordLink = "http://epankaj.com/resetpassword?token=" + resetToken;
            String content = "Dear User,\n\n"
                    + "Please click the following link to reset your password:\n"
                    + resetPasswordLink + "\n\n"
                    + "If you did not request a password reset, please ignore this email.\n\n"
                    + "Regards,\n"
                    + "Your App Team";
            message.setText(content);

            // Send the email
            Transport.send(message);
            System.out.println("Reset password email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send reset password email: " + e.getMessage());
        }
    }

}
