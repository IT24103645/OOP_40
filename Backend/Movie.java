package com.example.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    //for jackson
    private static final long serialVersionUID = 1L;

    //class eke attributes tikaz
    private int movieId;
    private String movieTitle;
    private String releaseDate; // changed from LocalDate to String
    private String description;
    private MovieCategory category;
    private List<Review> reviewList = new ArrayList<>();
    private List<ShowTime> showTimeList = new ArrayList<>();

    // default constructor
    public Movie() {
    }

    //parametrized constructor
    public Movie(int movieId, String movieTitle, String description, String category, String releaseDate) {
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

        try {
            this.category = MovieCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }

        if (releaseDate == null || releaseDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Release date cannot be null or empty");
        }

        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    //getters
    public int getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public MovieCategory getCategory() {
        return category;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public List<ShowTime> getShowTimeList() {
        return showTimeList;
    }

    //setters
    public void setMovieTitle(String movieTitle) {
        //movieTitle ekata null value ekak hari nettm space witrak tiyana ekak dunnoth error ekak danwa
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty.");
        }
        this.movieTitle = movieTitle;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setReleaseDate(String releaseDate) {
        //releaseDate ekata null value ekak hari nettm space witrak tiyana ekak dunnoth error ekak danwa
        if (releaseDate == null || releaseDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Release date cannot be null or empty.");
        }
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        //description ekata null value ekak hari nettm space witrak tiyana ekak dunnoth error ekak danwa
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    public void setCategory(MovieCategory category) {
        //category ekata null value ekak dunnoth error ekak danwa
        if (category == null) {
            throw new IllegalArgumentException("Movie category cannot be null.");
        }
        this.category = category;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = (reviewList != null) ? reviewList : new ArrayList<>();
    }

    public void setShowTimeList(List<ShowTime> showTimeList) {
        this.showTimeList = (showTimeList != null) ? showTimeList : new ArrayList<>();
    }

    //custom methods
    //showtime object ekak add kranwa showtime list ekata
    public void addShowTime(ShowTime s) {
        showTimeList.removeIf(existing -> existing.getShowTimeId() == s.getShowTimeId());
        showTimeList.add(s);
    }

    //showtime list eke showtime object eka hoyala tiyanwanm eka delete karanwa
    public void deleteShowTime(int showTimeId) {
        showTimeList.removeIf(s -> s.getShowTimeId() == showTimeId);
    }

    //review list ekata review object ekak add karanwa
    public void addReview(Review r) {
        //review list eke e object eka tiynwanm issrlama ain krnwa
        reviewList.removeIf(existing -> existing.getReviewId() == r.getReviewId());

        //ita passe aluth eka add kranwa
        reviewList.add(r);
    }

    //review list eken review object ekak ain kranwa
    public void deleteReview(int reviewId) {
        reviewList.removeIf(r -> r.getReviewId() == reviewId);
    }

}
