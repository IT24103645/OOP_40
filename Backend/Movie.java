package com.example.cinelux;

class Movie {
    //replace arrays with lists?
    private static final int MAX_REVIEWS = 10;
    private static final int MAX_SHOWTIMES = 10;
    private int movieId;
    private String title;
    private String description;
    private MovieCategory category;
    private Review[] reviewArray = new Review[MAX_REVIEWS];
    private ShowTime[] showTimeArray = new ShowTime[MAX_SHOWTIMES];
    private int reviewCount = 0;
    private int showTimeCount = 0;

    public Movie(int movieId, String title, String description, MovieCategory category) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be empty.");
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty.");
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public ShowTime[] getShowTimes() {
        ShowTime[] published = new ShowTime[showTimeCount];
        int count = 0;
        for (int i = 0; i < showTimeCount; i++) {
            if (showTimeArray[i].getIsPublished()) published[count++] = showTimeArray[i];
        }
        return published;
    }

    public void addShowTime(ShowTime s) {
        for (int i = 0; i < showTimeCount; i++) {
            if (showTimeArray[i].getShowTimeId() == s.getShowTimeId()) {
                showTimeArray[i] = s;
                return;
            }
        }
        if (showTimeCount >= MAX_SHOWTIMES) throw new IllegalStateException("ShowTime array full.");
        showTimeArray[showTimeCount++] = s;
    }

    public Review[] getReviews() {
        return reviewArray;
    }

    public void addReview(Review r) {
        for (int i = 0; i < reviewCount; i++) {
            if (reviewArray[i].getReviewId() == r.getReviewId()) {
                reviewArray[i] = r;
                return;
            }
        }
        if (reviewCount >= MAX_REVIEWS) throw new IllegalStateException("Review array full.");
        reviewArray[reviewCount++] = r;
    }

    public double calcGrossRevenue() {
        double total = 0;
        for (int i = 0; i < showTimeCount; i++) {
            total += showTimeArray[i].calcShowtimeRevenue();
        }
        return total;
    }

    public void deleteReview(int reviewId) {
        for (int i = 0; i < reviewCount; i++) {
            if (reviewArray[i].getReviewId() == reviewId) {
                reviewArray[i] = null;
                return;
            }
        }
        throw new IllegalArgumentException("Review not found.");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public MovieCategory getCategory() {
        return category;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be empty.");
        this.title = title;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Description cannot be empty.");
        this.description = description;
    }

    public void setCategory(MovieCategory category) {
        this.category = category;
    }
}

enum MovieCategory {
    HORROR, ROMANCE, ACTION, DRAMA, COMEDY, FAMILY
}
