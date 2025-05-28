package com.example.demo.listeners;

import com.example.demo.models.*;
import com.example.demo.utils.DataStore;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class BookingQueueListener implements ServletContextListener {

    private Thread bookingProcessorThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BookingQueue queue = new BookingQueue();
        sce.getServletContext().setAttribute("bookingQueue", queue);

        bookingProcessorThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(60000); // wait for 1 minute
                    if (Thread.currentThread().isInterrupted()) {
                        break;  // Exit if the thread has been interrupted
                    };

                    while (!queue.isEmpty()) {
                        BookingRequest request = queue.remove();

                        try {
                            // If the ticket doesn't exist, skip (likely canceled by user)
                            if (!DataStore.ticketExistsForCustomer(request.getCustomerId(), request.getTicketId())) {
                                continue;
                            }

                            // Get the movie
                            Movie movie = DataStore.getMovieById(request.getMovieId());
                            if (movie == null) {
                                throw new IllegalStateException("Movie not found for movieId: " + request.getMovieId());
                            }


                            // Get the showtime or throw error
                            ShowTime showtime = movie.getShowTimeList().stream()
                                    .filter(s -> s.getShowTimeId() == request.getShowtimeId())
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalStateException("Showtime not found for showtimeId: " + request.getShowtimeId()));

                            // Check and mark seat
                            Boolean[] seats = showtime.getSeatsArray();
                            int seatIndex = request.getBookedSeat();
                            if (seatIndex < 0 || seatIndex >= seats.length) {
                                throw new IllegalStateException("Invalid seat index: " + seatIndex + " for showtimeId: " + showtime.getShowTimeId());
                            }
                            if (seats[seatIndex]) {
                                DataStore.updateTicketStatus(request.getCustomerId(), request.getTicketId(), TicketStatus.FAILED);
                                continue;
                            }

                            // Mark seat as booked
                            seats[seatIndex] = true;

                            // ✅ Successfully booked
                            DataStore.updateTicketStatus(request.getCustomerId(), request.getTicketId(), TicketStatus.SUCCESS);

                        } catch (IllegalStateException e) {
                            // Log and skip this request
                            e.printStackTrace();
                        }
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Properly restore the interrupt flag
                    break; // Exit the loop gracefully
                }
            }
        });

        bookingProcessorThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (bookingProcessorThread != null) {
            bookingProcessorThread.interrupt();
        }
    }
}
