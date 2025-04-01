<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Successful</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; }
        .success-box { border: 1px solid #4CAF50; padding: 20px; margin: 20px 0; }
        .ticket-info { margin: 15px 0; }
        .btn { padding: 10px 15px; background: #4CAF50; color: white; text-decoration: none; }
    </style>
</head>
<body>
    <h1>Booking Confirmed!</h1>
    
    <div class="success-box">
        <h2>Your PNR: ${pnr}</h2>
        
        <div class="ticket-info">
            <p><strong>Train:</strong> ${trainName} (${trainNumber})</p>
            <p><strong>From:</strong> ${source} <strong>To:</strong> ${destination}</p>
            <p><strong>Date:</strong> ${journeyDate}</p>
        </div>
        
        <p>Your ticket has been successfully booked. Please save your PNR for future reference.</p>
    </div>
    
    <a href="BookTickets.jsp" class="btn">Book Another Ticket</a>
</body>
</html>