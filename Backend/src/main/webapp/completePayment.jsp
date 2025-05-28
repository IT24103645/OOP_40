<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete Payment</title>
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
    String ticketType = (String) session.getAttribute("ticketType");
    int amount = "adult".equalsIgnoreCase(ticketType) ? 1500 : 1300;
%>

<div class="signup">
    <h2 class="signup-title">Complete Payment</h2>

    <form method="get" action="${pageContext.request.contextPath}/payment">
        <input type="hidden" name="action" value="paymentComplete" />

        <div class="signup-input">
            <p><strong>Amount to pay:</strong> <%= amount %> LKR</p>
        </div>

        <!-- Optional: you can keep this section for display only (no input data submitted) -->
        <div class="signup-inputs">
            <div class="signup-input ">
                <p>Card Number</p>
                <input type="text" maxlength="19" placeholder="1234 5678 9012 3456"/>
            </div>

            <div class="signup-input">
                <p>Expiry Date</p>
                <div class="expiry-fields" style="display: flex; gap: 10px;">
                    <select style="flex: 1;" class="select-Input">
                        <option value="" disabled selected>Month</option>
                        <% for (int i = 1; i <= 12; i++) { %>
                        <option><%= i %></option>
                        <% } %>
                    </select>

                    <select style="flex: 1;" class="select-Input">
                        <option value="" disabled selected>Year</option>
                        <%
                            int currentYear = java.time.Year.now().getValue();
                            for (int i = 0; i <= 10; i++) {
                        %>
                        <option><%= currentYear + i %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <div class="signup-input">
                <p>CVC</p>
                <input type="password" maxlength="3" placeholder="***"/>
            </div>
        </div>

        <div class="signup-btns">
            <button class="signup-signup" type="submit">Pay Now</button>
            <button class="signup-cancel" type="reset">Cancel</button>
        </div>
    </form>
</div>

</body>
</html>
