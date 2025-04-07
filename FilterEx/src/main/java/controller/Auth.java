package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		if (user != null && pwd != null && user.equals("karthik") && pwd.equals("admin")) {
			session.setAttribute("user", "yes");
			// This response will be caught by JavaScript and redirected
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			session.setAttribute("user", "no");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
