package com.arka.recipe_app.mapper;

import com.arka.recipe_app.models.dto.OtpDto;

public class OtpMapper {
    public static OtpDto fromOtpValidatorToOtpDto(String sessionId, String message) {
        return OtpDto.builder()
                .sessionId(sessionId)
                .message(message)
                .build();
    }
}
