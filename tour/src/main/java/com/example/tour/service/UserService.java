package com.example.tour.service;

import com.example.tour.entity.User;
import com.example.tour.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        log.info("getAllUsers");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.info("getUserById");
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        log.info("deleteUser");
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        log.info("saveUser");
        return userRepository.save(user);
    }
}


