package com.example.tour.repository;

import com.example.tour.entity.Booking;
import com.example.tour.entity.Tour;
import com.example.tour.entity.User;
import com.example.tour.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTour(Tour tour);
    List<Booking> findByUser(User user);

    /*List<Booking> findByDate(LocalDate date);
    List<Booking> findByDateAfter(LocalDate date);

    List<Booking> findByStatus(Status status);
    List<Booking> findByUserAndStatus(User user, Status status);
    List<Booking> findByTourAndStatus(Tour tour, Status status);
    long countByTourAndStatusNot(Tour tour, Status status);*/
}
