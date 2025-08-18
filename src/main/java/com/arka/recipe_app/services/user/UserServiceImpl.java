package com.arka.recipe_app.services.user;

import com.arka.recipe_app.exception.UserExceptions;
import com.arka.recipe_app.models.domain.CreateUpdateUser;
import com.arka.recipe_app.models.entity.DietType;
import com.arka.recipe_app.models.entity.User;
import com.arka.recipe_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(CreateUpdateUser user){

        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw new UserExceptions("User with email " + user.getEmail() + " already exists.");
        }

        User newUser = User
                .builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .dietType(DietType.valueOf(user.getDietType()))
                .build();

        return userRepository.save(newUser);
    }
}
