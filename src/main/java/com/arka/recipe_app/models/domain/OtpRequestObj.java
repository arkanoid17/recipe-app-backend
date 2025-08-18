package com.arka.recipe_app.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpRequestObj {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
}
