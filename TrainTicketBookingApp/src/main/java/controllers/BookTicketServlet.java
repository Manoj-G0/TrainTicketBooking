package controllers;

import services.BookTicketService;
import models.Ticket;
import models.Passenger;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/book-ticket")
public class BookTicketServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Create Ticket object from request parameters
            Ticket ticket = createTicketFromRequest(request);
            
            // Process booking using existing service
            BookTicketService bookService = new BookTicketService();
            String pnr = bookService.bookTicket(ticket);
            
            // Set success attributes
            setSuccessAttributes(request, pnr, ticket);
            
            // Forward to success page
            request.getRequestDispatcher("BookingSuccess.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Booking failed: " + e.getMessage());
            request.getRequestDispatcher("BookingFailed.jsp").forward(request, response);
        }
    }
    
    private Ticket createTicketFromRequest(HttpServletRequest request) throws ParseException {
        Ticket ticket = new Ticket();
        
        // Set basic ticket info
        ticket.setTrainNumber(request.getParameter("trainNumber"));
        ticket.setSource(request.getParameter("source"));
        ticket.setDestination(request.getParameter("destination"));
        ticket.setTravelClass(request.getParameter("travelClass"));
        
        // Parse date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date journeyDate = sdf.parse(request.getParameter("journeyDate"));
        ticket.setJourneyDate(journeyDate);
        
        // Process passengers
        ticket.setPassengers(createPassengersFromRequest(request));
        
        return ticket;
    }
    
    private List<Passenger> createPassengersFromRequest(HttpServletRequest request) {
        String[] names = request.getParameterValues("passengerName");
        String[] ages = request.getParameterValues("passengerAge");
        String[] genders = request.getParameterValues("passengerGender");
        String[] berths = request.getParameterValues("berthPreference");
        
        List<Passenger> passengers = new ArrayList<>();
        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                Passenger passenger = new Passenger();
                passenger.setName(names[i]);
                passenger.setAge(Integer.parseInt(ages[i]));
                passenger.setGender(genders[i]);
                passenger.setBerthPreference(berths[i]);
                passengers.add(passenger);
            }
        }
        return passengers;
    }
    
    private void setSuccessAttributes(HttpServletRequest request, String pnr, Ticket ticket) {
        request.setAttribute("pnr", pnr);
        request.setAttribute("trainNumber", ticket.getTrainNumber());
        request.setAttribute("trainName", getTrainName(ticket.getTrainNumber()));
        request.setAttribute("source", ticket.getSource());
        request.setAttribute("destination", ticket.getDestination());
        request.setAttribute("journeyDate", ticket.getJourneyDate());
        request.setAttribute("travelClass", ticket.getTravelClass());
        request.setAttribute("passengers", ticket.getPassengers());
    }
    
    private String getTrainName(String trainNumber) {
        switch(trainNumber) {
            case "12951": return "Rajdhani Express";
            case "12009": return "Shatabdi Express";
            case "12213": return "Duronto Express";
            default: return "Unknown Train";
        }
    }
}