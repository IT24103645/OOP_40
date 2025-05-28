<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.models.Movie" %>
<%@ page import="com.example.demo.models.ShowTime" %>
<%@ page import="com.example.demo.models.TicketType" %>
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

<% Movie movie = (Movie) session.getAttribute("buyTicketMovie"); %>


<div class="signup">
    <h2 class="signup-title">Buy Ticket</h2>
    <form method="post" action="${pageContext.request.contextPath}/showtime?action=setSession">

        <div class="signup-inputs">
            <p>Movie : <%= movie.getMovieTitle() %> </p>

            <div class="signup-input">
                <p>Select Showtime</p>
                <select name="showtimeId" class="select-Input">
                    <% for (ShowTime s : movie.getShowTimeList()) { %>
                    <option value="<%= s.getShowTimeId() %>">
                        <%= s.getTime() %> • <%= s.getCinemaHall() %>
                    </option>
                    <% } %>
                </select>
            </div>

            <div class="signup-input">
                <p>Ticket Type</p>
                <select name="ticketType" class="select-Input">
                    <% for (TicketType type : TicketType.values()) { %>
                    <option value="<%= type.name() %>"><%= type.name() %></option>
                    <% } %>
                </select>
            </div>
        </div>

        <div class="signup-btns">
            <button class="signup-signup" type="submit">Continue →</button>
            <button class="signup-cancel" type="reset">Cancel</button>
        </div>
    </form>
</div>

</body>
</html>
