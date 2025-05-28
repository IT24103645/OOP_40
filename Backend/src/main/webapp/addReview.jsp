<%@ page import="com.example.demo.models.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Review</title>
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

<% Movie movie = (Movie) request.getAttribute("reviewMovie"); %>

<div class="signup">
    <h2 class="signup-title">Add Review for <%= movie != null ? movie.getMovieTitle() : "" %></h2>
    <form method="post" action="${pageContext.request.contextPath}/review">
        <input type="hidden" name="action" value="add" />
        <input type="hidden" name="movieId" value="<%= movie != null ? movie.getMovieId() : "" %>" />

        <div class="signup-inputs">
            <div class="signup-input">
                <p>Your Review</p>
                <textarea name="reviewBody" maxlength="100" required></textarea>
            </div>

            <div class="signup-input">
                <p>Star Rating</p>
                <select name="starCount" required class="select-Input">
                    <option value="1">1 Star</option>
                    <option value="2">2 Stars</option>
                    <option value="3">3 Stars</option>
                    <option value="4">4 Stars</option>
                    <option value="5">5 Stars</option>
                </select>
            </div>
        </div>
        <div class="signup-btns">
            <button class="signup-signup" type="submit">Add Review</button>
            <button class="signup-cancel" type="button" onclick="window.history.back()">Cancel</button>
        </div>
    </form>
</div>
</body>
</html>