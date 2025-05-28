<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.demo.models.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Movie movie = (Movie) request.getAttribute("movie");
    String today = LocalDate.now().toString();
%>
<html>
<head>
    <title>Edit Movie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="navbar">
    <div class="logo">Cinelux</div>
    <div class="nav-buttons">
        <%
            String role = (String) session.getAttribute("role");
            String username = (String) session.getAttribute("user");
        %>
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

<div class="signup">
    <h2 class="signup-title">Edit Movie</h2>
    <form method="post" action="${pageContext.request.contextPath}/movies?action=edit&id=<%= movie.getMovieId() %>">

        <div class="signup-inputs">
            <div class="signup-input">
                <p>Movie Title</p>
                <input name="title" maxlength="10" value="<%= movie.getMovieTitle() %>" />
            </div>

            <div class="signup-input">
                <p>Description</p>
                <textarea name="description" maxlength="200"><%= movie.getDescription() %></textarea>
            </div>

            <div class="signup-input">
                <p>Release Date</p>
                <input type="date" name="releaseDate" max="<%= today %>" value="<%= movie.getReleaseDate() %>" />
            </div>

            <div class="signup-input">
                <p>Category</p>
                <select name="category" class="select-Input">
                    <option <%= movie.getCategory().name().equals("HORROR") ? "selected" : "" %>>HORROR</option>
                    <option <%= movie.getCategory().name().equals("ROMANCE") ? "selected" : "" %>>ROMANCE</option>
                    <option <%= movie.getCategory().name().equals("COMEDY") ? "selected" : "" %>>COMEDY</option>
                    <option <%= movie.getCategory().name().equals("ACTION") ? "selected" : "" %>>ACTION</option>
                    <option <%= movie.getCategory().name().equals("FAMILY") ? "selected" : "" %>>FAMILY</option>
                </select>
            </div>
        </div>
        <div class="signup-btns">
            <button class="signup-signup" type="submit">Confirm</button>
            <button class="signup-cancel" type="reset">Cancel</button>
        </div>
    </form>
</div>

</body>
</html>
