package com.dailystar.dsacademy.util.mapper;

import com.dailystar.dsacademy.dto.request.CategoryRequestDto;
import com.dailystar.dsacademy.dto.request.CourseRequestDto;
import com.dailystar.dsacademy.dto.request.UserRequestDto;
import com.dailystar.dsacademy.dto.response.UserResponseDto;
import com.dailystar.dsacademy.model.Category;
import com.dailystar.dsacademy.model.Course;
import com.dailystar.dsacademy.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ModelMapper
{
    public User mapUserRequestDtoToUser(final UserRequestDto dto)
    {
        return new User()
                .setId(dto.getUserId() == null ? UUID.randomUUID().toString() : dto.getUserId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setDateOfBirth(dto.getDateOfBirth())
                .setGender(dto.getGender())
                .setOccupation(dto.getOccupation())
                .setOrganization(dto.getOrganization())
                .setDepartment(dto.getDepartment())
                .setEmail(dto.getEmail())
                .setMobile(dto.getMobile())
                .setLocale(dto.getLocale())
                .setNotes(dto.getNotes())
                .setBody(dto.getBody())
                .setProfileImage(dto.getProfileImage())
                .setAttachments(dto.getAttachments())
                .setTimeZone(dto.getTimeZone())
                .setAddress(dto.getAddress())
                .setLastLogInTime(dto.getLastLogInTime())
                .setRoles(dto.getRoles())
                .setActive(dto.isActive())
                .setStatus(dto.getStatus())
                .setLanguage(dto.getLanguage())
                // here we insert badges,
                .setVerified(dto.isVerified())
                .setLinkedin(dto.getLinkedin())
                .setCreated(dto.getCreated())
                .setModified(dto.getModified());
    }

    public UserResponseDto mapUserToUserResponseDto(final User user)
    {
        return new UserResponseDto().setUserId(user.getId() == null ? UUID.randomUUID().toString() : user.getId());
    }

    public Category mapCategoryRequestDtoToCategory(final CategoryRequestDto dto)
    {
        return new Category()
                .setId(dto.getCategoryId() == null ? UUID.randomUUID().toString() : dto.getCategoryId())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setBody(dto.getBody())
                .setPicture(dto.getPicture())
                .setLogo(dto.getLogo())
                .setCreated(dto.getCreated())
                .setModified(dto.getModified());
    }

    public Course mapCourseRequestDtoToCourse(final CourseRequestDto dto)
    {
        return new Course()
                .setId(dto.getCourseId() == null ? UUID.randomUUID().toString() : dto.getCourseId())
                .setEnrollment(dto.isEnrollment())
                .setCategoryId(dto.getCategoryId())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPicture(dto.getPicture())
                .setBanner(dto.getBanner())
                .setContents(dto.getContents())
                .setAttachments(dto.getAttachments())
                .setStart(dto.getStart())
                .setEnd(dto.getEnd())
                .setDuration(dto.getDuration())
                .setPrice(dto.getPrice())
                .setSalePrice(dto.getSalePrice())
                .setCertification(dto.getCertification())
                .setCreated(dto.getCreated())
                .setModified(dto.getModified());
    }
}
