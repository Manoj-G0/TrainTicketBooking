package controllers;

import services.TicketAvailabilityCheckService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/train-list")
public class TrainListServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int sourceId = Integer.parseInt(request.getParameter("source"));
        int destinationId = Integer.parseInt(request.getParameter("destination"));
        
        TicketAvailabilityCheckService availabilityService = new TicketAvailabilityCheckService();
        String trainOptions = availabilityService.getTrainOptions(sourceId, destinationId);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(trainOptions);
    }
}