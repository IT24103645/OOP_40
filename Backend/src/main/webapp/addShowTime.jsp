<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.models.Movie" %>
<%@ page import="com.example.demo.models.CinemaHall" %>
<%
    Integer movieId = (Integer) request.getAttribute("movieId");
    if (movieId == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing movie ID");
        return;
    }
%>

<html>
<head>
    <title>Add Showtime</title>
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

<%
    CinemaHall[] halls = CinemaHall.values();
%>

<div class="signup">
    <h2 class="signup-title">Add Showtime</h2>

    <form method="post" action="${pageContext.request.contextPath}/showtime?id=<%= movieId %>&action=add">

        <div class="signup-inputs">
            <div class="signup-input">
                <p>Cinema Hall</p>
                <select name="cinemaHall" required>
                    <% for (CinemaHall hall : halls) { %>
                    <option value="<%= hall.name() %>"><%= hall.name() %></option>
                    <% } %>
                </select>
            </div>

            <div class="signup-input">
                <p>Time</p>
                <input type="time" name="time" required />
            </div>
        </div>

        <div class="signup-btns">
            <button class="signup-signup" type="submit">Add Showtime</button>
            <button class="signup-cancel" type="reset">Cancel</button>
        </div>
    </form>
</div>

</body>
</html>
