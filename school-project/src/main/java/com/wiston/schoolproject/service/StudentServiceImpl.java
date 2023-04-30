package com.wiston.schoolproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Student;
import com.wiston.schoolproject.exception.StudentNotFoundException;
import com.wiston.schoolproject.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return unwrapStudent(studentOptional, id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Set<Course> getEnrolledCourses(Long studentId) {
        return getStudent(studentId).getCourses();
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new StudentNotFoundException(id);
        }
    }

}