package controllers;

import models.Ticket;
import models.Passenger;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BookTicketDAO;

@WebServlet("/book-ticket")
public class TicketBookingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Ticket ticket = new Ticket();
            
            // Set ticket details
            ticket.setTrainNumber(request.getParameter("trainNumber"));
            ticket.setSource(request.getParameter("source"));
            ticket.setDestination(request.getParameter("destination"));
            ticket.setTravelClass(request.getParameter("travelClass"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date journeyDate = sdf.parse(request.getParameter("journeyDate"));
            ticket.setJourneyDate(journeyDate);
            
            // Process passengers
            String[] names = request.getParameterValues("passengerName");
            String[] ages = request.getParameterValues("passengerAge");
            String[] genders = request.getParameterValues("passengerGender");
            String[] berths = request.getParameterValues("berthPreference");
            
            List<Passenger> passengers = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                Passenger passenger = new Passenger();
                passenger.setName(names[i]);
                passenger.setAge(Integer.parseInt(ages[i]));
                passenger.setGender(genders[i]);
                passenger.setBerthPreference(berths[i]);
                passengers.add(passenger);
            }
            ticket.setPassengers(passengers);
            
            // Book ticket
            BookTicketDAO bookService = new BookTicketDAO();
            String pnr = bookService.bookTicket(ticket);
            
            request.setAttribute("pnr", pnr);
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/BookingSuccess.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/BookingFailure.jsp").forward(request, response);
        }
    }
}