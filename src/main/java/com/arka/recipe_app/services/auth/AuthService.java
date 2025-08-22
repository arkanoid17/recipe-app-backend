package com.arka.recipe_app.services.auth;

import com.arka.recipe_app.models.domain.AuthResponse;
import com.arka.recipe_app.models.dto.UserDto;
import com.arka.recipe_app.models.entity.OtpValidator;
import com.arka.recipe_app.models.entity.User;

public interface AuthService {

    OtpValidator sendOtp(String email);

    AuthResponse validateOtp(String sessionId,String otp, String email);

    String loginUser(String email);
}
