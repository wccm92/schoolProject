package com.wiston.schoolproject.exception;

public class StudentNotEnrolledException extends RuntimeException {

    public StudentNotEnrolledException() {
        super("You are trying to save a grade for a student who doesn't exist in this course");
    }
}
