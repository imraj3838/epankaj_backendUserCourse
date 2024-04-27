package com.rajlee.api.epankaj.repositories;

import com.rajlee.api.epankaj.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Long> {
    List<Courses> findAllByName(String name);
}
