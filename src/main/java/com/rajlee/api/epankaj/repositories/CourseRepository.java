package com.rajlee.api.epankaj.repositories;

import com.rajlee.api.epankaj.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Long> {
}
