package com.example.demo.models;

import java.io.Serializable;

public class Review implements Serializable {
    //Jackson ekata ona attribute ekak
    private static final long serialVersionUID = 1L;

    //class ekek attributes tika
    private int reviewId;
    private String customerName;
    private String movieTitle;
    private int starCount;
    private String reviewBody;

    // default constructor
    public Review() {
    }

    //parameterized constructor
    public Review(int reviewId, String customerName, String movieTitle, int starCount, String reviewBody) {
        if (reviewId <= 0) {
            throw new IllegalArgumentException("Review ID must be greater than 0");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty");
        }
        if (starCount < 1 || starCount > 5) {
            throw new IllegalArgumentException("Star count must be between 1 and 5");
        }
        if (reviewBody == null || reviewBody.trim().isEmpty()) {
            throw new IllegalArgumentException("Review body cannot be null or empty");
        }
        if (reviewBody.length() > 100) {
            throw new IllegalArgumentException("Review body cannot exceed 100 characters");
        }

        this.reviewId = reviewId;
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.starCount = starCount;
        this.reviewBody = reviewBody;
    }

    //getters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getStarCount() {
        return starCount;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    //setters
    public void setStarCount(int starCount) {
        //starcount eka 1-5 athara nettm erorr ekak danwa
        if (starCount < 1 || starCount > 5) {
            throw new IllegalArgumentException("Star count must be between 1 and 5.");
        }
        this.starCount = starCount;
    }

    public void setReviewBody(String reviewBody) {
        //reviewBody ekata null value ekak hari nettm space witrak tiyana ekak dunnoth error ekak danwa
        if (reviewBody == null || reviewBody.trim().isEmpty()) {
            throw new IllegalArgumentException("Review body cannot be empty.");
        }

        //reviewBody eka akuru 100t wada wedinam error ekak danwa
        if (reviewBody.length() > 100) {
            throw new IllegalArgumentException("Review body cannot be longer than 100 characters.");
        }
        this.reviewBody = reviewBody;
    }
}
