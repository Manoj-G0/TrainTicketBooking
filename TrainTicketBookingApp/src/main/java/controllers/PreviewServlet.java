
package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class PreviewServlet
 */
@WebServlet("/PreviewServlet")
public class PreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String[]name =  request.getParameterValues("passangerName");
		String[] age = request.getParameterValues("passengerAge");
		String[] gender = request.getParameterValues("passangerGender");
		String[] berth = request.getParameterValues("passengerBerth");
		
		ArrayList<Passenger> pass = new ArrayList<>();
		for(int i = 0;i < age.length;i++)
		{
			Passenger p = new Passenger();
			p.setName(name[i]);
			p.setAge(age[i]);
			p.setGender(gender[i]);
			p.setBerth(berth[i]);
			pass.add(p);
		}
		FareCalculationService fc = FareCalculationService()
		fc.calculateFare(pass);
		request.setAttribute("passlist",pass);
		request.getRequestDispatcher("preview.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
