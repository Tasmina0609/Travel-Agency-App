package com.dyh.travelapp.service;

import com.dyh.travelapp.model.Tour;
import com.dyh.travelapp.repository.TourRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TourService {

    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> listAllTours() {
        return tourRepository.findAll();
    }

    public List<Tour> listAll(String keyword) {
        if (keyword != null) {
            return tourRepository.searchTour(keyword);
        }
        return tourRepository.findAll();
    }

    public void saveTour(Tour tour) {
        tourRepository.save(tour);
    }

    public Tour getTour(Long  id) {
        return tourRepository.findById(id).get();
    }

    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }


}
