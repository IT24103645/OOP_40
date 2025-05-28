<!-- movieDetails.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.models.Movie" %>
<%@ page import="com.example.demo.models.Review" %>
<%@ page import="com.example.demo.models.ShowTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Movie Details</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <div class="logo">Cinelux</div>
    <div class="nav-buttons">
        <!-- For NOT LOGGED IN users -->
        <% String role = (String) session.getAttribute("role"); %>
        <% String username = (String) session.getAttribute("user"); %>
        <% if (role == null) { %>
        <a class="nav-btn" href="movies">Home</a>
        <a class="nav-btn" href="signup.jsp">Signup</a>
        <a class="nav-btn" href="login.jsp">Login</a>
        <% } else if ("admin".equals(role)) { %>
        <!-- For ADMIN -->
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
        <!-- For REGULAR USER -->
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

<%
    Movie movie = (Movie) request.getAttribute("movie");
%>

<div class="movieDetails">
    <h3><%= movie.getMovieTitle() %></h3>
    <p><%= movie.getDescription() %></p>

    <div class="movieDetails-details">
        <p><strong>Release date: </strong> <%= movie.getReleaseDate() %></p>
        <p><strong>Category: </strong> <%= movie.getCategory() %></p>

        <%
            List<ShowTime> showtimes = movie.getShowTimeList();

            if (showtimes == null || showtimes.isEmpty()) {
        %>
        <p>Coming Soon!.....</p>
        <%
        } else {
        %>
<%--        move the styles into stylesheet      --%>
        <p><strong>Showtimes:</strong></p>
        <div style="display: flex; flex-wrap: wrap; gap: 10px; margin-top: 10px;">
            <% for (ShowTime s : showtimes) { %>
            <div style="padding: 6px 12px; background-color: #f0f0f0; border-radius: 8px; font-size: 14px;">
                <%= s.getTime() %> • <%= s.getCinemaHall() %>
            </div>
            <% } %>
        </div>
        <%
            }
        %>
    </div>

    <div class="movieDetails-btns">
        <%
            int movieId = movie.getMovieId(); // For URL parameters
        %>

        <% if ("admin".equals(role)) { %>
        <!-- Admin controls -->
        <a class="movieDetails-btn-red" href="movies?action=edit&id=<%= movieId %>">Edit movie</a>
        <a class="movieDetails-btn-red" href="movies?action=delete&id=<%= movieId %>">Delete movie</a>
        <a class="movieDetails-btn-orange" href="showtime?action=add&id=<%= movieId %>">Add showtime</a>
        <a class="movieDetails-btn-orange" href="showtime?action=edit&id=<%= movieId %>">Edit showtime</a>

        <% } else if ("user".equals(role)) { %>
        <% if (showtimes != null && !showtimes.isEmpty()) { %>
        <!-- Regular user: Buy ticket and addreview -->
        <a class="movieDetails-btn-orange" href="ticket?action=getPage&movieId=<%= movieId %>">Buy Ticket</a>
        <a class="movieDetails-btn-red" href="review?action=getPage&movieId=<%= movieId %>">Add review</a>
        <% } %>

        <% } else if (role == null) { %>
        <% if (showtimes != null && !showtimes.isEmpty()) { %>
        <!-- Guest user: Redirect to signup -->
        <a class="movieDetails-btn-orange" href="signup.jsp?redirect=buyTicket&movieId=<%= movieId %>">Buy Ticket</a>
        <% } %>
        <% } %>

    </div>

    <div class="movieDetails-reviews">
        <%
            List<Review> reviews = movie.getReviewList();

            if (reviews.isEmpty()) {
        %>
        <p>No reviews yet.</p>
        <%
        } else {
            for (Review r : reviews) {
        %>
        <div class="movieDetails-review">
            <h4><%= r.getCustomerName() %></h4>
            <p>Stars: <%= r.getStarCount() %></p>
            <p>Review: <%= r.getReviewBody() %></p>
        </div>
        <%
                }
            }
        %>
    </div>
</div>


<script src="js/script.js" defer></script>
</body>
</html>