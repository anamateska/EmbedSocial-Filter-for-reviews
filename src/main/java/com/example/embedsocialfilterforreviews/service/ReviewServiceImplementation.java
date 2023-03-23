package com.example.embedsocialfilterforreviews.service;

import com.example.embedsocialfilterforreviews.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplementation implements ReviewService {

    @Override
    public List<Review> sortReviews(List<Review> reviews, String ratingOrder, String dateOrder) {
            List<Review> sorted = new ArrayList<>();
            if (ratingOrder.equals("Highest First")) {
                if (dateOrder.equals("Oldest First")) {
                    sorted = reviews.stream().sorted(Comparator.comparing(Review::getRating).reversed().thenComparing(Review::getReviewCreatedOnDate)).collect(Collectors.toList());
                } else {
                    sorted = reviews.stream().sorted(Comparator.comparing(Review::getRating).thenComparing(Review::getReviewCreatedOnDate).reversed()).collect(Collectors.toList());
                }
            } else {
                if (dateOrder.equals("Oldest First")) {
                    sorted = reviews.stream().sorted(Comparator.comparing(Review::getRating).thenComparing(Review::getReviewCreatedOnDate)).collect(Collectors.toList());
                } else {
                    sorted = reviews.stream().sorted(Comparator.comparing(Review::getRating).reversed().thenComparing(Review::getReviewCreatedOnDate).reversed()).collect(Collectors.toList());
                }
            }
            return sorted;
    }

    @Override
    public List<Review> filterAndSortReviews(List<Review> reviews, String textPriority, String ratingOrder, String dateOrder, int minRating) {
        List<Review> filteredReviews = reviews.stream().filter(review -> review.getRating() >= minRating).collect(Collectors.toList());
        if(textPriority.equals("No")){
            filteredReviews=sortReviews(filteredReviews,ratingOrder,dateOrder);
            return filteredReviews;
        }
        else {
            List<Review> textReviews = filteredReviews.stream().filter(review -> review.getReviewFullText().length() > 0).collect(Collectors.toList());
            List<Review> noTextReviews = filteredReviews.stream().filter(review -> review.getReviewFullText().length() == 0).collect(Collectors.toList());
            textReviews=sortReviews(textReviews,ratingOrder,dateOrder);
            noTextReviews=sortReviews(noTextReviews,ratingOrder,dateOrder);
            for(Review review : noTextReviews){
                textReviews.add(review);
            }
            return textReviews;
        }
    }
}