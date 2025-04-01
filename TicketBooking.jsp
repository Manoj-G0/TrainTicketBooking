<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team8-Booking App</title>
</head>
<body>
	 From:<select name="from" id = "from" onchange = "updateTrains()">
	 
            <%
                // Retrieve the ArrayList from the request scope
                ArrayList<String> trainList = (ArrayList<String>) request.getAttribute("stlist");
                if (trainList != null) {
                    for (String train : trainList) {
                    	System.out.println("jsp-------" + train);
            %>
                        <option value="<%= train %>"><%= train %></option>
            <%
                    }
                } 
            %>
                
        </select>
             To:<select name="to" id = "to" onchange = "updateTrains()">
            <%
                // Retrieve the ArrayList from the request scope
                //ArrayList<String> trainList = (ArrayList<String>) request.getAttribute("stlist");
                if (trainList != null) {
                    for (String train : trainList) {
                    	System.out.println("jsp-------" + train);
            %>
                        <option value="<%= train %>"><%= train %></option>
            <%
                    }
                }
            %>
                
        </select><br><br>
        
        Trains : 
        <select id ="train">
        	
        </select>
        
        <script>
        	function updateTrains()
        	{
        		let from = document.getElementById("from").value;
        		let to = document.getElementById("to").value;
        		
        		const xhr = new XMLHttpRequest();
        		xhr.open("POST","http://localhost:8080/Team8BookingApp/TrainListServlet",open);
        		xhr.onreadystatechange = funtion (){
        			if(xhr.readyState == 4 && xhr.status == 200)
        			{
        				let select = document.getElementById("train");
        				
        				select.innerHTML = "";
        				list.forEach(train => {
        					let option = document.createElement("option");
        					option.value = train;
        					option.textContent = triain;
        					select.appendChild(option);
        				});
        			}
        			
        		}
        		
        		
        	}
        	
        </script>
</body>
</html>