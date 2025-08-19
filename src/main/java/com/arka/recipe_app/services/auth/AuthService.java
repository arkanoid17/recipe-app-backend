package com.arka.recipe_app.services.auth;

import com.arka.recipe_app.models.entity.OtpValidator;

public interface AuthService {

    OtpValidator sendOtp(String email);

    void validateOtp(String sessionId, String email);
}
