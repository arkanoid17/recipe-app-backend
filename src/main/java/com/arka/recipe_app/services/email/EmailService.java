package com.arka.recipe_app.services.email;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void sendOtpEMail(String email, String otp) throws MessagingException, IOException;
}
