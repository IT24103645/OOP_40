package com.example.demo;

import com.example.demo.models.TicketType;
import com.example.demo.utils.DataStore;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("paymentComplete".equalsIgnoreCase(action)) {
            try {
                // Step 1: Get ticket type from session
                HttpSession session = request.getSession();
                TicketType ticketType = (TicketType) session.getAttribute("buyTicketType");

                if (ticketType == null) {
                    throw new ServletException("Ticket type not found in session");
                }

                // Step 2: Create payment and get payment ID
                int paymentId = DataStore.createPaymentForTicketType(ticketType);

                if (paymentId == -1) {
                    throw new ServletException("Failed to create payment");
                }

                // Step 3: Store payment ID in session
                session.setAttribute("buyTicketPaymentId", paymentId);

                // Step 4: Forward to ticket servlet with paymentComplete action
                response.sendRedirect(request.getContextPath() + "/ticket?action=paymentComplete");

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Error processing payment completion", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to completePayment.jsp
        request.getRequestDispatcher("completePayment.jsp").forward(request, response);
    }

}