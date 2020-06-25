package com.dyh.travelapp.controller;

import com.dyh.travelapp.model.Tour;
import com.dyh.travelapp.model.TourStatus;
import com.dyh.travelapp.service.TourService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TourService tourService;

    public AdminController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public String adminPage() {
        return "admin/adminPage";
    }

//    @GetMapping("/tours")
//    public String viewTours(Model model) {
//        List<Tour> tourList=tourService.listAllTours();
//        model.addAttribute("tourList",tourList);
//        return "adminTours";
//    }

    @GetMapping("/tours")
    public String viewAndSearchTours(Model model, @Param("keyword") String keyword) {
        List<Tour> tourList = tourService.listAll(keyword);
        model.addAttribute("tourList", tourList);
        model.addAttribute("keyword", keyword);
        return "admin/adminTours";
    }

    @GetMapping("/newTour")
    public String showNewTourForm(Model model) {
        Tour tour=new Tour();
        model.addAttribute("tour",tour);
        model.addAttribute("tourStatuses", TourStatus.values());
        return "admin/newTour";
    }

    @PostMapping("/saveTour")
    public String saveTour(@ModelAttribute("tour") Tour tour) {
        tourService.saveTour(tour);
        return "redirect:/admin/tours";
    }

    @GetMapping("editTour/{id}")
    public String editTourForm(@PathVariable("id") long id, Model model) {
        Tour tour=tourService.getTour(id);
        model.addAttribute("tour", tour);
        model.addAttribute("tourStatuses", TourStatus.values());
        return "admin/editTour";
    }

    @PostMapping("updateTour/{id}")
    public String updateTour(@PathVariable("id") long id, Tour tour, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            tour.setId(id);
            return "admin/editTour";
        }
        tourService.saveTour(tour);

        model.addAttribute("tour",tourService.listAllTours());
        return "redirect:/admin/tours";
    }

    @RequestMapping("/deleteTour/{id}")
    public String deleteTour(@PathVariable("id") String id) {
        tourService.deleteTour(Long.valueOf(id));
        return "redirect:/admin/tours";
    }


}
