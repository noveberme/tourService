package com.example.tour.repository;

import com.example.tour.entity.Guide;
import com.example.tour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByGuide(Guide guide);
    List<Tour> findByTitle(String title);
    List<Tour> findByOrderByPriceAsc();
    List<Tour> findByOrderByPriceDesc();
}
