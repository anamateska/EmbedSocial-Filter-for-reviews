package com.example.embedsocialfilterforreviews.web;

import com.example.embedsocialfilterforreviews.model.Review;
import com.example.embedsocialfilterforreviews.service.ReviewService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private ObjectMapper objectMapper;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping
    public String orderedReviews(
            @RequestParam(defaultValue = "No") String textPriority,
            @RequestParam(defaultValue = "Highest First") String ratingOrder,
            @RequestParam(defaultValue = "Newest First") String dateOrder,
            @RequestParam(defaultValue = "1") int minRating,
            Model model) throws IOException {
        File json = new File("reviews.json");
        List<Review> allReviews = new ArrayList<>(objectMapper.readValue(json, new TypeReference<List<Review>>(){}));
        List<Review> orderedReviews = new ArrayList<>(reviewService.filterAndSortReviews(allReviews, textPriority, ratingOrder, dateOrder, minRating));
        model.addAttribute("orderedReviews", orderedReviews);
        return "reviews";
    }

}