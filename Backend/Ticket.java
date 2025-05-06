import java.text.SimpleDateFormat;
import java.util.Date;

class Ticket {
    private int ticketId;
    private String movieTitle;
    private String cinemaHall;
    private String time;
    private int seatNumber;
    private String dateIssued;
    private int paymentId;
    private boolean isValid;
    private TicketType type;

    public Ticket(int ticketId, String movieTitle, String cinemaHall, String time, int seatNumber, int paymentId, TicketType type) {
        this.ticketId = ticketId;
        this.movieTitle = movieTitle;
        this.cinemaHall = cinemaHall;
        this.time = time;
        this.seatNumber = seatNumber;
        this.paymentId = paymentId;
        this.isValid = true;
        this.type = type;
        this.dateIssued = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public int getTicketId() { return ticketId; }
    public String getMovieTitle() { return movieTitle; }
    public String getCinemaHall() { return cinemaHall; }
    public String getTime() { return time; }
    public String getDateIssued() { return dateIssued; }
    public int getPaymentId() { return paymentId; }
    public TicketType getType() {return type;}

}

enum TicketType {
    ADULT, CHILD
}