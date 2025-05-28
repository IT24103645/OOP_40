package com.example.demo;

import com.example.demo.models.Movie;
import com.example.demo.models.ShowTime;
import com.example.demo.models.TicketType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.demo.utils.DataStore;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/showtime")
public class ShowTimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        //this handles when admin clicks "add showtime" button. fowards admin to addShowtime.jsp with movie object
        if ("add".equalsIgnoreCase(action)) {
            try {
                // Get the movieId from the request parameters
                int movieId = Integer.parseInt(request.getParameter("id"));

                // Retrieve the Movie object from the DataStore
                Movie movie = DataStore.getMovieById(movieId);

                if (movie != null) {
                    // Forward to addShowTime.jsp with movieId as a request attribute
                    request.setAttribute("movieId", movieId);
                    request.getRequestDispatcher("addShowTime.jsp").forward(request, response);
                } else {
                    // Movie not found, send error response
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                }
            } catch (NumberFormatException e) {
                // Invalid movieId
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
            }
        }
//        else {
//            // Regular get: return showtimes as JSON for a specific movie
//            int movieId = Integer.parseInt(request.getParameter("movieId"));
//            response.setContentType("application/json");
//            response.getWriter().print(DataStore.getShowtimesJson(movieId));
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            try {
                int movieId = Integer.parseInt(request.getParameter("id"));
                boolean success = DataStore.addShowTimeToMovie(movieId, request);

                if (success) {
                    response.sendRedirect("movies");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add showtime.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID.");
            }
        } else if ("setSession".equalsIgnoreCase(action)) {
            try {
                int showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
                String ticketTypeParam = request.getParameter("ticketType");

                Movie movie = (Movie) request.getSession().getAttribute("buyTicketMovie");

                if (movie == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No movie in session.");
                    return;
                }

                ShowTime selectedShowTime = movie.getShowTimeList().stream()
                        .filter(s -> s.getShowTimeId() == showtimeId)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid showtimeId: " + showtimeId));

                TicketType selectedTicketType = TicketType.valueOf(ticketTypeParam);

                HttpSession session = request.getSession();
                session.setAttribute("buyTicketShowtime", selectedShowTime);
                session.setAttribute("buyTicketType", selectedTicketType);

                response.sendRedirect("pickSeat.jsp");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid showtime or ticket type.");
            }
        } else if("seat".equalsIgnoreCase(action)){
            try {
                // Parse seat number from request parameter
                int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));

                // Save seat number in session
                HttpSession session = request.getSession();
                session.setAttribute("seatNumber", seatNumber);

                // Forward to PaymentServlet
                request.getRequestDispatcher("/payment").forward(request, response);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid seat number.");
            }
        }
    }



}
