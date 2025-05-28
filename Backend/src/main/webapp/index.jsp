<!-- index.jsp -->
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.models.Movie" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Cinelux</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
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

<a class="movies-addNewButton" href="movies?action=sort" >Sort movies</a>

<div class="movies">
    <%
        List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
        if (movieList != null) {
            for (Movie m : movieList) {
    %>

    <div class="movieTile">
        <a href="movies?id=<%= m.getMovieId() %>">
            <div class="movieTile-info">
                <h4><%= m.getMovieTitle() %></h4>
                <p class="movietile-description"><%= m.getDescription() %></p>
                <p><%= m.getCategory() %></p>
                <p><%= m.getReleaseDate() %></p>
            </div>
        </a>
    </div>
    <%
            }
        }
    %>
</div>

<script src="js/script.js" defer></script>
</body>
</html>