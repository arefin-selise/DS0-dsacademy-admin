package com.dailystar.dsacademy.service.impl;

import com.dailystar.dsacademy.dto.request.UserRequestDto;
import com.dailystar.dsacademy.dto.response.UserResponseDto;
import com.dailystar.dsacademy.model.User;
import com.dailystar.dsacademy.repository.UserRepository;
import com.dailystar.dsacademy.service.UserService;
import com.dailystar.dsacademy.util.enums.UserStatus;
import com.dailystar.dsacademy.util.filter.FilterApplier;
import com.dailystar.dsacademy.util.filter.SearchFilter;
import com.dailystar.dsacademy.util.mapper.ModelMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Lazy
public class UserServiceImpl extends FilterApplier implements UserService
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public UserServiceImpl(final @Lazy UserRepository userRepository, final ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User createUserRegistration(final UserRequestDto request)
    {
        final User user = modelMapper.mapRegistrationDtoToUser(request);
        return userRepository.save(user);
    }

    @Override
    public User updateUserProfile(final UserRequestDto request)
    {
        final User user = modelMapper.mapRegistrationDtoToUser(request);
        user.setId(fetchUserProfilesByEmail(request.getEmail()).getId());
        return userRepository.save(user);
    }

    @Override
    public User fetchUserProfilesById(final String userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User details not found"));
    }

    @Override
    public User fetchUserProfilesByEmail(final String email)
    {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User details not found"));
    }

    @Override
    public List<User> fetchPendingUserList()
    {
        return userRepository.findAllByStatus(UserStatus.PENDING.name()).stream().toList();
    }

    @Override
    public List<UserResponseDto> fetchUserListByFilter(final SearchFilter filter)
    {
        final Query q = buildQuery(filter, User.class);
        List<User> users = userRepository.search(q, User.class);
        List<UserResponseDto> result = users.stream().map(modelMapper::mapUserToUserResponseDto).toList();
        return result;
    }
}
