package com.wiston.schoolproject.service;

import com.wiston.schoolproject.entity.User;

public interface UserService {

    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
