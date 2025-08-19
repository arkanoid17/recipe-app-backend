package com.arka.recipe_app.services.auth;

import com.arka.recipe_app.exception.OtpException;
import com.arka.recipe_app.models.entity.OtpValidator;
import com.arka.recipe_app.repository.OtpRepository;
import com.arka.recipe_app.services.email.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class OtpHandler {


    @Value("${otp.validation.duration}")
    private String otpValidationDuration;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    EmailService emailService;

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

    public boolean otpValidation(String sessionId, String otp){
        Optional<OtpValidator> otpOptional = otpRepository.findOtpValidatorById(sessionId);
        if(!otpOptional.isPresent()){
            throw new OtpException("Invalid Session-ID!");
        }
        OtpValidator otpVal = otpOptional.get();

        if(otpVal.isValidated){
            throw new OtpException("Otp validated previously!");
        }

        if(!withinDuration(otpVal)){
            throw new OtpException("Otp timed out!");
        }

        boolean isValid = otp.equalsIgnoreCase(otpVal.getOtp());

        if(isValid){
            otpVal.setValidated(true);
            otpVal.setValidatedAt(LocalDateTime.now());
            otpRepository.save(otpVal);
        }

        return isValid;
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
        try {
            emailService.sendOtpEMail(email, otp);
        }catch (MessagingException e){
            throw new OtpException("Failed to send otp to " + email, e);
        }catch (IOException e){
            throw new OtpException("Failed to load email template for " + email, e);
        }
        System.out.println("OTP sent to " + email + ": " + otp);
    }

    private OtpValidator saveOtp(OtpValidator otp) {
        return otpRepository.save(otp);
    }
}
