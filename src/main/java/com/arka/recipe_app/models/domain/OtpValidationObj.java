package com.arka.recipe_app.models.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpValidationObj {

    @NotBlank(message = "Email ID can not be empty!")
    String email;

    @NotBlank(message = "Session ID can not be empty!")
    String sessionId;

    @NotBlank(message = "Otp can not be empty!")
    String otp;
}
