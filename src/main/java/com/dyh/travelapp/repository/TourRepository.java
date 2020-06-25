package com.dyh.travelapp.repository;

import com.dyh.travelapp.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("SELECT t FROM Tour t WHERE CONCAT(t.name, ' ',t.price) LIKE %?1%")
    public List<Tour> searchTour(String keyword);

}
