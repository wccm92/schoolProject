package com.wiston.schoolproject.service;

import java.util.List;
import java.util.Set;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
    Set<Course> getEnrolledCourses(Long studentId);
}