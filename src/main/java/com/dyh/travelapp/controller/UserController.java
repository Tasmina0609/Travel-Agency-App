package com.dyh.travelapp.controller;

import com.dyh.travelapp.model.Tour;
import com.dyh.travelapp.service.TourService;
import com.dyh.travelapp.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final TourService tourService;

    public UserController(TourService tourService, UserService userService) {
        this.tourService = tourService;
    }

    @GetMapping
    public String userPage() {
        return "user/userPage";
    }

    @GetMapping("/tours")
    public String viewTours(Model model, @Param("keyword") String keyword) {
        List<Tour> tourList = tourService.listAll(keyword);
        model.addAttribute("tourList", tourList);
        model.addAttribute("keyword", keyword);
        return "user/userTours";
    }

}
