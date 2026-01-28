package com.example.tour.repository;

import com.example.tour.entity.User;
import com.example.tour.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByNumberPhone(String numberPhone);

    boolean existsByEmail(String email);
    boolean existsByNumberPhone(String username);

    List<User> findByUserRole(Role role);
}
