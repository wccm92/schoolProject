package com.wiston.schoolproject.service;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Student;
import com.wiston.schoolproject.repository.CourseRepository;
import org.junit.Assert;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class CourseServiceImplTest {

    @Mock
    CourseRepository mockedCourseRepository;
    @Mock
    StudentService mockedStudentService;

    @InjectMocks
    CourseServiceImpl courseService;

    // Preparing data.
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
    void addStudentToCourseTest() {

        Mockito.when(mockedStudentService.getStudent(student.getId())).thenReturn(student);
        Mockito.when(mockedCourseRepository.findById(course.getId())).thenReturn(Optional.ofNullable(course));
        Mockito.when(mockedCourseRepository.save(course)).thenReturn(course);

        Course result = courseService.addStudentToCourse(course.getId(), student.getId());

        Assert.assertEquals(result.getStudents().size(), 1);
    }


}