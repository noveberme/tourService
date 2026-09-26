package com.example.tour.controller;

import com.example.tour.entity.Guide;
import com.example.tour.entity.Tour;
import com.example.tour.enums.Language;
import com.example.tour.security.CustomUserDetails;
import com.example.tour.service.BookingService;
import com.example.tour.service.GuideService;
import com.example.tour.service.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {
    @Autowired
    TourService tourService;

    @Autowired
    GuideService guideService;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public String listTours(@RequestParam(required = false) String search,
                            @RequestParam(required = false) Integer maxPrice,
                            Model model) {
        List<Tour> tours = tourService.searchTours(search, maxPrice);
        model.addAttribute("tours", tours);
        model.addAttribute("language", Language.values());
        return "tours";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
        Guide guide = guideService.getGuideByUser(principal.getUser());

        if (guide == null) {
            model.addAttribute("error", "Профиль гида не найден. Обратитесь к администратору.");
            return "error";
        }

        model.addAttribute("tours", new Tour());
        model.addAttribute("languages", Language.values());
        return "add_tour";
    }

    @PostMapping("/create")
    public String createTour(@Valid @ModelAttribute Tour tour, BindingResult result,
                             @AuthenticationPrincipal CustomUserDetails principal,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("languages", Language.values());
            return "add_tour";
        }

        Guide guide = guideService.getGuideByUser(principal.getUser());
        if (guide == null) {
            model.addAttribute("error", "Аккаунт гида не найден");
            model.addAttribute("languages", Language.values());
            return "add_tour";
        }

        tour.setGuide(guide);
        tourService.saveTour(tour);
        return "redirect:/tours";
    }

    @GetMapping("/{id}")
    public String viewTour(@PathVariable Long id, Model model,
                           @AuthenticationPrincipal CustomUserDetails principal) {
        Tour tour = tourService.getTourById(id);
        if (tour == null) {
            model.addAttribute("error", "Тур не найден");
            return "error";
        }

        List<Long> bookedTourIds = bookingService.getBookedTourIdsByUser(principal.getUser());
        model.addAttribute("booked_tours", bookedTourIds);
        model.addAttribute("tour", tour);
        return "tour_details"; //нужно не забыть создать
    }

    @GetMapping("/edit/{id}") //форма редактирования тура - достпуно только пока гидам (добавить админов)
    public String showEditForm(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal CustomUserDetails principal) {
        Tour tour = tourService.getTourById(id);
        if (tour == null) {
            return "redirect:/tours?error=Тур не найден";
        }

        Guide guide = guideService.getGuideByUser(principal.getUser());
        if (guide == null || !tour.getGuide().getId().equals(guide.getId())) {
            return "redirect:/tours?error=У вас нет прав на редактирование этого тура";
        }

        model.addAttribute("tour", tour);
        model.addAttribute("languages", Language.values());
        return "edit_tour";
    }

    @PostMapping("/edit/{id}")
    public String updateTour(@PathVariable Long id,
                             @Valid @ModelAttribute Tour tour,
                             BindingResult result,
                             @AuthenticationPrincipal CustomUserDetails principal,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("languages", Language.values());
            return "edit_tour";
        }

        Guide guide = guideService.getGuideByUser(principal.getUser());
        tour.setGuide(guide);
        tour.setId(id);
        tourService.saveTour(tour);
        return "redirect:/tours";
    }

    @PostMapping("/delete/{id}")
    public String deleteTour(@PathVariable Long id,  @AuthenticationPrincipal CustomUserDetails principal) {
        Tour tour = tourService.getTourById(id);
        if (tour == null) {
            return "redirect:/tours?error=Тур не найден";
        }

        Guide guide = guideService.getGuideByUser(principal.getUser());
        if (guide != null && tour.getGuide().getId().equals(guide.getId())) {
            tourService.deleteTour(tour);
        }

        return "redirect:/tours";
    }
}
