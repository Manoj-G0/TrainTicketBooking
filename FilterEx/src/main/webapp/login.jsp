<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<h2>Login</h2>
	User name : <input type="text" id="user"><br>
	Password : <input type="password" id="pwd"><br> 
	<button type="button" onclick="auth()">Submit</button><br><br>
	
	<a href="DashServlet">Dashboard</a> |
	<a href="home.jsp">Home</a> |
	<a href="sampleServlet">Sample</a>

	<script>
		function auth() {
			const xhr = new XMLHttpRequest();
			let user = document.getElementById("user").value;
			let password = document.getElementById("pwd").value;

			xhr.open("GET", "http://localhost:8080/FilterEx/Auth?user=" + user + "&pwd=" + password, true);
			
			xhr.onload = function() {
				if (xhr.status === 200) {
					window.location.href = "home.jsp";
				} else {
					alert("Login failed");
				}
			};
			
			xhr.send();
		}
	</script>
</body>
</html>
