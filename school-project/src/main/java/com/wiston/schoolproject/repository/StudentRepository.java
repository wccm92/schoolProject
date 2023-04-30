package com.wiston.schoolproject.repository;

import com.wiston.schoolproject.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}