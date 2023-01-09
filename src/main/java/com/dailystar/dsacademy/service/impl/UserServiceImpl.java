package com.dailystar.dsacademy.service.impl;

import com.dailystar.dsacademy.dto.request.RegistrationDto;
import com.dailystar.dsacademy.model.User;
import com.dailystar.dsacademy.repository.UserRepository;
import com.dailystar.dsacademy.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Log4j2
@Service
@Lazy
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(@Lazy UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User updateUserRegistration(final RegistrationDto request) {
        final User user = new User();
        user.setFirstName("md");
        user.setLastName("habib");
        user.setDateOfBirth(LocalDate.now());
        user.setGender("MALE");
        user.setEmail("habib@gmail.com");
        user.addRole("user");
        user.setMobile("01741222333");
        user.setLanguage("english");

        userRepository.save(user);
        return user;
    }
}
