package com.dailystar.dsacademy.service.impl;

import com.dailystar.dsacademy.dto.request.CourseRequestDto;
import com.dailystar.dsacademy.model.Course;
import com.dailystar.dsacademy.repository.CourseRepository;
import com.dailystar.dsacademy.repository.UserRepository;
import com.dailystar.dsacademy.service.CourseService;
import com.dailystar.dsacademy.service.UserService;
import com.dailystar.dsacademy.util.filter.FilterApplier;
import com.dailystar.dsacademy.util.mapper.ModelMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Lazy
public class CourseServiceImpl extends FilterApplier implements CourseService
{
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    public CourseServiceImpl(final @Lazy CourseRepository courseRepository, final ModelMapper modelMapper)
    {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Course createCourse(final CourseRequestDto request)
    {
        return null;
    }

    @Override
    public Course updateCourse(final CourseRequestDto request)
    {
        return null;
    }

    @Override
    public Course fetchCourseByUserId(final String userId)
    {
        return null;
    }

    @Override
    public List<Course> fetchAllCourses()
    {
        return null;
    }

    @Override
    public List<Course> fetchAllCoursesByUserId(final String userId)
    {
        return null;
    }
}
