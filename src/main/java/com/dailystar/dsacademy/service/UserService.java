package com.dailystar.dsacademy.service;

import com.dailystar.dsacademy.dto.request.RegistrationDto;
import com.dailystar.dsacademy.dto.response.UserResponseDto;
import com.dailystar.dsacademy.model.User;
import com.dailystar.dsacademy.util.filter.SearchFilter;

import java.util.List;

public interface UserService
{
    User createUserRegistration(final RegistrationDto request);
    User updateUserProfile(final RegistrationDto request);
    User fetchUserProfilesById(final String userId);
    User fetchUserProfilesByEmail(final String email);
    List<User> fetchPendingUserList();
    List<UserResponseDto> fetchUserListByFilter(final SearchFilter filter);
}
