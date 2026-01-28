package com.example.tour.service;

import com.example.tour.entity.Booking;
import com.example.tour.entity.Tour;
import com.example.tour.entity.User;
import com.example.tour.enums.Status;
import com.example.tour.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    public Booking createBooking(Long tourId, Long userId, int countParticipants) {
        Tour tour = tourService.getTourById(tourId);
        User user = userService.getUserById(userId);

        if (tour == null || user == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setTour(tour);
        booking.setUser(user);
        booking.setDate(LocalDate.now());
        booking.setCountParticipants(countParticipants);
        booking.setTotalPrice(
                BigDecimal.valueOf((long) tour.getPrice() * countParticipants)
        );
        booking.setStatus(Status.PENDING);

        return bookingRepository.save(booking);
    }
}
