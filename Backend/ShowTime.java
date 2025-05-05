class ShowTime {
    private static final int MAX_TICKETS = 10;
    private int showTimeId;
    private String cinemaHall;
    private String time;
    private boolean[] seatsArray;
    private Ticket[] ticketsSoldArray = new Ticket[MAX_TICKETS];
    private int ticketCount = 0;
    private double showTimeRevenue = 0;
    private boolean isPublished = true;

    public ShowTime(int showTimeId, String cinemaHall, String time, int seatsArrayLength) {
        this.showTimeId = showTimeId;
        this.cinemaHall = cinemaHall;
        this.time = time;
        this.seatsArray = new boolean[seatsArrayLength];
    }

    public void bookSeat(int position) {
        if (!seatsArray[position - 1]) {
            seatsArray[position - 1] = true;
        } else {
            throw new IllegalStateException("Seat already booked.");
        }
    }

    public void addTicket(Ticket t) {
        if (ticketCount >= MAX_TICKETS) throw new IllegalStateException("Ticket array full.");
        ticketsSoldArray[ticketCount++] = t;
    }

    public double calcShowtimeRevenue() {
        double total = 0;
        for (int i = 0; i < ticketCount; i++) {
            total += (ticketsSoldArray[i].getType() == TicketType.ADULT) ? 1500 : 1300;
        }
        showTimeRevenue = total;
        return total;
    }

    public void unpublishShowtime() {
        isPublished = false;
    }

    public boolean[] getSeatsArray() {
        return seatsArray;
    }

    public int getShowTimeId() { return showTimeId; }
    public String getCinemaHall() { return cinemaHall; }
    public String getTime() { return time; }
    public boolean getIsPublished() { return isPublished; }
    public double getShowTimeRevenue() { return showTimeRevenue; }

    public void setCinemaHall(String cinemaHall) {
        if (!isPublished) return;
        this.cinemaHall = cinemaHall;
    }

    public void setTime(String time) {
        if (!isPublished) return;
        this.time = time;
    }
}