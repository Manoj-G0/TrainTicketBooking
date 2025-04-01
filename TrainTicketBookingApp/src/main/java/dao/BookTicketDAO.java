package dao;

import models.Passenger;
import models.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookTicketDAO {
    // Mock data storage
    private static List<Ticket> mockTickets = new ArrayList<>();
    private static List<Passenger> mockPassengers = new ArrayList<>();
    private static int nextTicketId = 1;
    
    public String bookTicket(Ticket ticket) {
        // Generate PNR
        String pnr = generatePNR();
        
        try {
            // Mock ticket insertion
            int ticketId = nextTicketId++;
            
            // Set generated PNR to ticket
            ticket.setPnr(pnr);
            ticket.setStatus("CONFIRMED");
            
            // Mock: Store ticket
            mockTickets.add(ticket);
            
            // Mock: Store passengers with ticket reference
            for (Passenger passenger : ticket.getPassengers()) {
                passenger.setTicketId(ticketId);
                passenger.setBerthAllocated(allocateBerth());
                mockPassengers.add(passenger);
            }
            
            return pnr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /* Commented out database code
    private int insertTicket(Connection conn, Ticket ticket, String pnr) throws SQLException {
        String sql = "INSERT INTO tickets (pnr, journey_date, train_number, " +
                    "source, destination, travel_class, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, pnr);
            pstmt.setDate(2, new java.sql.Date(ticket.getJourneyDate().getTime()));
            pstmt.setString(3, ticket.getTrainNumber());
            pstmt.setString(4, ticket.getSource());
            pstmt.setString(5, ticket.getDestination());
            pstmt.setString(6, ticket.getTravelClass());
            pstmt.setString(7, "CONFIRMED");
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to insert ticket, no ID obtained");
    }
    
    private void insertPassenger(Connection conn, Passenger passenger, int ticketId) throws SQLException {
        String sql = "INSERT INTO passengers (name, age, gender, " +
                    "berth_preference, berth_allocated, ticket_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, passenger.getName());
            pstmt.setInt(2, passenger.getAge());
            pstmt.setString(3, passenger.getGender());
            pstmt.setString(4, passenger.getBerthPreference());
            pstmt.setString(5, allocateBerth());
            pstmt.setInt(6, ticketId);
            
            pstmt.executeUpdate();
        }
    }
    */
    
    private String generatePNR() {
        return "PNR" + String.format("%06d", new Random().nextInt(999999));
    }
    
    private String allocateBerth() {
        String[] berths = {"LB", "MB", "UB", "SL", "SU"};
        return berths[new Random().nextInt(berths.length)];
    }
    
    // Helper methods for mock data access (optional)
    public static List<Ticket> getAllMockTickets() {
        return new ArrayList<>(mockTickets);
    }
    
    public static List<Passenger> getPassengersForTicket(int ticketId) {
        List<Passenger> result = new ArrayList<>();
        for (Passenger p : mockPassengers) {
            if (p.getTicketId() == ticketId) {
                result.add(p);
            }
        }
        return result;
    }
}