package com.example.tour.service;

import com.example.tour.entity.Tour;
import com.example.tour.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private GuideService guideService;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public void deleteTour(Tour tour) {
        tourRepository.delete(tour);
    }

    public List<Tour> searchTours(String title, Integer maxPrice) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasMaxPrice = maxPrice != null;

        if (hasTitle && hasMaxPrice) {
            return tourRepository.findByTitleContainingIgnoreCase(title).stream()
                    .filter(t -> t.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }
        else if (hasTitle) {
            return tourRepository.findByTitleContainingIgnoreCase(title);
        }
        else if (hasMaxPrice) {
            return tourRepository.findByPriceLessThanEqual(maxPrice);
        }
        return tourRepository.findAll();
    }

    public List<Tour> getAvailableTours() {
        return tourRepository.findAll();
    }
}
