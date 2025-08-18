package com.arka.recipe_app.services.user;

import com.arka.recipe_app.models.domain.CreateUpdateUser;
import com.arka.recipe_app.models.entity.User;

public interface UserService {

    User registerUser(CreateUpdateUser user);
}
