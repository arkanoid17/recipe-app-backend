package com.arka.recipe_app.models.dto;

import com.arka.recipe_app.models.entity.DietType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private DietType dietType;
}
