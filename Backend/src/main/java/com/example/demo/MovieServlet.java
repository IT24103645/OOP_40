package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;
import com.example.demo.models.Movie;
import com.example.demo.utils.DataStore;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the movie ID and action from the request parameters
        String movieIdParam = request.getParameter("id");
        String action = request.getParameter("action");

        //sort movies and send user to index.jsp
        if ("sort".equalsIgnoreCase(action)) {
            List<Movie> movies = DataStore.getAllMovies();

            // Insertion sort - newest release date first
            for (int j = 1; j < movies.size(); j++) {
                Movie key = movies.get(j);
                String keyDate = key.getReleaseDate(); // format: yyyy-MM-dd
                int i = j - 1;

                while (i >= 0 && movies.get(i).getReleaseDate().compareTo(keyDate) < 0) {
                    movies.set(i + 1, movies.get(i));
                    i = i - 1;
                }
                movies.set(i + 1, key);
            }

            request.setAttribute("movieList", movies);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }


        // Check if the movie ID is provided in the URL
        if (movieIdParam != null) {
            // Handle DELETE action
            if ("delete".equalsIgnoreCase(action)) {
                try {
                    int movieId = Integer.parseInt(movieIdParam);
                    boolean success = DataStore.deleteMovie(movieId);

                    if (success) {
                        response.sendRedirect("movies"); // Redirect to updated movie list
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Failed to delete movie");
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
                }
                return; // Stop further processing
            }

            // If the action is "edit", handle the edit logic
            if ("edit".equalsIgnoreCase(action)) {
                try {
                    int movieId = Integer.parseInt(movieIdParam);
                    Movie movie = DataStore.getMovieById(movieId);

                    if (movie != null) {
                        request.setAttribute("movie", movie);
                        request.getRequestDispatcher("editMovie.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
                }
                return; // No further processing needed after forward

            } else {
                // If action is not "edit", just show movie details
                try {
                    int movieId = Integer.parseInt(movieIdParam);
                    Movie movie = DataStore.getMovieById(movieId);

                    if (movie != null) {
                        request.setAttribute("movie", movie);
                        request.getRequestDispatcher("movieDetails.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
                }
            }

        } else {
            // If no movie ID is provided, show the movie list
            List<Movie> movies = DataStore.getAllMovies();
            request.setAttribute("movieList", movies);

            // Determine the user's role from the session
            HttpSession session = request.getSession(false);
            String role = (session != null) ? (String) session.getAttribute("role") : null;

            // Forward based on the user role
            if ("admin".equals(role)) {
                request.getRequestDispatcher("adminLanding.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("==== /movies POST hit ====");
        System.out.println("action: " + request.getParameter("action"));
        System.out.println("title: " + request.getParameter("title"));
        System.out.println("releaseDate: " + request.getParameter("releaseDate"));



        String action = request.getParameter("action");
        if ("edit".equalsIgnoreCase(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            String title = request.getParameter("title");
//            String desc = request.getParameter("description");
//            String category = request.getParameter("category");
//            String releaseDate = request.getParameter("releaseDate");
//
//            boolean success = DataStore.editMovie(id, title, desc, category, releaseDate);
//            response.getWriter().print(success ? "success" : "fail");

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String desc = request.getParameter("description");
                String category = request.getParameter("category");
                String releaseDate = request.getParameter("releaseDate");

                boolean success = DataStore.editMovie(id, title, desc, category, releaseDate);

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/movies?id=" + id);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } else {
            // Add a movie

            String title = request.getParameter("title");
            String desc = request.getParameter("description");
            String category = request.getParameter("category");
            String releaseDate = request.getParameter("releaseDate");

            boolean success = DataStore.addMovie(title, desc, category, releaseDate);

            if (success) {
                // Refresh the list and forward to adminLanding.jsp
                List<Movie> movies = DataStore.getAllMovies();
                request.setAttribute("movieList", movies);
                request.getRequestDispatcher("adminLanding.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        boolean success = DataStore.deleteMovie(id);
//        response.getWriter().print(success ? "success" : "fail");
//    }
}
