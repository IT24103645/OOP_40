package com.example.demo.models;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ticketId;
    private int movieId;
    private String movieTitle;
    private CinemaHall cinemaHall;
    private String time;  // changed from LocalDateTime
    private int seatNumber;
    private String dateIssued;  // changed from LocalDateTime
    private int paymentId;
    private TicketType type;
    private TicketStatus status = TicketStatus.PENDING; // default to pending

    //default constructor
    public Ticket() {
    }

    //parameterized constructor
    public Ticket(int ticketId, int movieId, String movieTitle, CinemaHall cinemaHall, String time,
                  int seatNumber, int paymentId, TicketType type, String dateIssued) {

        if (ticketId <= 0) {
            throw new IllegalArgumentException("Ticket ID must be greater than 0");
        }

        if (movieId <= 0) {
            throw new IllegalArgumentException("Movie ID must be greater than 0");
        }

        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty");
        }

        if (cinemaHall == null) {
            throw new IllegalArgumentException("Cinema hall cannot be null");
        }

        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Showtime cannot be null or empty");
        }

        if (dateIssued == null || dateIssued.trim().isEmpty()) {
            throw new IllegalArgumentException("Date issued cannot be null or empty");
        }

        if (seatNumber <= 0) {
            throw new IllegalArgumentException("Seat number must be greater than 0");
        }

        if (paymentId <= 0) {
            throw new IllegalArgumentException("Payment ID must be greater than 0");
        }

        if (type == null) {
            throw new IllegalArgumentException("Ticket type cannot be null");
        }

        this.ticketId = ticketId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.cinemaHall = cinemaHall;
        this.time = time;
        this.seatNumber = seatNumber;
        this.paymentId = paymentId;
        this.type = type;
        this.dateIssued = dateIssued;
    }

    // Getters
    public int getTicketId() {
        return ticketId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public String getTime() {
        return time;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public TicketType getType() {
        return type;
    }

    public TicketStatus getStatus() {
        return status;
    }

    // Setters
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
