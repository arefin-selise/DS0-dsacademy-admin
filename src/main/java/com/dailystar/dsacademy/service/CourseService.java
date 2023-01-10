package com.dailystar.dsacademy.service;

import com.dailystar.dsacademy.dto.request.CourseRequestDto;
import com.dailystar.dsacademy.model.Course;

import java.util.List;

public interface CourseService
{
    Course createCourse(final CourseRequestDto request);
    Course updateCourse(final CourseRequestDto request);
    Course fetchCourseByUserId(final String userId);
    List<Course> fetchAllCourses();
    List<Course> fetchAllCoursesByUserId(final String userId);
}
