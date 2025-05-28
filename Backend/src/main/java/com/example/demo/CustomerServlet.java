package com.example.demo;

import com.example.demo.models.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.demo.utils.DataStore;
import com.example.demo.models.Movie;
import java.util.List;

import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private static final String ADMIN_USERNAME = "oop40isum";
    private static final String ADMIN_PASSWORD = "isum123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("logout".equalsIgnoreCase(action)) {
            session.setAttribute("user", null);
            session.setAttribute("role", null);
            response.sendRedirect("movies");
        } else {
            //method that takes in customer ID and finds the customer details and set it to session attributes
            //but for that to work we need a method in dataStore that can get 1 customer through ID
            //maybe we can do this when user logs in ?? so no doGet method needed here
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");

        // Admin login check
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", "ISUM");
            session.setAttribute("role", "admin");

            response.sendRedirect("movies");  // ✔ goes to MovieServlet
            return;
        }


        if ("login".equalsIgnoreCase(action)) {
            boolean valid = DataStore.validateCustomer(username, password);

            if (valid) {
                Customer customer = DataStore.getCustomerByCredentials(username, password); // 🆕 Fetch full customer
                HttpSession session = request.getSession();

                session.setAttribute("user", username);
                session.setAttribute("role", "user");
                session.setAttribute("customer", customer); // 🆕 Store the whole customer object

                response.sendRedirect("movies");
            } else {
                response.getWriter().print("fail");
            }
        } else {
            //Add customer
            boolean success = DataStore.createCustomer(username, password);

            if (success) {
                Customer customer = DataStore.getCustomerByCredentials(username, password); // 🆕 Get customer after creation
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                session.setAttribute("role", "user");
                session.setAttribute("customer", customer); // 🆕 Store the whole customer object

                response.sendRedirect("movies");
            } else {
                response.sendRedirect("signup.jsp?error=true");
            }
        }
    }

}
