package com.arka.recipe_app.services.auth;

import com.arka.recipe_app.models.entity.OtpValidator;
import com.arka.recipe_app.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class OtpHandler {


    @Value("${otp.validation.duration}")
    private String otpValidationDuration;

    @Autowired
    private OtpRepository otpRepository;

    public OtpValidator generateOtp(String email) {
        Optional<OtpValidator> otpOptional = otpRepository.findFirstByEmailOrderByOtpGeneratedAtDesc(email);
        if(otpOptional.isPresent()){
            OtpValidator otp = otpOptional.get();
            if(withinDuration(otp) && !otp.isValidated()){
                sendOtp(email,otp.getOtp());
                return otp;
            }
        }

        OtpValidator otp = OtpValidator
                .builder()
                .email(email)
                .otp(String.format("%06d", (int) (Math.random() * 1000000)))
                .otpGeneratedAt(LocalDateTime.now())
                .build();


        // Generate a 6-digit OTP
        return saveOtp(otp);
    }

    public boolean withinDuration(OtpValidator otpValidator) {
        // Check if the OTP is still valid based on the duration
        long currentTime = System.currentTimeMillis();
        long otpGeneratedAt = otpValidator
                .getOtpGeneratedAt()
                .atZone(ZoneId.systemDefault())   // or ZoneId.of("UTC") if you want UTC
                .toInstant()
                .toEpochMilli();
        long duration = Long.parseLong(otpValidationDuration);

        return (currentTime - otpGeneratedAt) <= duration;
    }

    private void sendOtp(String email, String otp) {
        // Logic to send OTP to the user's email
        // This could involve using an email service to send the OTP
        // For now, we will just print the OTP to the console
        System.out.println("OTP sent to " + email + ": " + otp);
    }

    private OtpValidator saveOtp(OtpValidator otp) {
        return otpRepository.save(otp);
    }
}
