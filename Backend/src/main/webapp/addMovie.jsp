<%@ page import="java.time.LocalDate" %>
<!-- addMovie.jsp -->
<html>
<head>
    <title>Add Movie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
    String today = LocalDate.now().toString();
%>

<div class="signup">
    <h2 class="signup-title">Add Movie</h2>
    <form method="post" action="${pageContext.request.contextPath}/movies">
        <input type="hidden" name="action" value="add" />

        <div class="signup-inputs">
            <div class="signup-input">
                <p>Movie Title</p>
                <input name="title" maxlength="10" />
            </div>

            <div class="signup-input">
                <p>Description</p>
                <textarea name="description" maxlength="200"></textarea>
            </div>

            <div class="signup-input">
                <p>Release Date</p>
                <input type="date" name="releaseDate" max="<%= today %>"/>
            </div>

            <div class="signup-input">
                <p>Category</p>
                <select name="category" class="select-Input">
                    <option>HORROR</option>
                    <option>ROMANCE</option>
                    <option>COMEDY</option>
                    <option>ACTION</option>
                    <option>FAMILY</option>
                </select>
            </div>
        </div>
        <div class="signup-btns">
            <button class="signup-signup" type="submit">Add Movie</button>
            <button class="signup-cancel" type="reset">Cancel</button>
        </div>
    </form>
</div>


</body>
</html>