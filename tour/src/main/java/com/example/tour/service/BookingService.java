package com.example.tour.service;

import com.example.tour.entity.Booking;
import com.example.tour.entity.Tour;
import com.example.tour.entity.User;
import com.example.tour.enums.Status;
import com.example.tour.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public Booking createBooking(Long tourId, Long userId, int countParticipants, LocalDate tourDate) {
        Tour tour = tourService.getTourById(tourId);
        User user = userService.getUserById(userId);

        if (tour == null)
            throw new IllegalArgumentException("Тур не найден");

        if (user == null)
            throw new IllegalArgumentException("Пользователь не найден");

        if (tourDate == null)
            throw new IllegalArgumentException("Дата экскурсии не указана");

        if (tourDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Нельзя забронировать тур на прошедшую дату");

        if (countParticipants <= 0)
            throw new IllegalArgumentException("Количество участников должно быть больше 0");

        int alreadyBokked = bookingRepository.findByTour(tour).stream()
                .filter(b -> b.getStatus() != Status.CANCELLED)
                .mapToInt(Booking::getCountParticipants)
                .sum();

        if (alreadyBokked + countParticipants > tour.getMaxParticipants()) {
            throw new IllegalStateException("Недостаточно свободных мест");
        }

        Booking booking = new Booking();
        booking.setTour(tour);
        booking.setUser(user);
        booking.setDate(tourDate);
        booking.setCountParticipants(countParticipants);
        booking.setTotalPrice(
                BigDecimal.valueOf((long) tour.getPrice() * countParticipants)
        );
        booking.setStatus(Status.PENDING);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingByUser(User user) {
        log.info("getBookingbyUser");
        return bookingRepository.findByUser(user);
    }

    public boolean cancelBooking(Long bookingId) {
        log.info("cancelBooking");

        Booking booking = getBookingById(bookingId);
        if (booking != null && booking.getStatus() != Status.CANCELLED) {
            booking.setStatus(Status.CANCELLED);
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    public List<Long> getBookedTourIdsByUser(User user) {
        return bookingRepository.findByUser(user).stream()
                .filter(b -> b.getStatus() != Status.CANCELLED)
                .map(b -> b.getTour().getId())
                .collect(Collectors.toList());
    }
}
