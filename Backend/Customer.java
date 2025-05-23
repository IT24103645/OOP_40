package com.example.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
   
    private static final long serialVersionUID = 1L;

  
    private int customerId;
    private String customerName;
    private String password;
    private List<Ticket> ticketsBoughtList = new ArrayList<>();
    private List<Review> reviewsAddedList = new ArrayList<>();

   
    public Customer() {
        
        this.ticketsBoughtList = new ArrayList<>();
        this.reviewsAddedList = new ArrayList<>();
    }

 
    public Customer(int customerId, String customerName, String password) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }

        if (password == null) {
          
            throw new IllegalArgumentException("Password cannot be null");
        }

        this.customerId = customerId;
        this.customerName = customerName;
        this.password = password;
        this.ticketsBoughtList = new ArrayList<>();
        this.reviewsAddedList = new ArrayList<>();
    }

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


    public void addReview(Review r) {
        reviewsAddedList.removeIf(existing -> existing.getReviewId() == r.getReviewId());
        reviewsAddedList.add(r);
    }

   
    public void deleteReview(int reviewId) {
        reviewsAddedList.removeIf(r -> r.getReviewId() == reviewId);
    }

   
    public void addTicket(Ticket t) {
        ticketsBoughtList.removeIf(existing -> existing.getTicketId() == t.getTicketId());
        ticketsBoughtList.add(t);
    }

   
    public void deleteTicket(int ticketId) {
        ticketsBoughtList.removeIf(t -> t.getTicketId() == ticketId);
    }
}
