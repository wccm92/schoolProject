package com.wiston.schoolproject.service;

import java.util.List;
import java.util.Set;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Student;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
    Course addStudentToCourse(Long courseId, Long studentId);
    Set<Student> getEnrolledStudents(Long courseId);
}