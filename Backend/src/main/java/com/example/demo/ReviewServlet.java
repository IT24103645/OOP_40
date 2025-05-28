package com.example.demo;

import com.example.demo.models.Customer;
import com.example.demo.models.Movie;
import com.example.demo.models.Review;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.demo.utils.DataStore;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getPage".equals(action)) {
            try {
                int movieId = Integer.parseInt(request.getParameter("movieId"));
                Movie movie = DataStore.getMovieById(movieId);

                if (movie != null) {
                    request.setAttribute("reviewMovie", movie);
                    request.getRequestDispatcher("addReview.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            try {
                int movieId = Integer.parseInt(request.getParameter("movieId"));
                String reviewBody = request.getParameter("reviewBody");
                int starCount = Integer.parseInt(request.getParameter("starCount"));

                HttpSession session = request.getSession();
                Customer customer = (Customer) session.getAttribute("customer");

                if (customer == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
                    return;
                }

                boolean success = DataStore.createReview(movieId, customer, reviewBody, starCount);

                if (!success) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                    return;
                }

                session.setAttribute("customer", customer);
                request.getRequestDispatcher("myReviews.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

}
