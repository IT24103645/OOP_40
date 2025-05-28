package com.example.demo.utils;

import com.example.demo.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.*;

//omaghaaat this annoying thing is needed all the time for error checking
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStore {
    private static List<Movie> movies = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static Map<String, Integer> idTracker = new HashMap<>();

    private static final String MOVIE_FILE = "data/movies.json";
    private static final String CUSTOMER_FILE = "data/customers.json";
    private static final String PAYMENT_FILE = "data/payments.json";
    private static final String ID_FILE = "data/idtracker.txt";

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        ensureDataFolderExists();
        initializeStorage();
        loadAll();
    }

    private static void ensureDataFolderExists() {
        File dataDir = new File("data");
        System.out.println("🧭 Data folder absolute path: " + dataDir.getAbsolutePath());
        if (!dataDir.exists()) {
            boolean created = dataDir.mkdirs();
            if (!created) {
                System.err.println("Failed to create data directory");
            }
        }
    }

    private static void initializeStorage() {
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdirs();

        createFileIfMissing(MOVIE_FILE, new ArrayList<Movie>());
        createFileIfMissing(CUSTOMER_FILE, new ArrayList<Customer>());
        createFileIfMissing(PAYMENT_FILE, new ArrayList<Payment>());
        createIdFileIfMissing();
    }

    private static void createFileIfMissing(String path, List<?> emptyList) {
        File file = new File(path);
        if (!file.exists()) {
            writeJsonList(path, emptyList);
        }
    }

    private static void createIdFileIfMissing() {
        File file = new File(ID_FILE);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("movieId:1000\n");
                writer.write("reviewId:2000\n");
                writer.write("ticketId:3000\n");
                writer.write("paymentId:4000\n");
                writer.write("showTimeId:5000\n");
                writer.write("customerId:6000\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadAll() {
        movies = readJsonList(MOVIE_FILE, new TypeReference<List<Movie>>() {});
        customers = readJsonList(CUSTOMER_FILE, new TypeReference<List<Customer>>() {});
        payments = readJsonList(PAYMENT_FILE, new TypeReference<List<Payment>>() {});
        idTracker = readIdTracker();
        if (idTracker.isEmpty()) {
            idTracker.put("movieId", 1000);
            idTracker.put("reviewId", 2000);
            idTracker.put("ticketId", 3000);
            idTracker.put("paymentId", 4000);
            idTracker.put("showTimeId", 5000);
            idTracker.put("customerId", 6000);
        }
    }

    private static <T> List<T> readJsonList(String path, TypeReference<List<T>> typeRef) {
        try {
            File file = new File(path);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    private static void writeJsonList(String path, List<?> list) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Map<String, Integer> readIdTracker() {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ID_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) map.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException ignored) {}
        return map;
    }

    private static void writeIdTracker() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE))) {
            for (Map.Entry<String, Integer> entry : idTracker.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveAll() {
        writeJsonList(MOVIE_FILE, movies);
        writeJsonList(CUSTOMER_FILE, customers);
        writeJsonList(PAYMENT_FILE, payments);
        writeIdTracker();
    }

    public static Movie getMovieById(int id) {
        return movies.stream().filter(m -> m.getMovieId() == id).findFirst().orElse(null);
    }


    public static List<Movie> getAllMovies() {
        return movies;
    }

    public static boolean addMovie(String title, String desc, String category, String releaseDate) {
        try {
            int id = idTracker.get("movieId") + 1;
            idTracker.put("movieId", id);
            Movie m = new Movie(id, title, desc, category, releaseDate);
            movies.add(m);
            saveAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean editMovie(int id, String title, String desc, String category, String releaseDate) {
        Movie m = movies.stream()
                .filter(movie -> movie.getMovieId() == id)
                .findFirst()
                .orElse(null);
        if (m != null) {
            m.setMovieTitle(title);
            m.setDescription(desc);
            m.setCategory(MovieCategory.valueOf(category.toUpperCase()));
            m.setReleaseDate(releaseDate);
            saveAll();
            return true;
        }
        return false;
    }


    public static boolean deleteMovie(int id) {
        boolean result = movies.removeIf(m -> m.getMovieId() == id);
        if (result) saveAll();
        return result;
    }

    public static boolean customerExists(String username) {
        return customers.stream().anyMatch(c -> c.getCustomerName().equals(username));
    }

    public static boolean createCustomer(String username, String password) {
        if (customerExists(username)) return false;
        int id = idTracker.get("customerId") + 1;
        idTracker.put("customerId", id);
        Customer c = new Customer(id, username, password);
        customers.add(c);
        saveAll();
        return true;
    }

    public static boolean validateCustomer(String username, String password) {
        return customers.stream().anyMatch(c -> c.getCustomerName().equals(username) && c.getPassword().equals(password));
    }

    public static Customer getCustomerByCredentials(String username, String password) {
        return customers.stream()
                .filter(c -> c.getCustomerName().equals(username) && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Returns the customer object by customerId
    public static Customer getCustomerById(int customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }


    public static boolean addShowTimeToMovie(int movieId, HttpServletRequest req) {
        try {
            Movie m = getMovieById(movieId);
            int showTimeId = idTracker.get("showTimeId") + 1;
            idTracker.put("showTimeId", showTimeId);
            CinemaHall hall = CinemaHall.valueOf(req.getParameter("cinemaHall"));
            String timeString = req.getParameter("time"); // e.g. "14:30"
            ShowTime st = new ShowTime(showTimeId, hall, timeString, 50);
            m.addShowTime(st);
            saveAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean handleTicketPurchase(HttpServletRequest req) {
        try {
            int movieId = Integer.parseInt(req.getParameter("movieId"));
            String username = (String) req.getSession().getAttribute("user");
            Movie movie = getMovieById(movieId);
            Customer user = customers.stream().filter(c -> c.getCustomerName().equals(username)).findFirst().orElse(null);
            ShowTime st = movie.getShowTimeList().get(0);
            TicketType type = TicketType.valueOf(req.getParameter("ticketType"));

            int ticketId = idTracker.get("ticketId") + 1;
            idTracker.put("ticketId", ticketId);
            int paymentId = idTracker.get("paymentId") + 1;
            idTracker.put("paymentId", paymentId);

            int seat = Integer.parseInt(req.getParameter("seat"));
            st.bookSeat(seat);

            String dateIssued = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Ticket ticket = new Ticket(ticketId, movieId, movie.getMovieTitle(), st.getCinemaHall(), st.getTime(), seat, paymentId, type, dateIssued);
            Payment payment = new Payment(paymentId, (type == TicketType.ADULT ? 1500 : 1300));
            st.addTicket(ticket);
            user.addTicket(ticket);
            payments.add(payment);
            saveAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Checks if a ticket with ticketId exists for a customer
    public static boolean ticketExistsForCustomer(int customerId, int ticketId) {
        Customer c = getCustomerById(customerId);
        if (c == null) return false;
        return c.getTickets().stream().anyMatch(t -> t.getTicketId() == ticketId);
    }

    // Update a ticket's status (if it exists)
    public static boolean updateTicketStatus(int customerId, int ticketId, TicketStatus status) {
        Customer c = getCustomerById(customerId);
        if (c == null) return false;

        for (Ticket t : c.getTickets()) {
            if (t.getTicketId() == ticketId) {
                t.setStatus(status);
                saveAll();
                return true;
            }
        }
        return false;
    }

    public static boolean addReview(HttpServletRequest req) {
        try {
            int reviewId = idTracker.get("reviewId") + 1;
            idTracker.put("reviewId", reviewId);
            String username = (String) req.getSession().getAttribute("user");
            String movieTitle = req.getParameter("movieTitle");
            int stars = Integer.parseInt(req.getParameter("starCount"));
            String body = req.getParameter("body");

            Review r = new Review(reviewId, username, movieTitle, stars, body);
            Movie m = movies.stream().filter(movie -> movie.getMovieTitle().equals(movieTitle)).findFirst().orElse(null);
            Customer c = customers.stream().filter(u -> u.getCustomerName().equals(username)).findFirst().orElse(null);

            if (m != null && c != null) {
                m.addReview(r);
                c.addReview(r);
                saveAll();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean deleteReview(int reviewId) {
        for (Customer c : customers) c.deleteReview(reviewId);
        for (Movie m : movies) m.deleteReview(reviewId);
        saveAll();
        return true;
    }

    public static String convertMovieToJson(Movie movie) {
        return "{\"title\":\"" + movie.getMovieTitle() + "\"}";
    }

    public static String convertMovieListToJson(List<Movie> movieList) {
        StringBuilder sb = new StringBuilder("[");
        for (Movie m : movieList) {
            sb.append(convertMovieToJson(m)).append(",");
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public static String getShowtimesJson(int movieId) {
        Movie m = getMovieById(movieId);
        return m == null ? "[]" : "[]"; // placeholder
    }

    public static String getTicketsForCustomer(String username) {
        Customer c = customers.stream().filter(u -> u.getCustomerName().equals(username)).findFirst().orElse(null);
        return (c != null) ? "[]" : "[]"; // placeholder
    }

    public static String getMovieReviewsJson(int movieId) {
        return "[]"; // placeholder
    }

    public static String getUserReviewsJson(String username) {
        return "[]"; // placeholder
    }

    //adds new payment to file
    public static int createPaymentForTicketType(TicketType type) {
        try {
            // Get and increment payment ID
            int paymentId = idTracker.get("paymentId") + 1;
            idTracker.put("paymentId", paymentId);

            // Determine amount based on ticket type
            double amount = (type == TicketType.ADULT) ? 1500 : 1300;

            // Create and save payment
            Payment payment = new Payment(paymentId, amount);
            payments.add(payment);
            saveAll();

            return paymentId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //adds new ticket
    /**
     * Creates a new ticket and adds it to the specified customer's ticket list
     * @param movie The Movie object for the ticket
     * @param showTime The ShowTime object for the ticket
     * @param type The type of ticket (ADULT or CHILD)
     * @param seatNumber The seat number for the ticket
     * @param paymentId The payment ID associated with the ticket
     * @param customerId The ID of the customer who owns the ticket
     * @return The ID of the created ticket, or -1 if creation failed
     */
    public static int createTicket(Movie movie, ShowTime showTime, TicketType type,
                                   int seatNumber, int paymentId, int customerId) {
        try {
            // Validate inputs
            if (movie == null || showTime == null || type == null || seatNumber <= 0 || paymentId <= 0 || customerId <= 0) {
                throw new IllegalArgumentException("Invalid parameters for ticket creation");
            }

            // Get and increment ticket ID
            int ticketId = idTracker.get("ticketId") + 1;
            idTracker.put("ticketId", ticketId);

            // Get current date/time for ticket issuance
            String dateIssued = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Create new ticket
            Ticket ticket = new Ticket(
                    ticketId,
                    movie.getMovieId(),
                    movie.getMovieTitle(),
                    showTime.getCinemaHall(),
                    showTime.getTime(),
                    seatNumber,
                    paymentId,
                    type,
                    dateIssued
            );

//            // Book the seat in the showtime
//            showTime.bookSeat(seatNumber);

            // Add ticket to showtime's sold tickets list
            showTime.addTicket(ticket);

            // Find customer and add ticket to their list
            Customer customer = getCustomerById(customerId);
            if (customer == null) {
                throw new IllegalArgumentException("Customer not found with ID: " + customerId);
            }
            customer.addTicket(ticket);

            // Save all changes
            saveAll();

            return ticketId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean createReview(int movieId, Customer customer, String reviewBody, int starCount) {
        if (customer == null || reviewBody == null || reviewBody.isEmpty() || starCount < 1 || starCount > 5) {
            throw new IllegalArgumentException("Invalid review data");
        }

        Movie movie = getMovieById(movieId);
        if (movie == null) {
            return false;
        }

        int reviewId = idTracker.get("reviewId") + 1;
        idTracker.put("reviewId", reviewId);

        Review review = new Review(
                reviewId,
                customer.getCustomerName(),
                movie.getMovieTitle(),
                starCount,
                reviewBody
        );

        customer.addReview(review);
        movie.addReview(review);
        saveAll();
        return true;
    }

}
