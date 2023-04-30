package com.wiston.schoolproject.service;

import com.wiston.schoolproject.entity.User;
import com.wiston.schoolproject.exception.UserNotFoundException;
import com.wiston.schoolproject.repository.UserRepository;
import com.wiston.schoolproject.security.utils.CustomEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CustomEncoder customEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return unwrapUser(userOptional, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return unwrapUser(userOptional, 404L);
    }

    @Override
        public User saveUser(User user) {
        user.setPassword(customEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException();
    }
}
