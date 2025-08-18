package com.arka.recipe_app.controllers;

import com.arka.recipe_app.mapper.OtpMapper;
import com.arka.recipe_app.mapper.UserMapper;
import com.arka.recipe_app.models.domain.CreateUpdateUser;
import com.arka.recipe_app.models.domain.OtpRequestObj;
import com.arka.recipe_app.models.dto.OtpDto;
import com.arka.recipe_app.models.dto.UserDto;
import com.arka.recipe_app.models.entity.OtpValidator;
import com.arka.recipe_app.models.entity.User;
import com.arka.recipe_app.services.auth.AuthService;
import com.arka.recipe_app.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;


    @PostMapping("register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody CreateUpdateUser user) {
        User newuser = userService.registerUser(user);
        return ResponseEntity.ok(UserMapper.userToDto(newuser));
    }

    @PostMapping("/otp/send")
    public ResponseEntity<OtpDto> sendOtp(@Valid @RequestBody OtpRequestObj otpRequestObj) {
        OtpValidator validator = authService.sendOtp(otpRequestObj.getEmail());
        return ResponseEntity.ok(OtpMapper.fromOtpValidatorToOtpDto(validator.getId(),validator.getEmail()));
    }

}
