import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GetStationsDao 
{
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres"; 
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Karthick_0";
    static ArrayList<String> stations = new ArrayList<>();
   public static ArrayList<String> getStations()
   {
	   try
	    {
	        // Load PostgreSQL or MySQL JDBC driver
	        Class.forName("org.postgresql.Driver"); // Use "com.mysql.cj.jdbc.Driver" for MySQL

	        // Establish connection
	        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        System.out.println("Connected to database successfully!");
	        
	        String query = "select distinct(station_name) from train_schedule";
	        try (Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            System.out.println("\n=== Train Details ===");
	            while (rs.next()) {
	                System.out.println("Name: " + rs.getString(1) );
	                stations.add(rs.getString(1));
	            }
	        }
	        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	   return stations;
   }
    // Fetch and display data
    
}

