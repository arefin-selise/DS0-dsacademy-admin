package com.dailystar.dsacademy.repository;

import com.dailystar.dsacademy.model.Course;
import com.dailystar.dsacademy.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Lazy
public interface CourseRepository extends MongoRepository<Course, String>, CustomSearchRepository<Course>
{
    Optional<Course> findById(final String courseId);
    List<Course> findCoursesByUserId(final String userId);
    List<Course> findAllCourses();
}
