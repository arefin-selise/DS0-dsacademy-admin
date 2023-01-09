package com.dailystar.dsacademy.controller;

import com.dailystar.dsacademy.dto.request.RegistrationDto;
import com.dailystar.dsacademy.model.User;
import com.dailystar.dsacademy.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Lazy
    public UserController(final UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> viewUserProfileById(@PathVariable String userId)
    {
        return ResponseEntity.ok(userService.fetchUserProfilesById(userId));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> userRegistration(@RequestBody RegistrationDto request)
    {
        return ResponseEntity.ok(userService.createUserRegistration(request));
    }

    @PostMapping("/profile/update")
    public ResponseEntity<Object> updateUserProfile(@RequestBody RegistrationDto request)
    {
        return ResponseEntity.ok(userService.updateUserProfile(request));
    }

    @GetMapping("/pending-user-list")
    public ResponseEntity<List<User>> getPendingUserList()
    {
        return ResponseEntity.ok(userService.fetchPendingUserList());
    }
}
