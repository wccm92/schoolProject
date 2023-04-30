package com.wiston.schoolproject;

import com.wiston.schoolproject.entity.Course;
import com.wiston.schoolproject.entity.Student;
import com.wiston.schoolproject.repository.CourseRepository;
import com.wiston.schoolproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GradeSubmissionApplication implements CommandLineRunner {

	@Autowired
    StudentRepository studentRepository;

	@Autowired
    CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(GradeSubmissionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student[] students = new Student[] {
				new Student("Pedro Piñeres", LocalDate.parse(("2000-07-31"))),
				new Student("Lisa Rodriguez", LocalDate.parse(("2003-03-01"))),
				new Student("Marcelo Herrera", LocalDate.parse(("2001-09-19"))),
				new Student("Juliana Zuñiga", LocalDate.parse(("2002-07-30")))
		};

		for (Student student : students) {
			studentRepository.save(student);
		}

		Course[] courses = new Course[] {
				new Course("Math", "M104", "In this class, you will learn about Math."),
				new Course("Spanish", "SP101", "In this class, you will learn about the spanish language."),
				new Course("Biology", "BIO311", "In this class, you will learn about biology."),
				new Course("History", "HIS393", "In this class, you will learn about history."),
				new Course("Economy", "ECO102", "In this class, you will learn about economy.")
		};

		for (Course course : courses) {
			courseRepository.save(course);
		}
	}
}
