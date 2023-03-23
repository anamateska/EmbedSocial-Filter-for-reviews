package com.example.embedsocialfilterforreviews.web;

import com.example.embedsocialfilterforreviews.model.Review;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private ObjectMapper objectMapper;

    public ReviewController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping
    public String reviewsAndForm(Model model) throws IOException {
        File json = new File("reviews.json");
        List<Review> reviews = new ArrayList<>(objectMapper.readValue(json, new TypeReference<List<Review>>(){}));
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

}
