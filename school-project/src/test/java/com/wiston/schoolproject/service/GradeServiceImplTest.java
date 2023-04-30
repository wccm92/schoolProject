package com.wiston.schoolproject.service;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Grade;
import com.wiston.schoolproject.entity.Student;
import com.wiston.schoolproject.exception.StudentNotEnrolledException;
import com.wiston.schoolproject.repository.GradeRepository;
import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class GradeServiceImplTest {

    @Mock
    GradeRepository mockedGradeRepository;
    @Mock
    StudentService mockedStudentService;
    @Mock
    CourseService mockedCourseService;

    @InjectMocks
    GradeServiceImpl gradeService;


    // Preparing data
    Student student = new Student("Pedro Piñeres", LocalDate.parse(("2000-07-31")));
    Course course = new Course("Math", "M104", "In this class, you will learn about Math.");

    @BeforeEach
    void setUp() {

        student.setId(1L);
        course.setId(1L);
        course.setStudents(new HashSet<>());
        student.setCourses(new HashSet<>());
    }

    @Test
    void saveGradeWithStudentEnrolled() {

        course.getStudents().add(student);
        Grade grade = new Grade(1L, "A+", student, course);

        Mockito.when(mockedStudentService.getStudent(student.getId())).thenReturn(student);
        Mockito.when(mockedCourseService.getCourse(course.getId())).thenReturn(course);
        Mockito.when(mockedGradeRepository.save(grade)).thenReturn(grade);
        Grade result = gradeService.saveGrade(grade, student.getId(), course.getId());

        Assert.assertEquals(result.getScore(), "A+");
    }

    @Test
    void saveGradeWithStudentNotEnrolled() {

        // Here is missing the enroll student to course operation on purpose. --->> "course.getStudents().add(student);"

        Grade grade = new Grade(1L, "A+", student, course);

        Mockito.when(mockedStudentService.getStudent(student.getId())).thenReturn(student);
        Mockito.when(mockedCourseService.getCourse(course.getId())).thenReturn(course);

        Assert.assertThrows(
                StudentNotEnrolledException.class,
                () -> {gradeService.saveGrade(grade, student.getId(), course.getId());}
        );
    }
}