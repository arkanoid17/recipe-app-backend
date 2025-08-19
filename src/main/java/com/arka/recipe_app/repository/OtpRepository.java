package com.arka.recipe_app.repository;

import com.arka.recipe_app.models.entity.OtpValidator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpValidator,String> {

    Optional<OtpValidator> findOtpValidatorById(String sessionId);

    Optional<OtpValidator> findFirstByEmailOrderByOtpGeneratedAtDesc(String email);

}
