package services;

import dao.BookTicketDAO;
import models.Ticket;

public class BookTicketService {
    public String bookTicket(Ticket ticket) {
        BookTicketDAO bookTicketDAO = new BookTicketDAO();
        return bookTicketDAO.bookTicket(ticket);
    }
}