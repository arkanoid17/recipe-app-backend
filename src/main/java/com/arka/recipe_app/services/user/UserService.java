package com.arka.recipe_app.services.user;

import com.arka.recipe_app.models.domain.CreateUpdateUser;
import com.arka.recipe_app.models.entity.User;

import java.util.Optional;

public interface UserService {

    User registerUser(CreateUpdateUser user);

    User getUserById(Long userId);

    Optional<User> getUserByEmail(String email);

}
