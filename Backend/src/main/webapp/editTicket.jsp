<!-- editTicket.jsp -->
<html>
<head>
    <title>Edit Ticket</title>
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
<form method="post" action="/ticket">
    <p><strong>Movie: Jumanji</strong></p>
    Time: <select name="time"></select><br>
    Cinema Hall: <select name="hall"></select>
    Seat Number: <select name="seat"></select><br>
    Ticket Type: <select name="ticketType"><option>ADULT</option><option>CHILD</option></select><br>
    <button type="submit">Confirm</button>
    <button type="reset">Cancel</button>
</form>
</body>
</html>