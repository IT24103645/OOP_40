<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.models.Customer" %>
<%@ page import="com.example.demo.models.Ticket" %>
<%@ page import="com.example.demo.models.TicketStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Buy Ticket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="navbar">
    <div class="logo">Cinelux</div>
    <div class="nav-buttons">
        <% String role = (String) session.getAttribute("role"); %>
        <% String username = (String) session.getAttribute("user"); %>

        <% if (role == null) { %>
        <a class="nav-btn" href="movies">Home</a>
        <a class="nav-btn" href="signup.jsp">Signup</a>
        <a class="nav-btn" href="login.jsp">Login</a>
        <% } else if ("admin".equals(role)) { %>
        <a class="nav-btn" href="movies">Home</a>
        <a class="nav-btn-logout" href="customer?action=logout">Logout</a>
        <div class="nav-user">
            <img class="nav-userIcon" src="./image 17.png" />
            <div class="nav-userDetails">
                <p><%= username %></p>
                <p class="nav-userStatus">ADMIN</p>
            </div>
        </div>
        <% } else if ("user".equals(role)) { %>
        <a class="nav-btn" href="movies">Home</a>
        <a class="nav-btn" href="myProfile.jsp">My Profile</a>
        <a class="nav-btn-logout" href="customer?action=logout">Logout</a>
        <div class="nav-user">
            <img class="nav-userIcon" src="./image 17.png" />
            <div class="nav-userDetails">
                <p><%= username %></p>
                <p class="nav-userStatus">Online</p>
            </div>
        </div>
        <% } %>
    </div>
</div>

<div class="myTickets">
    <h2 class="myTickets-title">My Tickets</h2>
    <div class="myTickets-tickets">
        <%
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null && customer.getTickets() != null) {
                for (Ticket ticket : customer.getTickets()) {
        %>
        <div class="myTicket">
            <h3><%= ticket.getMovieTitle() %></h3>
            <div class="myTickets-details">
                <div class="myTicket-detail-container">
                    <p>Ticket Type :</p>
                    <p>Cinema hall :</p>
                    <p>Date :</p>
                    <p>Time :</p>
                    <p>Seat Number :</p>
                    <p>Ticket status :</p>
                </div>
                <div class="myTicket-detail-container">
                    <p><%= ticket.getType() %></p>
                    <p><%= ticket.getCinemaHall() %></p>
                    <p><%= ticket.getDateIssued() %></p>
                    <p><%= ticket.getTime() %></p>
                    <p><%= ticket.getSeatNumber() + 1 %></p>
                    <p><%= ticket.getStatus() %></p>
                </div>
            </div>
            <div class="myTickets-btns">
                <%
                    if (ticket.getStatus() == TicketStatus.FAILED) {
                %>
                <button class="orange-btn">Edit ticket</button>
                <button class="red-btn">Cancel ticket</button>
                <%
                } else if (ticket.getStatus() == TicketStatus.PENDING || ticket.getStatus() == TicketStatus.SUCCESS) {
                %>
                <button class="red-btn">Cancel ticket</button>
                <%
                    }
                %>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</div>

</body>
</html>
