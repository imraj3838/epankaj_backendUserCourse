package com.rajlee.api.epankaj.course_services;

import com.rajlee.api.epankaj.dtos.CourseDTO;
import com.rajlee.api.epankaj.models.Courses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CourseService {
    List<Courses> getAllCourses();

    Optional<Courses> findById(long id);

    Courses addCourse(CourseDTO courseDTO);

    List<Courses> findByName(String name);

    Courses updateCourseById(long id, Courses course);

    void deleteCourseById(long id);
}
