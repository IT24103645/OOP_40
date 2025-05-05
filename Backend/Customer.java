class Customer {
    private static final int MAX_TICKETS = 10;
    private static final int MAX_REVIEWS = 10;
    private int customerId;
    private String customerName;
    private String password;
    private Ticket[] ticketsBoughtArray = new Ticket[MAX_TICKETS];
    private Review[] reviewAddedArray = new Review[MAX_REVIEWS];
    private int ticketCount = 0;
    private int reviewCount = 0;

    public Customer(int customerId, String customerName, String password) {
        if (customerName == null || customerName.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Password too short.");
        this.customerId = customerId;
        this.customerName = customerName;
        this.password = password;
    }

    public Review[] getReviews() {
        return reviewAddedArray;
    }

    public void addReview(Review r) {
        for (int i = 0; i < reviewCount; i++) {
            if (reviewAddedArray[i].getReviewId() == r.getReviewId()) {
                reviewAddedArray[i] = r;
                return;
            }
        }
        if (reviewCount >= MAX_REVIEWS) throw new IllegalStateException("Review array full.");
        reviewAddedArray[reviewCount++] = r;
    }

    public Ticket[] getTickets() {
        return ticketsBoughtArray;
    }

    public void addTicket(Ticket t) {
        if (ticketCount >= MAX_TICKETS) throw new IllegalStateException("Ticket array full.");
        ticketsBoughtArray[ticketCount++] = t;
    }

    public void deleteReview(int reviewId) {
        for (int i = 0; i < reviewCount; i++) {
            if (reviewAddedArray[i].getReviewId() == reviewId) {
                reviewAddedArray[i] = null;
                return;
            }
        }
        throw new IllegalArgumentException("Review not found.");
    }

    public int getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getPassword() { return password; }

    public void setCustomerName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        this.customerName = name;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Password too short.");
        this.password = password;
    }
};