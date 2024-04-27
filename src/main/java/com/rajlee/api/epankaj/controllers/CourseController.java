package com.rajlee.api.epankaj.controllers;

import com.rajlee.api.epankaj.course_services.CourseService;
import com.rajlee.api.epankaj.dtos.CourseDTO;
import com.rajlee.api.epankaj.models.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
    @RequestMapping("/epankaj/v.0/courses") // Separate path for courses
    public class CourseController {

        @Autowired
        private CourseService courseService; // Assuming you have a CourseService class

        @GetMapping("/getall")
        public List<Courses> getAllCourses() {
            return courseService.getAllCourses(); // Assuming CourseService has this method
        }

        @GetMapping("/id/{id}")
        public Optional<Courses> getCourseById(@PathVariable("id") long id) {
            return courseService.findById(id); // Assuming CourseService has this method
        }

        @PostMapping("/save")
        public Courses addCourse(@RequestBody CourseDTO courseDTO) {
            return courseService.addCourse(courseDTO); // Assuming CourseService has this method
        }

        @GetMapping("/name/{name}") // Modify if name is not a unique identifier for courses
        public List<Courses> getCoursesByName(@PathVariable("name") String name) {
            return courseService.findByName(name); // Assuming CourseService has this method (may need adjustment)
        }

        // Add more endpoints for searching/filtering courses if needed

        @DeleteMapping("/delete/{id}")
        public void deleteCourse(@PathVariable("id") long id) {
            courseService.deleteCourseById(id); // Assuming CourseService has this method
        }
    }

