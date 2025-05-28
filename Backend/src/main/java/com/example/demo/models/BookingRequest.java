package com.example.demo.models;

public class BookingRequest {
    private int customerId;
    private int ticketId;
    private int showtimeId;
    private int movieId;
    private int bookedSeat;

    public BookingRequest(int customerId, int ticketId, int showtimeId, int movieId, int bookedSeat) {
        this.customerId = customerId;
        this.ticketId = ticketId;
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.bookedSeat = bookedSeat;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getBookedSeat() {
        return bookedSeat;
    }
}