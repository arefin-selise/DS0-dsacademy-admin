package com.dailystar.dsacademy.util.mapper;

import com.dailystar.dsacademy.dto.request.CategoryRequestDto;
import com.dailystar.dsacademy.dto.request.CourseRequestDto;
import com.dailystar.dsacademy.dto.request.UserRequestDto;
import com.dailystar.dsacademy.dto.response.UserResponseDto;
import com.dailystar.dsacademy.model.Category;
import com.dailystar.dsacademy.model.Course;
import com.dailystar.dsacademy.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ModelMapper
{
    public User mapUserRequestDtoToUser(final UserRequestDto dto)
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

    public Category mapCategoryRequestDtoToCategory(final CategoryRequestDto dto)
    {
        return new Category().setName(dto.getName());
    }

    public Course mapCourseRequestDtoToCourse(final CourseRequestDto dto)
    {
        return new Course().setName(dto.getName());
    }
}
