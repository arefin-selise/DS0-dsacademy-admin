package com.dailystar.dsacademy.service.impl;

import com.dailystar.dsacademy.dto.request.RegistrationDto;
import com.dailystar.dsacademy.model.User;
import com.dailystar.dsacademy.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Log4j2
@Service
@Lazy
public class UserServiceImpl implements UserService {
    public UserServiceImpl(){

    }

    @Override
    public User updateUserRegistration(final RegistrationDto request) {
        final User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(LocalDate.now());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.addRole(request.getRole());
        return user;
    }
}
