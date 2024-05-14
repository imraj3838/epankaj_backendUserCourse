package com.rajlee.api.epankaj.jwtservice;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.rajlee.api.epankaj.models.Users;
import com.rajlee.api.epankaj.repositories.Userrepository;
import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
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
        String sendGridApiKey = "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"; // Replace with your actual SendGrid API key
        Email from = new Email("priyanshu@rajlee.com"); // Replace with your desired sender email address
        Email to = new Email(email);
        String subject = "Reset Password";
        String resetPasswordLink = "http://localhost:3000/resetpassword/" + resetToken;
        String content = "Dear User,\n\n"
                + "Please click the following link to reset your password:\n"
                + resetPasswordLink + "\n\n"
                + "If you did not request a password reset, please ignore this email.\n\n"
                + "Regards,\n"
                + "Your App Team";

        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, emailContent);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Reset password email sent successfully.");
        } catch (IOException ex) {
            System.out.println("Failed to send reset password email: " + ex.getMessage());
        }
    }

}
