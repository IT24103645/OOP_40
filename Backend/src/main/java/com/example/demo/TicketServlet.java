package com.example.demo;

import com.example.demo.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import com.example.demo.utils.DataStore;

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        // forward user to buyTicket page
        if ("getPage".equalsIgnoreCase(action)) {
            String movieIdParam = request.getParameter("movieId");

            if (movieIdParam != null) {
                try {
                    int movieId = Integer.parseInt(movieIdParam);
                    com.example.demo.models.Movie movie = DataStore.getMovieById(movieId);

                    if (movie != null) {
                        // ✅ Store movie in session attribute instead of request
                        HttpSession session = request.getSession();
                        session.setAttribute("buyTicketMovie", movie);

                        try {
                            request.getRequestDispatcher("/buyTicket.jsp").forward(request, response);
                        } catch (Exception e) {
                            throw new IOException("Failed to forward to buyTicket.jsp", e);
                        }
                        return;
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                        return;
                    }

                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movieId format");
                    return;
                }
            }
        }


        if("bookingRequest".equalsIgnoreCase(action)){
            //take session data then make ticket object (status automatically initialized to "Pending")

            //create a booking request

            //add the booking request to bookingQueue

            //foward user to myTickets.jsp (??? do we need to set customer object)

            //optional : prevent user from going back to signup pages
            //optional : delete session attributes
        }

        if("setSession".equalsIgnoreCase(action)){
            //takes in ticketID then sets the values to session attributes including the seat
        }

        if ("paymentComplete".equals(action)) {
            try {
                // Step 1: Retrieve all required data from session
                HttpSession session = request.getSession();

                Movie movie = (Movie) session.getAttribute("buyTicketMovie");
                ShowTime showTime = (ShowTime) session.getAttribute("buyTicketShowtime");
                TicketType ticketType = (TicketType) session.getAttribute("buyTicketType");
                int seatNumber = (int) session.getAttribute("seatNumber");
                int paymentId = (int) session.getAttribute("buyTicketPaymentId");

                Customer customer = (Customer) session.getAttribute("customer");
                int customerId = customer.getCustomerId();

                // Step 2: Create new ticket
                int ticketId = DataStore.createTicket(movie, showTime, ticketType, seatNumber, paymentId, customerId);

                if (ticketId == -1) {
                    throw new ServletException("Failed to create ticket");
                }

                // Step 3: Create and add booking request to queue
                BookingQueue bookingQueue = (BookingQueue) getServletContext().getAttribute("bookingQueue");
                BookingRequest bookingRequest = new BookingRequest(
                        customerId,
                        ticketId,
                        showTime.getShowTimeId(),
                        movie.getMovieId(),
                        seatNumber // Converting to 0-based index if needed
                );
                bookingQueue.insert(bookingRequest);

                // Step 4: Refresh customer data and forward to myTickets page
                Customer updatedCustomer = DataStore.getCustomerById(customerId);
                session.setAttribute("customer", updatedCustomer);

                request.getRequestDispatcher("/myTickets.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Error processing payment completion", e);
            }
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean result = DataStore.handleTicketPurchase(request);
        response.getWriter().print(result ? "success" : "fail");
    }
}
