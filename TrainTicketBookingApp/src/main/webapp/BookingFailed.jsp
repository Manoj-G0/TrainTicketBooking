<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Failed</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; }
        .error-box { border: 1px solid #f44336; padding: 20px; margin: 20px 0; }
        .btn { padding: 10px 15px; background: #4CAF50; color: white; text-decoration: none; }
    </style>
</head>
<body>
    <h1>Booking Failed</h1>
    
    <div class="error-box">
        <p>${errorMessage}</p>
        <p>Please try again or contact customer support.</p>
    </div>
    
    <a href="BookTickets.jsp" class="btn">Try Again</a>
</body>
</html>