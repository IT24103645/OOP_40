<%@ page import="com.example.demo.models.Customer" %>
<%@ page import="com.example.demo.models.Review" %>
<%@ page import="java.util.List" %>
<!-- myReviews.jsp -->

<html>
<head>
    <title>My reviews</title>
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
    Customer customer = (Customer) session.getAttribute("customer");
    List<Review> reviews = (customer != null) ? customer.getReviews() : new java.util.ArrayList<>();
%>
<div class="myReviews">
    <h2 class="myReviews-title">My Reviews</h2>
    <section class="myReviews-reviews">
        <% for (Review review : reviews) { %>
        <div class="review-card">
            <div class="review-left">
                <div class="review-img" style="background-image: url('https://via.placeholder.com/150');"></div>
            </div>
            <div class="review-right">
                <div>
                    <div class="review-header">
                        <h3 class="review-title"><%= review.getMovieTitle() %></h3>
                        <span class="review-stars"><%= review.getStarCount() %> stars</span>
                    </div>
                    <p class="review-body"><%= review.getReviewBody() %></p>
                </div>
                <div class="review-actions">
                    <form method="post" action="editReview.jsp">
                        <input type="hidden" name="reviewId" value="<%= review.getReviewId() %>">
                        <button class="btn edit-btn" type="submit">Edit</button>
                    </form>
                    <form method="post" action="deleteReview">
                        <input type="hidden" name="reviewId" value="<%= review.getReviewId() %>">
                        <button class="btn delete-btn" type="submit">Delete</button>
                    </form>
                </div>
            </div>
        </div>
        <% } %>
    </section>
</div>



</body>
</html>