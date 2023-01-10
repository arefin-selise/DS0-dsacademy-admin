package com.dailystar.dsacademy.controller;

import com.dailystar.dsacademy.dto.request.CourseRequestDto;
import com.dailystar.dsacademy.model.Course;
import com.dailystar.dsacademy.service.CourseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController
{
    private final CourseService courseService;

    @Lazy
    public CourseController(final CourseService courseService)
    {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCourse(@RequestBody CourseRequestDto request)
    {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> editCourse(@RequestBody CourseRequestDto request)
    {
        return ResponseEntity.ok(courseService.updateCourse(request));
    }

    @GetMapping( "/view/{courseId}")
    public ResponseEntity<Course> viewCourseById(@PathVariable String courseId)
    {
        return ResponseEntity.ok(courseService.fetchCourseByCourseId(courseId));
    }

    @GetMapping("/view/courses")
    public ResponseEntity<List<Course>> viewCourseList()
    {
        return ResponseEntity.ok(courseService.fetchAllCourses());
    }
}
