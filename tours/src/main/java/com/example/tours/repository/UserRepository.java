package com.example.tours.repository;

import com.example.tours.entity.User;
import com.example.tours.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByNumberPhone(String numberPhone);

    boolean existsByEmail(String email);
    boolean existsByNumberPhone(String username);

    List<User> findByUserRole(Role role);
}
