package com.example.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowTime implements Serializable {
    //for jackson
    private static final long serialVersionUID = 1L;

    //class eke attributes tika
    private int showTimeId;
    private CinemaHall cinemaHall;
    private String time;  // Changed from LocalDateTime to String
    private Boolean[] seatsArray;
    private List<Ticket> ticketsSoldList = new ArrayList<>();
    private double showTimeRevenue;
    private boolean isPublished;

    //default constructor
    public ShowTime() {
    }

    //parameterized constructor
    public ShowTime(int showTimeId, CinemaHall cinemaHall, String time, int seatsArrayLength) {

        //showtime id eka negative wunoth error ekak danawa
        if (showTimeId <= 0) {
            throw new IllegalArgumentException("ShowTime ID must be greater than 0");
        }

        //cinemaHall null wenna be
        if (cinemaHall == null) {
            throw new IllegalArgumentException("Cinema hall cannot be null");
        }

        //time eka null OR empty wenna be
        if (time == null || time.isBlank()) {
            throw new IllegalArgumentException("Showtime cannot be null or empty");
        }

        //seatsArray kiyana array eke length eka negative wenna be
        if (seatsArrayLength <= 0) {
            throw new IllegalArgumentException("Seats array length must be greater than 0");
        }

        this.showTimeId = showTimeId;
        this.cinemaHall = cinemaHall;
        this.time = time;
        this.seatsArray = new Boolean[seatsArrayLength];
        Arrays.fill(this.seatsArray, false);
        this.isPublished = true;
    }

    // getters
    public int getShowTimeId() {
        return showTimeId;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public String getTime() {
        return time;
    }

    public Boolean[] getSeatsArray() {
        return seatsArray;
    }

    public List<Ticket> getTicketsSoldList() {
        return ticketsSoldList;
    }

    public double getShowTimeRevenue() {
        return showTimeRevenue;
    }

    // seters
    public void setShowTimeId(int showTimeId) {
        this.showTimeId = showTimeId;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSeatsArray(Boolean[] seatsArray) {
        this.seatsArray = seatsArray;
    }

    public void setTicketsSoldList(List<Ticket> ticketsSoldList) {
        this.ticketsSoldList = ticketsSoldList != null ? ticketsSoldList : new ArrayList<>();
    }

    public void setShowTimeRevenue(double showTimeRevenue) {
        this.showTimeRevenue = showTimeRevenue;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    //custom methods
    public void bookSeat(int position) {
        if (position > 0 && position <= seatsArray.length && !seatsArray[position]) {
            seatsArray[position] = true;
        }
    }

    public double calcShowtimeRevenue() {
        double sum = 0;
        for (Ticket t : ticketsSoldList) {
            sum += (t.getType() == TicketType.ADULT) ? 1500 : 1300;
        }
        showTimeRevenue = sum;
        return sum;
    }

    public void addTicket(Ticket t) {
        ticketsSoldList.removeIf(existing -> existing.getTicketId() == t.getTicketId());
        ticketsSoldList.add(t);
    }

    public void deleteTicket(int ticketId) {
        ticketsSoldList.removeIf(t -> t.getTicketId() == ticketId);
    }
}
