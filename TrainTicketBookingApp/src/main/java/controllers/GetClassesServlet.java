import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.GetClassesDAO;
import models.TrainClass;

public class GetClassesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try {
            int trainNo = Integer.parseInt(request.getParameter("trainno"));
            List<TrainClass> classes = GetClassesDAO.getClasses(trainNo);
            
            JSONArray arr = new JSONArray(classes);
            out.print(arr.toString());
            out.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
            out.print("Error fetching classes");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
