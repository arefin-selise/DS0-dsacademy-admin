package com.dailystar.dsacademy.util.mapper;

import com.dailystar.dsacademy.dto.request.UserRequestDto;
import com.dailystar.dsacademy.dto.response.UserResponseDto;
import com.dailystar.dsacademy.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ModelMapper
{
    public User mapRegistrationDtoToUser(final UserRequestDto dto)
    {
        return new User().setEmail(dto.getEmail())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setStatus(dto.getStatus());
    }

    public UserResponseDto mapUserToUserResponseDto(final User user)
    {
        return new UserResponseDto().setUserId(user.getId() == null ? UUID.randomUUID().toString() : user.getId());
    }
}
