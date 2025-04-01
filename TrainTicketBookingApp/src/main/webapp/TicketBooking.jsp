<%@ page import="java.util.List" %>
<%@ page import="models.Station" %>
<%@ page import="dao.GetStationsDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Train Tickets</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 120px; }
        select, input { padding: 8px; width: 250px; }
        .passenger { margin: 15px 0; padding: 15px; border: 1px solid #ddd; border-radius: 5px; }
        .btn { padding: 8px 15px; background: #4CAF50; color: white; border: none; cursor: pointer; margin-right: 10px; }
        .btn-danger { background: #f44336; }
        #trainSection, #passengerSection { display: none; margin-top: 20px; }
        .loading { color: #666; font-style: italic; }
    </style>
</head>
<body>
    <h1>Book Train Tickets</h1>
    
    <%
        GetStationsDAO stationsDAO = new GetStationsDAO();
        List<Station> stations = stationsDAO.getAllStations();
    %>
    
    <form id="bookingForm" action="book-ticket" method="post">
        <!-- Station Selection -->
        <div class="form-group">
            <label>From Station:</label>
            <select name="source" id="sourceStation" required onchange="checkStations()">
                <option value="">-- Select --</option>
                <% for (Station station : stations) { %>
                    <option value="<%= station.getId() %>"><%= station.getName() %> (<%= station.getCode() %>)</option>
                <% } %>
            </select>
        </div>
        
        <div class="form-group">
            <label>To Station:</label>
            <select name="destination" id="destinationStation" required onchange="checkStations()">
                <option value="">-- Select --</option>
                <% for (Station station : stations) { %>
                    <option value="<%= station.getId() %>"><%= station.getName() %> (<%= station.getCode() %>)</option>
                <% } %>
            </select>
        </div>
        
        <!-- Train Selection -->
        <div id="trainSection">
            <div class="form-group">
                <label>Select Train:</label>
                <select name="trainNumber" id="trainSelect" required>
                    <option value="">-- Select Train --</option>
                </select>
                <span id="trainLoading" class="loading" style="display:none;">Loading trains...</span>
            </div>
            
            <div class="form-group">
                <label>Travel Date:</label>
                <input type="date" name="journeyDate" required min="<%= java.time.LocalDate.now() %>">
            </div>
            
            <div class="form-group">
                <label>Class:</label>
                <select name="travelClass" required>
                    <option value="SL">Sleeper (SL)</option>
                    <option value="3A">AC 3 Tier (3A)</option>
                    <option value="2A">AC 2 Tier (2A)</option>
                    <option value="1A">AC First Class (1A)</option>
                </select>
            </div>
        </div>
        
        <!-- Passenger Details -->
        <div id="passengerSection">
            <h2>Passenger Details</h2>
            <div id="passengersContainer">
                <div class="passenger">
                    <h3>Passenger 1</h3>
                    <div class="form-group">
                        <label>Full Name:</label>
                        <input type="text" name="passengerName" required>
                    </div>
                    <div class="form-group">
                        <label>Age:</label>
                        <input type="number" name="passengerAge" required min="1" max="120">
                    </div>
                    <div class="form-group">
                        <label>Gender:</label>
                        <select name="passengerGender" required>
                            <option value="M">Male</option>
                            <option value="F">Female</option>
                            <option value="O">Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Berth Preference:</label>
                        <select name="berthPreference" required>
                            <option value="LB">Lower Berth</option>
                            <option value="MB">Middle Berth</option>
                            <option value="UB">Upper Berth</option>
                            <option value="SL">Side Lower</option>
                            <option value="SU">Side Upper</option>
                        </select>
                    </div>
                </div>
            </div>
            
            <button type="button" class="btn" onclick="addPassenger()">Add Passenger</button>
            <button type="submit" class="btn">Book Tickets</button>
        </div>
    </form>

    <script>
        function checkStations() {
            const source = document.getElementById('sourceStation').value;
            const destination = document.getElementById('destinationStation').value;
            
            if (source && destination) {
                loadTrains();
                document.getElementById('trainSection').style.display = 'block';
            } else {
                document.getElementById('trainSection').style.display = 'none';
                document.getElementById('passengerSection').style.display = 'none';
            }
        }
        
        function loadTrains() {
            const source = document.getElementById('sourceStation').value;
            const destination = document.getElementById('destinationStation').value;
            const trainSelect = document.getElementById('trainSelect');
            const loadingIndicator = document.getElementById('trainLoading');
            
            trainSelect.innerHTML = '<option value="">-- Select Train --</option>';
            trainSelect.disabled = true;
            loadingIndicator.style.display = 'inline';
            
            // AJAX call to fetch trains
            const xhr = new XMLHttpRequest();
            xhr.open('POST', 'train-list', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            
            xhr.onload = function() {
                loadingIndicator.style.display = 'none';
                if (this.status === 200) {
                    trainSelect.innerHTML = this.responseText;
                    trainSelect.disabled = false;
                    document.getElementById('passengerSection').style.display = 'block';
                } else {
                    trainSelect.innerHTML = '<option value="">Error loading trains</option>';
                }
            };
            
            xhr.onerror = function() {
                loadingIndicator.style.display = 'none';
                trainSelect.innerHTML = '<option value="">Error loading trains</option>';
            };
            
            xhr.send('source=' + source + '&destination=' + destination);
        }
        
        function addPassenger() {
            const container = document.getElementById('passengersContainer');
            const count = container.children.length + 1;
            
            const newPassenger = document.createElement('div');
            newPassenger.className = 'passenger';
            newPassenger.innerHTML = `
                <h3>Passenger ${count}</h3>
                <div class="form-group">
                    <label>Full Name:</label>
                    <input type="text" name="passengerName" required>
                </div>
                <div class="form-group">
                    <label>Age:</label>
                    <input type="number" name="passengerAge" required min="1" max="120">
                </div>
                <div class="form-group">
                    <label>Gender:</label>
                    <select name="passengerGender" required>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                        <option value="O">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Berth Preference:</label>
                    <select name="berthPreference" required>
                        <option value="LB">Lower Berth</option>
                        <option value="MB">Middle Berth</option>
                        <option value="UB">Upper Berth</option>
                        <option value="SL">Side Lower</option>
                        <option value="SU">Side Upper</option>
                    </select>
                </div>
                <button type="button" class="btn btn-danger" onclick="this.parentNode.remove()">Remove Passenger</button>
            `;
            
            container.appendChild(newPassenger);
        }
        
        // Set minimum date to today
        document.querySelector('input[type="date"]').min = new Date().toISOString().split('T')[0];
    </script>
</body>
</html>