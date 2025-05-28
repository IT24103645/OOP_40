<!-- editShowTime.jsp -->
<html>
<head>
    <title>Edit ShowTime</title>
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
<form method="post" action="/showtime">
    Cinema Hall: <select name="hall"> <option>C1</option><option>C2</option><option>C3</option> </select><br>
    Time: <select name="time"> <!-- 5-minute intervals --> </select><br>
    <button type="submit">Confirm</button>
    <button type="reset">Cancel</button>
    <button formaction="deleteConfirmation.jsp">Delete Showtime</button>
</form>
</body>
</html>