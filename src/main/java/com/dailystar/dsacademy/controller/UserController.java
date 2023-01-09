package com.dailystar.dsacademy.controller;

import com.dailystar.dsacademy.dto.request.RegistrationDto;
import com.dailystar.dsacademy.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Lazy
    public UserController(final UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> userRegistration(@RequestBody RegistrationDto request){

        return ResponseEntity.ok(userService.updateUserRegistration(request));
    }
}
