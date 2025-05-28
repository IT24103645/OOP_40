<!-- signup.jsp -->
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="signup">
    <h2 class="signup-title">Sign-up</h2>
    <form method="post" action="<%= request.getContextPath() %>/customer">
        <section class="signup-inputs">
            <div class="signup-input">
                <p>Username</p>
                <input name="username" maxlength="20" />
            </div>
            <div class="signup-input">
                <p>Password</p>
                <input name="password" type="password" maxlength="10" />
            </div>
        </section>
        <section class="signup-btns">
            <button class="signup-signup" type="submit">Sign-in</button>
            <button class="signup-cancel" type="reset">Cancel</button>
        </section>
    </form>
</div>
<script src="js/script.js" defer></script>
</body>
</html>