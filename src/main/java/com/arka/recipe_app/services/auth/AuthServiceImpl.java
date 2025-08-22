package com.arka.recipe_app.services.auth;

import com.arka.recipe_app.config.JwtService;
import com.arka.recipe_app.exception.OtpException;
import com.arka.recipe_app.mapper.UserMapper;
import com.arka.recipe_app.models.domain.AuthResponse;
import com.arka.recipe_app.models.dto.UserDto;
import com.arka.recipe_app.models.entity.OtpValidator;
import com.arka.recipe_app.models.entity.User;
import com.arka.recipe_app.repository.OtpRepository;
import com.arka.recipe_app.repository.UserRepository;
import com.arka.recipe_app.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private OtpHandler otpHandler;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;


    @Override
    public OtpValidator sendOtp(String email) {
        return otpHandler.generateOtp(email);
    }

    @Override
    public AuthResponse validateOtp(String sessionId, String otp, String email) {


        boolean isValid = otpHandler.otpValidation(sessionId,otp);
        String token = null;

        if(!isValid){
            throw new OtpException("Invalid OTP!");
        }else {
            token = loginUser(email);
        }

        return AuthResponse.builder()
                .message("Verified successfully!")
                .token(token)
                .build();
    }

    @Override
    public String loginUser(String email) {
       Optional<User> userOpt = userService.getUserByEmail(email);
       if (userOpt.isPresent()){

           User user = userOpt.get();
           Map<String, Object> claims = new HashMap<>();
           claims.put("userId", String.valueOf(user.getId()));
           return jwtService.generateToken(user.getId(), claims);
       }

       return null;
    }
}
