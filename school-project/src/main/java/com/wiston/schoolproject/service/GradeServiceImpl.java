package com.wiston.schoolproject.service;

import java.util.List;
import java.util.Optional;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Grade;
import com.wiston.schoolproject.entity.Student;
import com.wiston.schoolproject.exception.GradeNotFoundException;
import com.wiston.schoolproject.exception.StudentNotEnrolledException;
import com.wiston.schoolproject.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    GradeRepository gradeRepository;
    StudentService studentService;
    CourseService courseService;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> gradeOptional = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return unwrapGrade(gradeOptional, studentId, courseId);
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);
        if(!course.getStudents().contains(student)) throw new StudentNotEnrolledException();
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Optional<Grade> optionalGrade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade unwrappedGrade = unwrapGrade(optionalGrade, studentId, courseId);
        unwrappedGrade.setScore(score);
        return gradeRepository.save(unwrappedGrade);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

}
