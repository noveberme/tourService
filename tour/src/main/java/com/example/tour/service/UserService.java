package com.example.tour.service;

import com.example.tour.entity.User;
import com.example.tour.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) { //подумать
        log.info("authenticate");
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existByPhone(String phone) {
        return userRepository.existsByNumberPhone(phone);
    }
}


