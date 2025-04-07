import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/DashServlet", "/sampleServlet"})
public class ProtectedFIlter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		Object user = (session != null) ? session.getAttribute("user") : null;
		System.out.println("Filter triggered. Session: " + session + ", User attribute: " + user);

		if (user != null && user.equals("yes")) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("login.jsp");
		}
	}
}
