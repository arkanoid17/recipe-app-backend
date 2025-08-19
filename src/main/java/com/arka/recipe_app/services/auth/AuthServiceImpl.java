package com.arka.recipe_app.services.auth;

import com.arka.recipe_app.exception.OtpException;
import com.arka.recipe_app.models.entity.OtpValidator;
import com.arka.recipe_app.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private OtpHandler otpHandler;


    @Override
    public OtpValidator sendOtp(String email) {
        return otpHandler.generateOtp(email);
    }

    @Override
    public void validateOtp(String sessionId, String email) {
        boolean isValid = otpHandler.otpValidation(sessionId,email);

        if(!isValid){
            throw new OtpException("Invalid OTP!");
        }
    }
}
