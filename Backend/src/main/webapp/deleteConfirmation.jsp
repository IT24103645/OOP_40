<!-- deleteConfirmation.jsp -->
<html>
<head>
    <title>Are you sure?</title>
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
<div style="background:red; color:#ffffff; padding:1em;">
    Are you sure?
    <form method="post">
        <button name="confirm" value="yes">Yes</button>
        <button name="confirm" value="no">No, Cancel</button>
    </form>
</div>
</body>
</html>