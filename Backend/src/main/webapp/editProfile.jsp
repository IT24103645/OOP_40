<!-- editProfile.jsp -->
<html>
<head>
    <title>Edit Profile</title>
</head>
<body>
<div class="navbar">
    <div class="logo">Cinelux</div>
    <div class="nav-buttons">
        <% if (session.getAttribute("user") != null) { %>
        <a href="index.jsp">Home</a>
        <a href="myProfile.jsp">My Profile</a>
        <a href="logout.jsp">Logout</a>
        <% } else { %>
        <a href="index.jsp">Home</a>
        <a href="signup.jsp">Signup</a>
        <a href="login.jsp">Login</a>
        <% } %>
    </div>
</div>
<form method="post" action="/customer">
    New Username: <input name="username" maxlength="10"><br>
    New Password: <input name="password1" type="password" maxlength="20"><br>
    Confirm Password: <input name="password2" type="password" maxlength="20"><br>
    <div id="error"></div>
    <button type="submit">Confirm</button>
    <button type="reset">Cancel</button>
</form>
</body>
</html>