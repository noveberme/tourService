package com.example.tour.repository;

import com.example.tour.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {
    Optional<Guide> findByEmail(String title);
    Optional<Guide> findByNumberPhone(String numberPhone);

    List<Guide> findByOrderByRatingDesc();

    boolean existsByEmail(String email);
    boolean existsByNumberPhone(String numberPhone);
}
