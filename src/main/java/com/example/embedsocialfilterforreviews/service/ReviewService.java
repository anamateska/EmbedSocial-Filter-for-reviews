package com.example.embedsocialfilterforreviews.service;

import com.example.embedsocialfilterforreviews.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> sortReviews(List<Review> reviews, String ratingOrder, String dateOrder);
    List<Review> filterAndSortReviews(List<Review> reviews, String textPriority, String ratingOrder, String dateOrder, int minRating);
}
