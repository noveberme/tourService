package com.example.tour.repository;

import com.example.tour.entity.Guide;
import com.example.tour.entity.Tour;
import com.example.tour.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    /*List<Tour> findByGuide(Guide guide);//поиск по гиду
    List<Tour> findByTitle(String title);//поиск по названию
    List<Tour> findByOrderByPriceAsc();//поиск по цене (по убыванию)
    List<Tour> findByOrderByPriceDesc();//поиск по цене по возрастанию*/
    List<Tour> findByTitleContainingIgnoreCase(String title);//поиск по названию (с учетом регистра)
    List<Tour> findByPriceLessThanEqual(int price);//поиск дешевых (цена ниже среднего)
    /*List<Tour> findByPriceBetween(int min, int max);
    List<Tour> findByTourLanguage(Language language);//поиск по языку тура*/
}
