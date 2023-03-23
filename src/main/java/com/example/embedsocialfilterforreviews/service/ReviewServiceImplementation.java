package com.example.embedsocialfilterforreviews.service;

import com.example.embedsocialfilterforreviews.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImplementation implements ReviewService {

    //This method is used for sorting reviews, first by rating and then by date
    @Override
    public List<Review> sortReviews(List<Review> reviews, String ratingOrder, String dateOrder) {
            List<Review> sortedReviews = new ArrayList<>();
            if (ratingOrder.equals("Highest First")) {
                //Sort by Highest rating first + Oldest date first
                if (dateOrder.equals("Oldest First")) {
                    sortedReviews = reviews.stream().sorted(Comparator.comparing(Review::getRating).reversed().thenComparing(Review::getReviewCreatedOnDate)).collect(Collectors.toList());
                }
                //Sort by Highest rating first + Newest date first
                else {
                    sortedReviews = reviews.stream().sorted(Comparator.comparing(Review::getRating).thenComparing(Review::getReviewCreatedOnDate).reversed()).collect(Collectors.toList());
                }
            } else {
                //Sort by Lowest rating first + Oldest date first
                if (dateOrder.equals("Oldest First")) {
                    sortedReviews = reviews.stream().sorted(Comparator.comparing(Review::getRating).thenComparing(Review::getReviewCreatedOnDate)).collect(Collectors.toList());
                }
                //Sort by Lowest rating first + Newest date first
                else {
                    sortedReviews = reviews.stream().sorted(Comparator.comparing(Review::getRating).reversed().thenComparing(Review::getReviewCreatedOnDate).reversed()).collect(Collectors.toList());
                }
            }
            return sortedReviews;
    }

    //This method is used to filter the reviews first and then sort them, according to the requirements
    @Override
    public List<Review> filterAndSortReviews(List<Review> reviews, String textPriority, String ratingOrder, String dateOrder, int minRating) {
        //Filter by minimum rating
        List<Review> filteredReviews = reviews.stream().filter(review -> review.getRating() >= minRating).collect(Collectors.toList());

        //If not prioritized by text, sort previously filtered reviews and return resulting list
        if(textPriority.equals("No")){
            filteredReviews=sortReviews(filteredReviews,ratingOrder,dateOrder);
            return filteredReviews;
        }

        //If prioritized by text, divide in two separate lists (text/no text) and sort them individually
        //Add the objects from the list of reviews with no text to the list of reviews with text
        //After achieving the right order, return the resulting list
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