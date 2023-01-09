package com.dailystar.dsacademy.service;

import com.dailystar.dsacademy.dto.request.RegistrationDto;
import com.dailystar.dsacademy.model.User;

public interface UserService {
    User updateUserRegistration(RegistrationDto request);
}
