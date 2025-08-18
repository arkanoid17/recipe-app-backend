package com.arka.recipe_app.repository;

import com.arka.recipe_app.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);
}
