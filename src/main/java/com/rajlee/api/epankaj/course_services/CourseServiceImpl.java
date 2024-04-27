package com.rajlee.api.epankaj.course_services;

import com.rajlee.api.epankaj.dtos.CourseDTO;
import com.rajlee.api.epankaj.models.Courses;
import com.rajlee.api.epankaj.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Courses> findById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Courses addCourse(CourseDTO courseDTO) {
        Courses course = new Courses();

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

        return courseRepository.save(course);

    }

    @Override
    public List<Courses> findByName(String name) {
        return courseRepository.findAllByName(name); // Assuming 'findAllByName' exists
    }

    @Override
    public Courses updateCourseById(long id, Courses course) {
        Optional<Courses> existingCourse = courseRepository.findById(id);

        if (existingCourse.isEmpty()) {
            throw new RuntimeException("Course not found with ID: " + id); // More informative message
        }

        Courses updatedCourse = existingCourse.get();
        if(updatedCourse.getName()!=null){
            updatedCourse.setName(course.getName());
        }

        if(updatedCourse.getDescription()!=null) {
            updatedCourse.setDescription(course.getDescription());
        }

        courseRepository.save(updatedCourse);
        return updatedCourse;
    }


    @Override
    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }
}
