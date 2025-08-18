package com.arka.recipe_app.mapper;

import com.arka.recipe_app.models.dto.UserDto;
import com.arka.recipe_app.models.entity.User;

public class UserMapper {
    public static UserDto userToDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .dietType(user.getDietType())
                .build();
    }
}
