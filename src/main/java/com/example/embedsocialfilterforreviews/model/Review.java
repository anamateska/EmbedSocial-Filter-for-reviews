package com.example.embedsocialfilterforreviews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review{
    public Long id;
    public String reviewId;
    public String reviewFullText;
    public String reviewText;
    public Integer numLikes;
    public Integer numComments;
    public Integer numShares;
    public Integer rating;
    public String reviewCreatedOn;
    public Date reviewCreatedOnDate;
    public Long reviewCreatedOnTime;
    public Long reviewerId;
    public String reviewerUrl;
    public String reviewerName;
    public String reviewerEmail;
    public String sourceType;
    public Boolean isVerified;
    public String source;
    public String sourceName;
    public String sourceId;
    public List<String> tags;
    public String href;
    public String logoHref;
    public List<String> photos;
}