package com.example.tour.controller;

import com.example.tour.entity.Booking;
import com.example.tour.entity.User;
import com.example.tour.security.CustomUserDetails;
import com.example.tour.service.BookingService;
import com.example.tour.service.TourService;
import com.example.tour.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private TourService tourService;

    @Autowired
    private UserService userService;

    @GetMapping("/bookings")
    public String getUserBooking(Model model,  @AuthenticationPrincipal CustomUserDetails principal) {
        List<Booking> bookings = bookingService.getBookingByUser(principal.getUser());
        model.addAttribute("bookings", bookings);
        return "bookings";
    }//по конкретному пользователю

    @PostMapping("/book_tour") //обработка попытки брони
    public String bookTour(@RequestParam Long tourId, @RequestParam LocalDate date,
                           @RequestParam Integer participants,
                           @AuthenticationPrincipal CustomUserDetails principal) {
        User user = principal.getUser();
        try {
            bookingService.createBooking(tourId, user.getId(), participants, date);
            return "redirect:/bookings";
        } catch (Exception e) {
            return "redirect:/tours?error=" + e.getMessage();
        }
    }

    @PostMapping("/cancel_booking")
    public String cancelBooking(@RequestParam Long bookingId,  @AuthenticationPrincipal CustomUserDetails principal) {
        User user = principal.getUser();
        try {
            bookingService.cancelBooking(bookingId, user.getId());
        } catch (SecurityException e) {
            return "redirect:/bookings?error=Нет прав на отмену этой брони";
        }

        return "redirect:/bookings";
    }

    @GetMapping("/booked-tour-ids")
    @ResponseBody
    public List<Long> getBookedTourIds( @AuthenticationPrincipal CustomUserDetails principal) {
        User user = principal.getUser();
        if (user == null) {
            return List.of();
        }
        return bookingService.getBookedTourIdsByUser(user); //возврат id туров уже забронированных
    }
}
