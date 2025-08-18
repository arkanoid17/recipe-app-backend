package com.arka.recipe_app.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "otp_validator")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpValidator {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public LocalDateTime otpGeneratedAt;

    @Column
    public boolean isValidated;

    @Column()
    public LocalDateTime validatedAt;

    @Column()
    public String otp;


}
