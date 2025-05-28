package com.example.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    //Jackson ekta ona attribute ekak
    private static final long serialVersionUID = 1L;

    //class eke attributes tika
    private int customerId;
    private String customerName;
    private String password;
    private List<Ticket> ticketsBoughtList = new ArrayList<>();
    private List<Review> reviewsAddedList = new ArrayList<>();

    // default constructor eka
    public Customer() {
        // Initialize lists to avoid null pointers during deserialization
        this.ticketsBoughtList = new ArrayList<>();
        this.reviewsAddedList = new ArrayList<>();
    }

    //parameterized constructor eka
    public Customer(int customerId, String customerName, String password) {
        if (customerName == null || customerName.trim().isEmpty()) {
            //customerName ekata null value ekak hari nettm space witrak tiyana ekak dunnoth error ekak danwa
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }

        if (password == null) {
            //password ekata null value ekak dunnoth error ekak danwa
            throw new IllegalArgumentException("Password cannot be null");
        }

        this.customerId = customerId;
        this.customerName = customerName;
        this.password = password;
        this.ticketsBoughtList = new ArrayList<>();
        this.reviewsAddedList = new ArrayList<>();
    }

    //getters
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Ticket> getTickets() {
        return ticketsBoughtList;
    }

    public String getPassword() {
        return password;
    }

    public List<Review> getReviews() {
        return reviewsAddedList;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        this.password = password;
    }

    public void setCustomerName(String customerName) {
        //customerName ekata null value ekak hari nettm space witrak tiyana ekak dunnoth error ekak danwa
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        this.customerName = customerName;
    }

    public void setTickets(List<Ticket> ticketsBoughtList) {
        this.ticketsBoughtList = (ticketsBoughtList != null) ? ticketsBoughtList : new ArrayList<>();
    }

    public void setReviews(List<Review> reviewsAddedList) {
        this.reviewsAddedList = (reviewsAddedList != null) ? reviewsAddedList : new ArrayList<>();
    }

    //custom methods
    //review list ekata review ekak add kranwa
    public void addReview(Review r) {
        reviewsAddedList.removeIf(existing -> existing.getReviewId() == r.getReviewId());
        reviewsAddedList.add(r);
    }

    //review list eke review eka tiynwanm delete karanwa
    public void deleteReview(int reviewId) {
        reviewsAddedList.removeIf(r -> r.getReviewId() == reviewId);
    }

    //ticket list ekata ticket ekak add kranwa
    public void addTicket(Ticket t) {
        ticketsBoughtList.removeIf(existing -> existing.getTicketId() == t.getTicketId());
        ticketsBoughtList.add(t);
    }

    //ticket list eke ticket eka tiynwanm delete kranwa
    public void deleteTicket(int ticketId) {
        ticketsBoughtList.removeIf(t -> t.getTicketId() == ticketId);
    }
}
