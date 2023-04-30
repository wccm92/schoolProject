package com.wiston.schoolproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Student;
import com.wiston.schoolproject.exception.CourseNotFoundException;
import com.wiston.schoolproject.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    StudentService studentService;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return unwrapCourse(courseOptional, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Set<Student> getEnrolledStudents(Long courseId) {
        return getCourse(courseId).getStudents();
    }

    @Override
    public Course addStudentToCourse(Long courseId, Long studentId) {
        Student student = studentService.getStudent(studentId);
        Course course = getCourse(courseId);
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new CourseNotFoundException(id);
        }
    }

}
