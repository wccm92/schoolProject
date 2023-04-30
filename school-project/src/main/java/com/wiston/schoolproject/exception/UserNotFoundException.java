package com.wiston.schoolproject.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("The User does not exist in our records");
    }
}
