package com.wiston.schoolproject.repository;

import com.wiston.schoolproject.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}