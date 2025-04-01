package controllers;

import dao.GetStationsDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Station;
import java.io.IOException;
import java.util.List;

@WebServlet("/booking-input")
public class BookingInputServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Station> stations = GetStationsDAO.getAllStations();
        
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("/TicketBooking.jsp").forward(request, response);
    }
}